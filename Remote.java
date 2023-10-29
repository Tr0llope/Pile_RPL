import java.io.*;
import java.net.Socket;

public class Remote {
    private Socket socket;
    private BufferedReader ins;
    private PrintStream outs;
    private boolean log_recording;
    private boolean log_playing;
    private boolean share;
    private PileRPL pile;

    public Remote(Socket socket, BufferedReader ins, PrintStream outs, boolean log_playing, boolean log_recording, PileRPL pile, boolean share) {
        this.socket = socket;
        this.ins = ins;
        this.outs = outs;
        this.log_recording = log_recording;
        this.log_playing = log_playing;
        this.pile = pile;
        this.share = share;

    }
    public void connect() {
        try{
            CalcUI calc = new CalcUI(ins, outs, log_playing, log_recording, pile, share);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
