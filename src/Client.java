import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket sock;
    private DataOutputStream out;

    public Client(String host, int port) {
        try {
            sock = new Socket(host, port);
            out = new DataOutputStream(sock.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBytes(byte[] bytes) {
        try {
            out.write(bytes);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPixelValue(int x, int y, int r, int g, int b) {
        writeBytes(new byte[] {
                // invert x
                (byte) (x == 255 ? 255 : 15 - x),
                (byte) y,
                (byte) r,
                (byte) g,
                (byte) b,
        });
    }

    public void show() {
        setPixelValue(255, 255, 255, 255, 255);
    }

    public void close() {
        try {
            out.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
