import com.google.gson.Gson;
import jdk.nashorn.internal.ir.IdentNode;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    public Connection con;

    public Database() throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library_db", "ivan", "password");
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

    private boolean checkType(String id, int[] types) {
        int user_type;
        try {
            user_type = getUserType(id);
        }
        catch (Exception e) {
            return false;
        }
        for (int x : types) {
            if (x == user_type) {
                return true;
            }
        }
        return false;
    }

    public String getUsers(String id) {
        if (!checkType(id, new int[] {0})) {
            return "error";
        }
        return execSelectQuery(
                "SELECT u.users_id, u.surname, u.name, u.patronymic, u.sex, u.login, " +
                       "u.birth_date, u.type, u.phone_number, u.email " +
                "FROM users u");
    }

    public String getPublications() {
        return execSelectQuery("SELECT * FROM publications");
    }

    public String getBooks() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, ph.title, py.year " +
                "FROM publications p " +
                "JOIN publishing_houses_publications php on p.publications_id = php.publications_id " +
                "JOIN publishing_houses ph ON php.publishing_houses_id = ph.publishing_houses_id " +
                "JOIN publishing_years py on p.publications_id = py.publications_id " +
                "WHERE p.type = 0"
        );
    }

    public String getDigests() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, ph.title, py.year " +
                "FROM publications p " +
                "JOIN publishing_houses_publications php on p.publications_id = php.publications_id " +
                "JOIN publishing_houses ph ON php.publishing_houses_id = ph.publishing_houses_id " +
                "JOIN publishing_years py on p.publications_id = py.publications_id " +
                "WHERE p.type = 1"
        );
    }

    public String getArticles() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, m.title, p1.title, mai.volume, mai.release_number " +
                "FROM publications p " +
                "LEFT JOIN digests_publications dp ON p.publications_id = dp.publications_id " +
                "LEFT JOIN magazines_publications mp ON p.publications_id = mp.publications_id " +
                "LEFT JOIN publications p1 ON p1.publications_id = dp.publications_id " +
                "LEFT JOIN magazines m on mp.magazines_id = m.magazines_id " +
                "LEFT JOIN magazine_article_info mai on p.publications_id = mai.publications_id " +
                "WHERE p.type = 2"
        );
    }

    public String getTheses() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, p1.title, m.title " +
                "FROM publications p " +
                "LEFT JOIN digests_publications dp ON p.publications_id = dp.publications_id " +
                "LEFT JOIN magazines_publications mp ON p.publications_id = mp.publications_id " +
                "LEFT JOIN publications p1 ON p1.publications_id = dp.publications_id " +
                "LEFT JOIN magazines m on mp.magazines_id = m.magazines_id " +
                "WHERE p.type = 3"
        );
    }

    public String getDocs() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, o.title " +
                "FROM publications p " +
                "JOIN organizations_publications op on p.publications_id = op.publications_id " +
                "JOIN organizations o on op.organizations_id = o.organizations_id " +
                "WHERE p.type = 4"
        );
    }

    public String getMagazines() {
        return execSelectQuery(
                "SELECT m.magazines_id, m.title, s.title " +
                "FROM magazines m " +
                "JOIN subjects s on m.subjects_id = s.subjects_id"
        );
    }

    public String getAuthors() {
        return execSelectQuery(
                "SELECT * FROM authors"
        );
    }

    public String getEditors() {
        return execSelectQuery(
                "SELECT * FROM editors"
        );
    }

    public String getOrganizations() {
        return execSelectQuery(
                "SELECT * FROM organizations"
        );
    }

    public String getPublHouses() {
        return execSelectQuery(
                "SELECT * FROM publishing_houses"
        );
    }

    public String getKeywordsOfPubl(String id) {
        String s =
                "SELECT k.keywords_id, k.keyword " +
                "FROM keywords k " +
                "JOIN publications_keywords pk on k.keywords_id = pk.keywords_id " +
                "JOIN publications p on pk.publications_id = p.publications_id " +
                "WHERE p.publications_id = ?";
        return execPSWithId(id, s);
    }

    public String getUdcOfPubl(String id) {
        String s =
                "SELECT uc.udc_codes_id, uc.udc_code " +
                "FROM udc_codes uc " +
                "JOIN publications_udc_codes puc ON uc.udc_codes_id = puc.udc_codes_id " +
                "JOIN publications p on puc.publications_id = p.publications_id " +
                "WHERE p.publications_id = ?";
        return execPSWithId(id, s);
    }

    public String getAllKeywords() {
        return execSelectQuery(
                "SELECT * FROM keywords"
        );
    }

    public String getAllUdc() {
        return execSelectQuery(
                "SELECT * FROM udc_codes"
        );
    }

    public String getVerfs(String id) {
        if (!checkType(id, new int[] {1})) {
            return "error";
        }
        return execSelectQuery(
                "SELECT v.verifications_id, v.users_id, u.login, u.phone_number, u.email, v.to_type " +
                "FROM verifications v " +
                "JOIN users u on v.users_id = u.users_id"
        );
    }

    private int getUserType(String id) throws Exception {
        PreparedStatement ps = con.prepareStatement("SELECT u.type FROM users u WHERE u.users_id = ?");
        ps.setInt(1, Integer.parseInt(id));
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new Exception("No such user");
        }
    }

    private String execPSWithId(String id, String query) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            return RSToString(rs);
        }
        catch (SQLException e) {
            return "error";
        }
    }

    public String addUser(String id, String name, String surname, String patronymic, String sex, String login,
                          String password, String birth_date, String type, String phone_number, String email) {
        if (!checkType(id, new int[] {0})) {
            return "error";
        }
        try {
            String query = "INSERT INTO users(name, surname, patronymic, sex, login, password, birth_date, type, " +
                    "phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            if (surname.equals("NULL")) {
                ps.setNull(2, Types.VARCHAR);
            } else {
                ps.setString(2, surname);
            }
            if (patronymic.equals("NULL")) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, patronymic);
            }
            ps.setInt(4, Integer.parseInt(sex));
            ps.setString(5, login);
            ps.setString(6, password);
            if (birth_date.equals("NULL")) {
                ps.setNull(7, Types.DATE);
            } else {
                ps.setDate(7, java.sql.Date.valueOf(birth_date));
            }
            ps.setInt(8, Integer.parseInt(type));
            if (phone_number.equals("NULL")) {
                ps.setNull(9, Types.BIGINT);
            } else {
                ps.setLong(9, Long.parseLong(phone_number));
            }
            if (email.equals("NULL")) {
                ps.setNull(10, Types.VARCHAR);
            } else {
                ps.setString(10, email);
            }
            ps.execute();
            return "ok";
        }
        catch (Exception e) {
            return "error";
        }
    }

    private String execSelectQuery(String query) {
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return RSToString(rs);
        }
        catch (SQLException e) {
            return "error";
        }
    }

    private String RSToString(ResultSet rs) throws SQLException {
        int columnCount = rs.getMetaData().getColumnCount();
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        while (rs.next()) {
            ArrayList<String> row = new ArrayList<>();
            int i = 1;
            while (i <= columnCount) {
                row.add(rs.getString(i++));
            }
            rows.add(row);
        }
        return new Gson().toJson(rows);
    }
}
