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
                    case "getBooks":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getBooks());
                        break;
                    case "getDigests":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getDigests());
                        break;
                    case "getArticles":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getArticles());
                        break;
                    case "getTheses":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getTheses());
                        break;
                    case "getDocs":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getDocs());
                        break;
                    case "getMagazines":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getMagazines());
                        break;
                    case "getAuthors":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getAuthors());
                        break;
                    case "getEditors":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getEditors());
                        break;
                    case "getOrganizations":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getOrganizations());
                        break;
                    case "getPublHouses":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getPublHouses());
                        break;
                    case "getKeywordsOfPubl":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getKeywordsOfPubl(data[2]));
                        break;
                    case "getUdcOfPubl":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getUdcOfPubl(data[2]));
                        break;
                    case "getAllKeywords":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getAllKeywords());
                        break;
                    case "getAllUdc":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getAllUdc());
                        break;
                    case "getVerfs":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getVerfs(data[0]));
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