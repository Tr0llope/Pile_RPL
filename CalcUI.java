import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;

public class CalcUI {
	boolean log_recording;
	boolean log_playing;
	public static InputStream ins;
	public static PrintStream outs;
	
	public CalcUI(String[] args){
		initStreams(args);
		mainLoop();
	}
	
	public void initStreams(String[] args){
		boolean local = false;
		boolean	remote = false;
		for(String s: args){
			switch(s){
				case "local":
					local = true;
					break;
				case "remote":
					remote = true;
					break;
				case "log":
					log_recording = true;
					break;
				case "replay":
					log_playing = true;
					break;
			}
		}
		if(local) initFullLocal();
		//if(remote) initFullRemote();
		if(local && log_playing) initReplayLocal();
		//if(remote && log_playing) initReplayRemote();
		
	}
	
	public void initFullLocal(){
		ins = System.in;
		outs = System.out;
	}
	/*public void initFullRemote(){
		ServerSocket serversocket = new ServerSocket(4040);
		Socket socket = serversocket.accept();
		ins = socket.getInputStream();
		outs = new PrintStream( socket.getOutputStream());
	}
	*/
	public void initReplayLocal(){
		try{ins = new FileInputStream("log.txt");}catch (Exception e){return;}
		outs = System.out;
	}
	/*public void initReplayRemote(){
		ServerSocket serversocket = new ServerSocket(4040);
		Socket socket = serversocket.accept();
		try{ins = new FileInputStream("log.txt");}catch (Exception e){return;}
		outs = new PrintStream( socket.getOutputStream());
	}*/

	public void mainLoop(){
		File logfile = null;
		FileWriter flog = null;
		BufferedWriter bufflog = null;
		try{
				if(log_recording){
					logfile = new File("log.txt");
					flog = new FileWriter(logfile.getAbsoluteFile());
					bufflog = new BufferedWriter(flog);
					outs.println("Logged Session !");
				}

		outs.println("Bienvenue dans la calculatrice RPL !\n" + //
				"Taille de la pile:");
		Scanner sc = new Scanner(ins);
		int taille = sc.nextInt();
		
		if(log_recording) bufflog.write(taille+"\n");
		if(log_playing) outs.println(taille);
		
		PileRPL pile = new PileRPL(taille);
	
		String input = sc.nextLine();
		while(!input.equals("quit")){
		String[] command = input.split(" ");
			
			if(log_recording) bufflog.write(input+"\n");
			if(log_playing) outs.println(input);
			
			for(int i = 0; i<command.length;i++){
			switch(command[i]){
				case "push":
					i+=1;
					ObjEmp o = new ObjEmp(Integer.parseInt(command[i]));
					pile.push(o);
					break;

				case "add":
					pile.ope("add");
					break;

				case "sub":
					pile.ope("sub");
					break;
				case "mul":
					pile.ope("mul");
					break;
				case "div":
					pile.ope("div");
					break;
		}//fin switch
		}//fin parcours input cmd
		if(!pile.isEmpty()){
			outs.println(pile);
		}
		input = sc.nextLine();
	}
	bufflog.close();
	}catch (Exception e){return;}
}
}
