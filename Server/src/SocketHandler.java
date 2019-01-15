import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class SocketHandler extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private static final String SEPARATOR = "##@%%";

    private static Map<String, Method> commands = new HashMap<>();
    static {
        for (Method method : MethodsWrapper.class.getDeclaredMethods()) {
            commands.put(method.getName(), method);
        }
    }

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
                String[] args = cmd.split(SEPARATOR);
                if ((args.length < 2) || !(commands.containsKey(args[1]))) {
                    send("unknown command");
                } else {
                    send((String) commands.get(args[1]).invoke(null, new Object[]{args}));
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