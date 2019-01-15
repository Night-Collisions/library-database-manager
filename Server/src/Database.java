import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    public Connection con;

    private static final int U_ADMIN = 0;
    private static final int U_LIBRARIAN = 1;
    private static final int U_PUBL_HOUSE = 2;
    private static final int U_AUTHOR = 3;
    private static final int U_READER = 4;

    private static final int S_WOMAN = 0;
    private static final int S_MAN = 1;
    private static final int S_OTHER = 2;

    private static final int P_BOOK = 0;
    private static final int P_DIGEST = 1;
    private static final int P_ARTICLE = 2;
    private static final int P_THESES = 3;
    private static final int P_DOCS = 4;

    public Database() throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library_db", "ivan", "password");
    }

    public String authUser(String login, String password) {
        String s = "SELECT users_id, name, surname, patronymic, sex, login, birth_date, type, phone_number, email " +
                   "FROM users WHERE login = ? AND password = ?";
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
            return e.getMessage();
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
        if (!checkType(id, new int[] {U_ADMIN})) {
            return "access error";
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
                "WHERE p.type = " + P_BOOK
        );
    }

    public String getDigests() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, ph.title, py.year " +
                "FROM publications p " +
                "JOIN publishing_houses_publications php on p.publications_id = php.publications_id " +
                "JOIN publishing_houses ph ON php.publishing_houses_id = ph.publishing_houses_id " +
                "JOIN publishing_years py on p.publications_id = py.publications_id " +
                "WHERE p.type = " + P_DIGEST
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
                "WHERE p.type = " + P_ARTICLE
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
                "WHERE p.type = " + P_THESES
        );
    }

    public String getDocs() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, o.title " +
                "FROM publications p " +
                "JOIN organizations_publications op on p.publications_id = op.publications_id " +
                "JOIN organizations o on op.organizations_id = o.organizations_id " +
                "WHERE p.type = " + P_DOCS
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

    public String getAuthorsOfPubl(String p_id) {
        String query =
                "SELECT a.authors_id, a.name, a.surname, a.patronymic, a.sex, a.birth_date, " +
                       "a.death_date, a.phone_number, a.email " +
                "FROM authors a " +
                "JOIN authors_publications ap ON a.authors_id = ap.authors_id" +
                                            "AND ap.publications_id = ?";
        return execPSWithId(p_id, query);
    }

    public String getEditors() {
        return execSelectQuery(
                "SELECT * FROM editors"
        );
    }

    public String getEditorsOfPubl(String p_id) {
        String query =
                "SELECT e.editors_id, e.name, e.surname, e.patronymic, e.sex, e.birth_date, " +
                       "e.death_date, e.phone_number, e.email " +
                "FROM editors e " +
                "JOIN editors_publications ep ON e.editors_id = ep.editors_id " +
                                            "AND ep.publications_id = 9;";
        return execPSWithId(p_id, query);
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
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return execSelectQuery(
                "SELECT v.verifications_id, v.users_id, u.login, u.phone_number, u.email, v.to_type, v.authors_id, " +
                        "v.publishing_houses_id " +
                "FROM verifications v " +
                "JOIN users u on v.users_id = u.users_id"
        );
    }

    public String getAuthOrg(String a_id) {
        String query =
                "SELECT o.title, ao.start, ao.finish " +
                "FROM authors_organizations ao " +
                "JOIN authors a on ao.authors_id = ? " +
                "JOIN organizations o on ao.organizations_id = o.organizations_id";
        return execPSWithId(a_id, query);
    }

    public String getUserPubl(String u_id) {
        try {
            String query =
                    "SELECT p.publications_id " +
                    "FROM publications p " +
                    "LEFT JOIN authors_publications ap ON p.publications_id = ap.publications_id " +
                    "LEFT JOIN users_authors ua ON ua.authors_id = ap.authors_id " +
                    "LEFT JOIN publishing_houses_publications php ON p.publications_id = php.publications_id " +
                    "LEFT JOIN users_publishing_houses uph ON uph.publishing_houses_id = php.publishing_houses_id " +
                    "WHERE ua.users_id = ? OR uph.users_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(u_id));
            ps.setLong(2, Long.parseLong(u_id));
            ResultSet rs = ps.executeQuery();
            return RSToString(rs);
        }
        catch (SQLException e) {
            return e.getMessage();
        }
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
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            return RSToString(rs);
        }
        catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String addUser(String id, String name, String surname, String patronymic, String sex, String login,
                          String password, String birth_date, String type, String phone_number, String email) {
        if (!checkType(id, new int[] {U_ADMIN})) {
            return "access error";
        }
        try {
            String query = "INSERT INTO users(name, surname, patronymic, sex, login, password, birth_date, type, " +
                    "phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            setFullNameAndSexInPs(ps, name, surname, patronymic, sex);
            ps.setString(5, login);
            ps.setString(6, password);
            setDateInPs(ps, birth_date, 7);
            ps.setInt(8, Integer.parseInt(type));
            setPhoneAndEmailInPs(ps, phone_number, email, 9);
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addBook(String id, String title, String ph_id, String year) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query_p = "INSERT INTO publications(type, title) VALUES (" + P_BOOK + ", ?) RETURNING publications_id";
            addPublHouseAndYear(title, ph_id, year, query_p);
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addDigest(String id, String title, String ph_id, String year) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query_p = "INSERT INTO publications(type, title) VALUES (" + P_DIGEST + ", ?) " +
                    "RETURNING publications_id";
            addPublHouseAndYear(title, ph_id, year, query_p);
            return "ok";        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addMArticle(String id, String title, String m_id, String volume, String relase_number) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query_p = "INSERT INTO publications(type, title) VALUES (" + P_ARTICLE + ", ?) " +
                    "RETURNING publications_id";
            long p_id = getPublIdAfterInsert(title, query_p);

            addMagazinePublication(p_id, m_id);
            
            String query_mai = "INSERT INTO magazine_article_info(publications_id, volume, release_number) VALUES (?, ?, ?)";
            PreparedStatement ps_mai = con.prepareStatement(query_mai);
            ps_mai.setLong(1, p_id);
            ps_mai.setLong(2, Integer.parseInt(volume));
            ps_mai.setLong(3, Integer.parseInt(relase_number));
            ps_mai.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addDArticle(String id, String title, String d_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return addPublicationWithDigest(P_ARTICLE, title, d_id);
    }

    public String addMTheses(String id, String title, String m_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query_p = "INSERT INTO publications(type, title) VALUES (" + P_THESES + ", ?) " +
                    "RETURNING publications_id";
            long p_id = getPublIdAfterInsert(title, query_p);
            addMagazinePublication(p_id, m_id);
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addDTheses(String id, String title, String d_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return addPublicationWithDigest(P_THESES, title, d_id);
    }

    public String addDocs(String id, String title, String o_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query_p = "INSERT INTO publications(type, title) VALUES (" + P_DOCS + ", ?) " +
                    "RETURNING publications_id";
            long p_id = getPublIdAfterInsert(title, query_p);
            String query_op = "INSERT INTO organizations_publications(publications_id, organizations_id) VALUES (?, ?)";
            PreparedStatement ps_op = con.prepareStatement(query_op);
            ps_op.setLong(1, p_id);
            ps_op.setInt(2, Integer.parseInt(o_id));
            ps_op.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addMagazine(String id, String title, String s_id, String o_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query =
                    "INSERT INTO magazines(title, subjects_id, organizations_id) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setLong(2, Long.parseLong(s_id));
            ps.setLong(3, Long.parseLong(o_id));
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addSubject(String id, String subject) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query =
                    "INSERT INTO subjects(title) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, subject);
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addOrganization(String id, String title, String address, String phone, String email) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO organizations(title, legal_address, phone_number, email) VALUES (?, ?, ?, ?)";
        return addOrgOrPublHouseCommonPart(query, title, address, phone, email);
    }

    public String addPublHouse(String id, String title, String address, String phone, String email) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO publishing_houses(title, legal_address, phone_number, email) VALUES (?, ?, ?, ?)";
        return addOrgOrPublHouseCommonPart(query, title, address, phone, email);
    }

    private String addOrgOrPublHouseCommonPart(String query, String title, String address, String phone, String email) {
        try {
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, title);
            ps.setString(2, address);
            setPhoneAndEmailInPs(ps, phone, email, 3);
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addAuthor(String id, String name, String surname, String patronymic, String sex, String birth_date,
                     String death_date, String phone_number, String email) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO authors(name, surname, patronymic, sex, birth_date, death_date, phone_number, email) " +
                                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return addAuthorOrEditorCommonPart(query, name, surname, patronymic, sex, birth_date, death_date, phone_number, email);
    }

    public String addEditor(String id, String name, String surname, String patronymic, String sex, String birth_date,
                            String death_date, String phone_number, String email) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO editors(name, surname, patronymic, sex, birth_date, death_date, phone_number, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return addAuthorOrEditorCommonPart(query, name, surname, patronymic, sex, birth_date, death_date, phone_number, email);
    }

    public String addAuthorOrEditorCommonPart(String query, String name, String surname, String patronymic, String sex,
                                              String birth_date, String death_date, String phone_number, String email) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            setFullNameAndSexInPs(ps, name, surname, patronymic, sex);
            setDateInPs(ps, birth_date, 5);
            setDateInPs(ps, death_date, 6);
            if (phone_number.equals("NULL")) {
                ps.setNull(7, Types.BIGINT);
            } else {
                ps.setLong(7, Long.parseLong(phone_number));
            }
            if (email.equals("NULL")) {
                ps.setNull(8, Types.VARCHAR);
            } else {
                ps.setString(8, email);
            }
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    private void setFullNameAndSexInPs(PreparedStatement ps, String name, String surname,
                                       String patronymic, String sex) throws Exception {
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
    }

    public String addKeyword(String id, String keyword) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query =
                    "INSERT INTO keywords(keyword) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, keyword);
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addUdc(String id, String udc) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query =
                    "INSERT INTO udc_codes(udc_code) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, udc);
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addVerf(String u_id, String to_type, String a_id, String ph_id) {
        if (!checkType(u_id, new int[] {U_READER})) {
            return "access error";
        }
        try {
            String query = "INSERT INTO verifications(users_id, to_type, authors_id, publishing_houses_id) " +
                           "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(u_id));
            ps.setInt(2, Integer.parseInt(to_type));
            if (a_id.equals("NULL")) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setLong(3, Long.parseLong(a_id));
            }
            if (ph_id.equals("NULL")) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setLong(4, Long.parseLong(ph_id));
            }
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String addAuthOrg(String u_id, String a_id, String o_id, String start, String finish) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query = "INSERT INTO authors_organizations(authors_id, organizations_id, start, finish) VALUES " +
                    "(?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(a_id));
            ps.setLong(2, Long.parseLong(o_id));
            ps.setDate(3, java.sql.Date.valueOf(start));
            ps.setDate(4, java.sql.Date.valueOf(finish));
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }    }

    public String addKeywordToPubl(String id, String p_id, String k_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO publications_keywords(publications_id, keywords_id) VALUES (?, ?)";
        return doSmthToPubl(query, p_id, k_id);
    }

    public String addUdcToPubl(String id, String p_id, String u_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO publications_udc_codes(publications_id, udc_codes_id) VALUES (?, ?)";
        return doSmthToPubl(query, p_id, u_id);
    }

    public String addAuthToPubl(String id, String p_id, String a_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO authors_publications(publications_id, authors_id) VALUES (?, ?)";
        return doSmthToPubl(query, p_id, a_id);
    }

    public String addEditorToPubl(String id, String p_id, String e_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO editors_publications(publications_id, editors_id) VALUES (?, ?)";
        return doSmthToPubl(query, p_id, e_id);
    }

    private String doSmthToPubl(String query, String p_id, String smth_id) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(p_id));
            ps.setLong(2, Long.parseLong(smth_id));
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    private String addPublicationWithDigest(int type, String title, String d_id) {
        try {
            String query_p = "INSERT INTO publications(type, title) VALUES (" + type + ", ?) " +
                    "RETURNING publications_id";
            long p_id = getPublIdAfterInsert(title, query_p);
            addDigestPublication(p_id, d_id);
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    private void addMagazinePublication(long p_id, String m_id) throws Exception {
        String query_mp = "INSERT INTO magazines_publications(publications_id, magazines_id) VALUES (?, ?)";
        PreparedStatement ps_mp = con.prepareStatement(query_mp);
        ps_mp.setLong(1, p_id);
        ps_mp.setInt(2, Integer.parseInt(m_id));
        ps_mp.executeUpdate();
    }

    private void addDigestPublication(long p_id, String d_id) throws Exception {
        String query_dp = "INSERT INTO digests_publications(publications_id, digests_id) VALUES (?, ?)";
        PreparedStatement ps_dp = con.prepareStatement(query_dp);
        ps_dp.setLong(1, p_id);
        ps_dp.setInt(2, Integer.parseInt(d_id));
        ps_dp.executeUpdate();
    }

    private long getPublIdAfterInsert(String title, String query_p) throws Exception {
        PreparedStatement ps_p = con.prepareStatement(query_p, PreparedStatement.RETURN_GENERATED_KEYS);
        ps_p.setString(1, title);
        ps_p.executeUpdate();
        ResultSet rs = ps_p.getGeneratedKeys();
        rs.next();
        return rs.getLong(1);
    }

    private String execSelectQuery(String query) {
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return RSToString(rs);
        }
        catch (SQLException e) {
            return e.getMessage();
        }
    }

    private void addPublHouseAndYear(String title, String ph_id, String year, String query_p) throws Exception {
        long p_id = getPublIdAfterInsert(title, query_p);

        String query_php = "INSERT INTO publishing_houses_publications(publications_id, publishing_houses_id) " +
                "VALUES (?, ?)";
        PreparedStatement ps_php = con.prepareStatement(query_php);
        ps_php.setLong(1, p_id);
        ps_php.setInt(2, Integer.parseInt(ph_id));
        ps_php.executeUpdate();

        String query_pe = "INSERT INTO publishing_years(publications_id, year) VALUES (?, ?)";
        PreparedStatement ps_pe = con.prepareStatement(query_pe);
        ps_pe.setLong(1, p_id);
        ps_pe.setInt(2, Integer.parseInt(year));
        ps_pe.executeUpdate();
    }

    public String deleteUser(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN})) {
            return "access error";
        }
        return deleteById("users", id);
    }

    public String deletePublication(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("publications", id);
    }

    public String deleteAuthor(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("authors", id);
    }

    public String deleteEditor(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("editors", id);
    }

    public String deleteOrganization(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("organizations", id);
    }

    public String deletePublHouse(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("publishing_houses", id);
    }

    public String deleteKeyword(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("keywords", id);
    }

    public String deleteUdc(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("udc_codes", id);
    }

    public String deleteMagazine(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("magazines", id);
    }

    public String deleteSubject(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("subjects", id);
    }

    public String deleteVerification(String u_id, String id) {
        if (!checkType(u_id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("verifications", id);
    }

    public String deleteKeywordFromPubl(String id, String p_id, String k_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "DELETE FROM publications_keywords WHERE publications_id = ? AND keywords_id = ?";
        return doSmthToPubl(query, p_id, k_id);
    }

    public String deleteUdcFromPubl(String id, String p_id, String u_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "DELETE FROM publications_udc_codes WHERE publications_id = ? AND udc_codes_id = ?";
        return doSmthToPubl(query, p_id, u_id);
    }

    public String deleteAuthFromPubl(String id, String p_id, String a_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "DELETE FROM authors_publications WHERE publications_id = ? AND authors_id = ?";
        return doSmthToPubl(query, p_id, a_id);
    }

    public String deleteEditorFromPubl(String id, String p_id, String e_id) {
        if (!checkType(id, new int[] {U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "DELETE FROM editors_publications WHERE publications_id = ? AND editors_id = ?";
        return doSmthToPubl(query, p_id, e_id);
    }

    private String deleteById(String table, String id) {
        try {
            String query = "DELETE FROM " + table + " WHERE " + table + "_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(id));
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    public String changeUser(String u_id, String changing_u_id, String name, String surname, String patronymic,
                             String sex, String birth_date, String phone_number, String email) {
        if (!(checkType(u_id, new int[] {U_ADMIN}) || u_id.equals(changing_u_id))) {
            return "access error";
        }
        try {
            String query =
                    "UPDATE users " +
                    "SET name = ?, surname = ?, patronymic = ?, sex = ?, birth_date = ?, phone_number = ?, email = ? " +
                    "WHERE users_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            setFullNameAndSexInPs(ps, name, surname, patronymic, sex);
            setDateInPs(ps, birth_date, 5);
            if (phone_number.equals("NULL")) {
                ps.setNull(6, Types.BIGINT);
            } else {
                ps.setLong(6, Long.parseLong(phone_number));
            }
            if (email.equals("NULL")) {
                ps.setNull(7, Types.VARCHAR);
            } else {
                ps.setString(7, email);
            }
            ps.setLong(8, Long.parseLong(changing_u_id));
            ps.executeUpdate();
            return "ok";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    private void setDateInPs(PreparedStatement ps, String date, int index) throws Exception {
        if (date.equals("NULL")) {
            ps.setNull(index, Types.DATE);
        } else {
            ps.setDate(index, java.sql.Date.valueOf(date));
        }
    }

    private void setPhoneAndEmailInPs(PreparedStatement ps, String phone, String email, int start_index) throws Exception {
        if (phone.equals("NULL")) {
            ps.setNull(start_index, Types.BIGINT);
        } else {
            ps.setLong(start_index, Long.parseLong(phone));
        }
        if (email.equals("NULL")) {
            ps.setNull(start_index + 1, Types.VARCHAR);
        } else {
            ps.setString(start_index + 1, email);
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
