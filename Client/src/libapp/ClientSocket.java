package libapp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {
    private int port; // здесь обязательно нужно указать порт к которому привязывается сервер.
    private String address;
    InetAddress ipAddress;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public ClientSocket(String address, int port) {
        this.address = address;
        this.port = port;

        try {
            this.ipAddress = InetAddress.getByName(this.address); // создаем объект который отображает вышеописанный IP-адрес.
            this.socket = new Socket(ipAddress, this.port); // создаем сокет используя IP-адрес и порт сервера.

            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

/*            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
            System.out.println();*/


        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public String makeRequest(String request) throws IOException {
        String result;
        out.writeUTF(request);
        out.flush();

        result = in.readUTF(); // ждем пока сервер отошлет строку текста.

/*            if (*//*нормальный*//*) {
            // То возвращаем
        } else  {
            // Говорим, что все плохо
        }*/

        return result;
    }
}
