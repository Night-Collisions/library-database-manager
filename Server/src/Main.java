import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Main {

    static private final int PORT = 9012;
    static private LinkedList<SocketHandler> serverList = new LinkedList<>();
    static Database db = null;

    public static void main(String[] args) throws IOException {
        try {
            db = new Database();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new SocketHandler(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}