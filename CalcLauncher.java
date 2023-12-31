import java.io.*;
import java.net.*;

public class CalcLauncher {
    private boolean log_recording;
    private boolean log_playing;
    private boolean share;
    private BufferedReader ins;
    private PrintStream outs;
    private PileRPL pile;

    public CalcLauncher(String[] args) throws Exception {
        initStreams(args);
        CalcUI calc = new CalcUI(ins, outs, log_playing, log_recording, pile, share);
    }

    	public void initStreams(String[] args) throws Exception{
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
                case "share":
                    share = true;
                    break;
			}
		}
		if(local && !log_playing) initFullLocal();
		if(remote && !log_playing) initFullRemote();
		if(local && log_playing) initReplayLocal();
		if(remote && log_playing) initReplayRemote();
		
	}
	
public void initFullLocal() throws Exception{
	ins = new BufferedReader(new InputStreamReader(System.in));
	outs = System.out;
    
}

public void initFullRemote() throws IOException {
    try(ServerSocket serverSocket = new ServerSocket(2222);){
        while(true){
            Socket socket = serverSocket.accept();
            if(!share) pile = new PileRPL();
            Thread t = new Thread(() -> {
                try{
                    ins = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    outs = new PrintStream(socket.getOutputStream());
                    outs.println("Connecté");
                    Remote remote = new Remote(socket, ins, outs, log_playing, log_recording, pile, share);
                    remote.connect();
                } catch (IOException e){
                    e.printStackTrace();
                }finally{
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }
}

public void initReplayLocal() throws Exception {
    ins = new BufferedReader(new InputStreamReader(new FileInputStream("log.txt")));
    outs = System.out;
}

public void initReplayRemote() throws IOException {
    try(ServerSocket serverSocket = new ServerSocket(2222);){
        while(true){
            Socket socket = serverSocket.accept();
            Thread t = new Thread(() -> {
                try{
                    ins = new BufferedReader(new InputStreamReader(new FileInputStream("log.txt")));
                    outs = new PrintStream(socket.getOutputStream());
                    outs.println("Replay");
                    Remote remote = new Remote(socket, ins, outs, log_playing, log_recording, pile, share);
                    remote.connect();
                } catch (IOException e){
                    e.printStackTrace();
                }finally{
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }
}

}
