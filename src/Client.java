import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket sock;
    private PrintWriter out;

    public Client(String host, int port) {
        try {
            sock = new Socket(host, port);
            out = new PrintWriter(sock.getOutputStream(), true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeString(String s) {
        out.println(s);
    }

    public void close() {
        out.close();
        try {
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
