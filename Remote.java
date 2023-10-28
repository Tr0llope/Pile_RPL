import java.io.*;
import java.net.Socket;
import java.nio.Buffer;

public class Remote {
    public Socket socket;
    public BufferedReader ins;
    public PrintStream outs;
    boolean log_recording;
    boolean log_playing;

    public Remote(Socket socket, BufferedReader ins, PrintStream outs, boolean log_playing, boolean log_recording) {
        this.socket = socket;
        this.ins = ins;
        this.outs = outs;
        this.log_recording = log_recording;
        this.log_playing = log_playing;
    }
    public void connect() {
        try{
            CalcUI calc = new CalcUI(ins, outs, log_playing, log_recording);
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
