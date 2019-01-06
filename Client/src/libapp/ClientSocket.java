package libapp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {
    static private int port; // здесь обязательно нужно указать порт к которому привязывается сервер.
    static private String address;
    static InetAddress ipAddress;
    static Socket socket;
    static BufferedReader in;
    static BufferedWriter out;

    public ClientSocket(String address, int port) throws IOException {
        this.address = address;
        this.port = port;

        try {
            this.ipAddress = InetAddress.getByName(this.address); // создаем объект который отображает вышеописанный IP-адрес.
            this.socket = new Socket(ipAddress, this.port); // создаем сокет используя IP-адрес и порт сервера.

            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

/*            // Создаем поток для чтения с клавиатуры.
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
            System.out.println();*/


        } catch (Exception e){
            assert socket != null;
            socket.close();
            assert in != null;
            in.close();
            assert out != null;
            out.close();
        }
    }

    public String makeRequest(String request) throws IOException {
        String result;
        out.write(request + '\n');
        out.flush();

        result = in.readLine(); // ждем пока сервер отошлет строку текста.

/*            if (*//*нормальный*//*) {
            // То возвращаем
        } else  {
            // Говорим, что все плохо
        }*/

        System.out.println(result);

        return result;
    }

    public Socket getSocket() {
        return socket;
    }
}
