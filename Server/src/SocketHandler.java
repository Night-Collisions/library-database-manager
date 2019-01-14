import java.io.*;
import java.net.Socket;

class SocketHandler extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private static final String SEPARATOR = "##@%%";

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
                String data[] = cmd.split(SEPARATOR);
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
                    case "getUsers":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getUsers(data[0]));
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
                    case "getAuthorsOfPubl":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getAuthorsOfPubl(data[2]));
                        break;
                    case "getEditors":
                        if (data.length != 2) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getEditors());
                        break;
                    case "getEditorsOfPubl":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getEditorsOfPubl(data[2]));
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
                    case "getAuthOrg":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.getAuthOrg(data[2]));
                        break;
                    case "addUser":
                        if (data.length != 12) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addUser(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                                data[9], data[10], data[11]));
                        break;
                    case "addAuthor":
                        if (data.length != 10) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addAuthor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                                data[9]));
                        break;
                    case "addEditor":
                        if (data.length != 10) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addEditor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                                data[9]));
                        break;
                    case "addBook":
                        if (data.length != 5) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addBook(data[0], data[2], data[3], data[4]));
                        break;
                    case "addDigest":
                        if (data.length != 5) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addDigest(data[0], data[2], data[3], data[4]));
                        break;
                    case "addMArticle":
                        if (data.length != 6) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addMArticle(data[0], data[2], data[3], data[4], data[5]));
                        break;
                    case "addDArticle":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addDArticle(data[0], data[2], data[3]));
                        break;
                    case "addMTheses":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addMTheses(data[0], data[2], data[3]));
                        break;
                    case "addDTheses":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addDTheses(data[0], data[2], data[3]));
                        break;
                    case "addDocs":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addDocs(data[0], data[2], data[3]));
                        break;
                    case "addMagazine":
                        if (data.length != 5) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addMagazine(data[0], data[2], data[3], data[4]));
                        break;
                    case "addOrganization":
                        if (data.length != 6) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addOrganization(data[0], data[2], data[3], data[4], data[5]));
                        break;
                    case "addPublHouse":
                        if (data.length != 6) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addPublHouse(data[0], data[2], data[3], data[4], data[5]));
                        break;
                    case "addSubject":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addSubject(data[0], data[2]));
                        break;
                    case "addKeyword":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addKeyword(data[0], data[2]));
                        break;
                    case "addUdc":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addUdc(data[0], data[2]));
                        break;
                    case "addKeywordToPubl":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addKeywordToPubl(data[0], data[2], data[3]));
                        break;
                    case "addUdcToPubl":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addUdcToPubl(data[0], data[2], data[3]));
                        break;
                    case "addAuthToPubl":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addAuthToPubl(data[0], data[2], data[3]));
                        break;
                    case "addEditorToPubl":
                        if (data.length != 4) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.addEditorToPubl(data[0], data[2], data[3]));
                        break;
                    case "deleteUser":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteUser(data[0], data[2]));
                        break;
                    case "deletePublication":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deletePublication(data[0], data[2]));
                        break;
                    case "deleteAuthor":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteAuthor(data[0], data[2]));
                        break;
                    case "deleteEditor":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteEditor(data[0], data[2]));
                        break;
                    case "deleteOrganization":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteOrganization(data[0], data[2]));
                        break;
                    case "deletePublHouse":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deletePublHouse(data[0], data[2]));
                        break;
                    case "deleteKeyword":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteKeyword(data[0], data[2]));
                        break;
                    case "deleteUdc":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteUdc(data[0], data[2]));
                        break;
                    case "deleteMagazine":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteMagazine(data[0], data[2]));
                        break;
                    case "deleteSubject":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteSubject(data[0], data[2]));
                        break;
                    case "deleteVerification":
                        if (data.length != 3) {
                            send("wrong args");
                            continue;
                        }
                        send(Server.db.deleteVerification(data[0], data[2]));
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