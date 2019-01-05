import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
        String cmd;
        try {
            while (true) {
                cmd = in.readLine();
                String data[] = cmd.split(", ");
                if (data.length < 2) {
                    send("unknown command");
                    continue;
                }
                switch (data[1]) {
                    case "authUser":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.authUser(data[2], data[3]));
                        break;
                    case "getPublications":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getPublications());
                        break;
                    default:
                        send("unknown command");
                        break;
                }
            }

        } catch (Exception e) {
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