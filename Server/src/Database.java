import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    public Connection con = null;

    public Database() throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library_db","ivan", "password");
    }

    public String authUser(String login, String password) {
        String s = "SELECT * FROM users WHERE login = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(s);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            String[] user = {"-1", "", "", "", "", "", "", "", "", "", ""};
            if (rs.next()) {
                int i = 1;
                while (i <= columnCount) {
                    user[i - 1] = rs.getString(i);
                    ++i;
                }
            }
            return String.join(", ", user);

        }
        catch (SQLException e) {
            return "error";
        }
    }


//    public static void main(String[] args) throws Exception {
//        Class.forName("org.postgresql.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital_db","ivan", "password");
//        Statement statement = connection.createStatement();
//
//        String query = "SELECT * FROM patients";
//        ResultSet rs = null;
//        try {
//            rs = statement.executeQuery(query);
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return;
//        }
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columnCount = rsmd.getColumnCount();
//
//        ArrayList<ArrayList<String>> patients = new ArrayList<>();
//        while (rs.next()) {
//            ArrayList<String> patient = new ArrayList<>();
//            int i = 1;
//            while (i <= columnCount) {
//                patient.add(rs.getString(i++));
//            }
//            patients.add(patient);
//        }
//
//
//        String to = new Gson().toJson(patients);
//        System.out.println(to);
//
//        Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
//        ArrayList<ArrayList<String>> from = new Gson().fromJson(to, type);
//        System.out.println(from);
//
//        connection.close();
//    }
}
