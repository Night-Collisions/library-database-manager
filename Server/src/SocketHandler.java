import java.io.*;
import java.net.Socket;

class SocketHandler extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public SocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            while (true) {
                word = in.readLine();
                // TODO
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}
    }
}