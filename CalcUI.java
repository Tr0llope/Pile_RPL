import java.util.*;
import java.io.*;

public class CalcUI {
	boolean log_recording;
	boolean log_playing;
	public BufferedReader ins;
	public PrintStream outs;
	PileRPL p;
	
	public CalcUI(BufferedReader ins, PrintStream outs, boolean log_playing, boolean log_recording) throws Exception{
		this.ins = ins;
		this.outs = outs;
		this.log_playing = log_playing;
		this.log_recording = log_recording;
		mainLoop();
	}
	
	public void parser(String input, PileRPL pile){
		String[] command = input.split(" ");
		for(int i = 0; i<command.length;i++){
			switch(command[i]){
				case "push":
					i+=1;
					String [] elem2D = command[i].split(",");
					if(elem2D.length == 1){
						ObjEmp o = new ObjEmp(Integer.parseInt(elem2D[0]));
						pile.push(o);
					}
					else if(elem2D.length == 2){
						ObjEmp o = new ObjEmp(Integer.parseInt(elem2D[0]), Integer.parseInt(elem2D[1]));
						pile.push(o);
					}
					else{
						ObjEmp o = new ObjEmp(Integer.parseInt(elem2D[0]), Integer.parseInt(elem2D[1]), Integer.parseInt(elem2D[2]));
						pile.push(o);
					}
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
			}
		}
	}

	public void mainLoop(){
		File logfile = null;
		FileWriter flog = null;
		BufferedWriter bufflog = null;
		try{
				if(log_recording && !log_playing){
					logfile = new File("log.txt");
					flog = new FileWriter(logfile.getAbsoluteFile());
					bufflog = new BufferedWriter(flog);
					outs.println("Logged Session !");
				}

		outs.println("Bienvenue dans la calculatrice RPL !\n" + //
				"Taille de la pile:");
		Scanner sc = new Scanner(ins);
		String input = sc.nextLine();
		int taille = Integer.parseInt(input);
		PileRPL pile = new PileRPL(taille);

		while(!input.equals("quit")){
		String[] command = input.split(" ");
			
			if(log_recording) bufflog.write(input+"\n");
			if(log_playing) outs.println(input);
			parser(input, pile);

		if(!pile.isEmpty()){
			outs.println(pile);
		}
		input = sc.nextLine();
	}
	sc.close();
	bufflog.close();
	}catch (Exception e){return;}
}
}
