package libapp;

public class QueryParser {
    public static String buildQuery(String[] args) {
        return String.join(ClientSocket.argSep, args);
    }
}
