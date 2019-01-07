package libapp;

import libapp.view.MessageController;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {
    static private int port;
    static private String address;
    private static InetAddress ipAddress;
    private static Socket socket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public ClientSocket(String address, int port) throws IOException {
        ClientSocket.address = address;
        ClientSocket.port = port;

        try {
            ipAddress = InetAddress.getByName(ClientSocket.address);
            socket = new Socket(ipAddress, ClientSocket.port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e){
            if (socket != null) {
                socket.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public String makeRequest(String request) {
        try {
            String result;
            out.write(request + '\n');
            out.flush();

            result = in.readLine();
            return result;
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }

        return "-1";
    }

    public static ClientSocket enableConnection(ClientSocket s) throws IOException {
        if (s != null) {
            if (s.getSocket().isClosed() || !s.getSocket().isConnected()) {
                return new ClientSocket("localhost", 9012);
            }
        } else {
            return new ClientSocket("localhost", 9012);
        }

        return s;
    }

    public Socket getSocket() {
        return socket;
    }
}
