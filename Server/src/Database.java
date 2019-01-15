import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;

class Database {

    private Connection con;

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

    Database() throws Exception {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library_db", "ivan", "password");
    }

    String authorizeUser(String login, String password) {
        String s =
                "SELECT users_id, name, surname, patronymic, sex, login, birth_date, type, phone_number, email " +
                "FROM users WHERE login = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(s);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            String[] user = {"-1", "", "", "", "", "", "", "", "", ""};
            if (rs.next()) {
                int i = 1;
                while (i <= columnCount) {
                    user[i - 1] = rs.getString(i);
                    ++i;
                }
            }
            return new Gson().toJson(user);

        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    String getPublsOfUser(String caller_id) {
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
            ps.setLong(1, Long.parseLong(caller_id));
            ps.setLong(2, Long.parseLong(caller_id));
            ResultSet rs = ps.executeQuery();
            return RSToString(rs);
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    String getUsers(String caller_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN})) {
            return "access error";
        }
        return execSelectQuery(
                "SELECT u.users_id, u.surname, u.name, u.patronymic, u.sex, u.login, " + 
                "u.birth_date, u.type, u.phone_number, u.email " +
                "FROM users u");
    }

    String getPublications() {
        return execSelectQuery("SELECT * FROM publications");
    }

    String getBooks() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, ph.title, py.year " +
                "FROM publications p " +
                "JOIN publishing_houses_publications php on p.publications_id = php.publications_id " +
                "JOIN publishing_houses ph ON php.publishing_houses_id = ph.publishing_houses_id " +
                "JOIN publishing_years py on p.publications_id = py.publications_id " +
                "WHERE p.type = " + P_BOOK
        );
    }

    String getDigests() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, ph.title, py.year " +
                "FROM publications p " +
                "JOIN publishing_houses_publications php on p.publications_id = php.publications_id " +
                "JOIN publishing_houses ph ON php.publishing_houses_id = ph.publishing_houses_id " +
                "JOIN publishing_years py on p.publications_id = py.publications_id " +
                "WHERE p.type = " + P_DIGEST
        );
    }

    String getArticles() {
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

    String getTheses() {
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

    String getDocs() {
        return execSelectQuery(
                "SELECT p.publications_id, p.title, o.title " +
                "FROM publications p " +
                "JOIN organizations_publications op on p.publications_id = op.publications_id " +
                "JOIN organizations o on op.organizations_id = o.organizations_id " +
                "WHERE p.type = " + P_DOCS
        );
    }

    String getSubjects() {
        return execSelectQuery(
                "SELECT * FROM subjects"
        );
    }

    String getMagazines() {
        return execSelectQuery(
                "SELECT m.magazines_id, m.title, s.title " +
                "FROM magazines m " +
                "JOIN subjects s on m.subjects_id = s.subjects_id"
        );
    }

    String getAuthors() {
        return execSelectQuery(
                "SELECT * FROM authors"
        );
    }

    String getEditors() {
        return execSelectQuery(
                "SELECT * FROM editors"
        );
    }

    String getOrganizations() {
        return execSelectQuery(
                "SELECT * FROM organizations"
        );
    }

    String getPublHouses() {
        return execSelectQuery(
                "SELECT * FROM publishing_houses"
        );
    }

    String getKeywords() {
        return execSelectQuery(
                "SELECT * FROM keywords"
        );
    }

    String getUdc() {
        return execSelectQuery(
                "SELECT * FROM udc_codes"
        );
    }

    String getVerfs(String caller_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return execSelectQuery(
                "SELECT v.verifications_id, v.users_id, u.login, u.phone_number, u.email, v.to_type, v.authors_id, " +
                "v.publishing_houses_id " +
                "FROM verifications v " +
                "JOIN users u on v.users_id = u.users_id"
        );
    }

    String getAuthorsWorktimes(String a_id) {
        String query =
                "SELECT ao.authors_organizations_id, o.title, ao.start, ao.finish " +
                "FROM authors_organizations ao " +
                "JOIN authors a on ao.authors_id = ? " +
                "JOIN organizations o on ao.organizations_id = o.organizations_id";
        return execPSWithId(a_id, query);
    }

    String getAuthorsOfPubl(String p_id) {
        String query =
                "SELECT a.authors_id, a.name, a.surname, a.patronymic, a.sex, a.birth_date, " +
                "a.death_date, a.phone_number, a.email " +
                "FROM authors a " +
                "JOIN authors_publications ap ON a.authors_id = ap.authors_id" +
                "AND ap.publications_id = ?";
        return execPSWithId(p_id, query);
    }

    String getEditorsOfPubl(String p_id) {
        String query =
                "SELECT e.editors_id, e.name, e.surname, e.patronymic, e.sex, e.birth_date, " +
                "e.death_date, e.phone_number, e.email " +
                "FROM editors e " +
                "JOIN editors_publications ep ON e.editors_id = ep.editors_id " +
                "AND ep.publications_id = ?";
        return execPSWithId(p_id, query);
    }

    String getKeywordsOfPubl(String id) {
        String s =
                "SELECT k.keywords_id, k.keyword " +
                "FROM keywords k " +
                "JOIN publications_keywords pk on k.keywords_id = pk.keywords_id " +
                "JOIN publications p on pk.publications_id = p.publications_id " +
                "WHERE p.publications_id = ?";
        return execPSWithId(id, s);
    }

    String getUdcOfPubl(String id) {
        String s =
                "SELECT uc.udc_codes_id, uc.udc_code " +
                "FROM udc_codes uc " +
                "JOIN publications_udc_codes puc ON uc.udc_codes_id = puc.udc_codes_id " +
                "JOIN publications p on puc.publications_id = p.publications_id " +
                "WHERE p.publications_id = ?";
        return execPSWithId(id, s);
    }

    String addUser(String caller_id, String name, String surname, String patronymic, String sex, String login,
                   String password, String birth_date, String type, String phone_number, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN})) {
            return "access error";
        }
        try {
            String query =
                    "INSERT INTO users(name, surname, patronymic, sex, login, password, birth_date, type, " +
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
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addBook(String caller_id, String title, String ph_id, String year) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return addBookAndDigestCommonPart(title, ph_id, year, P_BOOK);
    }

    String addDigest(String caller_id, String title, String ph_id, String year) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return addBookAndDigestCommonPart(title, ph_id, year, P_DIGEST);
    }

    private String addBookAndDigestCommonPart(String title, String ph_id, String year, int type) {
        try {
            String query_p =
                    "INSERT INTO publications(type, title) VALUES (" + type + ", ?) " +
                    "RETURNING publications_id";
            addPublHouseAndYear(title, ph_id, year, query_p);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addMagazineArticle(String caller_id, String title, String m_id, String volume, String relase_number) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query_p =
                    "INSERT INTO publications(type, title) VALUES (" + P_ARTICLE + ", ?) " +
                    "RETURNING publications_id";
            long p_id = getPublIdAfterInsert(title, query_p);

            addMagazinePublication(p_id, m_id);

            String query_mai =
                    "INSERT INTO magazine_article_info(publications_id, volume, release_number) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement ps_mai = con.prepareStatement(query_mai);
            ps_mai.setLong(1, p_id);
            ps_mai.setLong(2, Integer.parseInt(volume));
            ps_mai.setLong(3, Integer.parseInt(relase_number));
            ps_mai.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addDigestArticle(String caller_id, String title, String d_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return addPublicationWithDigest(P_ARTICLE, title, d_id);
    }

    String addMagazineTheses(String caller_id, String title, String m_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query_p =
                    "INSERT INTO publications(type, title) VALUES (" + P_THESES + ", ?) " +
                    "RETURNING publications_id";
            long p_id = getPublIdAfterInsert(title, query_p);
            addMagazinePublication(p_id, m_id);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addDigestTheses(String caller_id, String title, String d_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return addPublicationWithDigest(P_THESES, title, d_id);
    }

    String addDocs(String caller_id, String title, String o_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query_p =
                    "INSERT INTO publications(type, title) VALUES (" + P_DOCS + ", ?) " +
                    "RETURNING publications_id";
            long p_id = getPublIdAfterInsert(title, query_p);
            String query_op = "INSERT INTO organizations_publications(publications_id, organizations_id) VALUES (?, ?)";
            PreparedStatement ps_op = con.prepareStatement(query_op);
            ps_op.setLong(1, p_id);
            ps_op.setInt(2, Integer.parseInt(o_id));
            ps_op.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String addPublicationWithDigest(int type, String title, String d_id) {
        try {
            String query_p =
                    "INSERT INTO publications(type, title) VALUES (" + type + ", ?) " +
                            "RETURNING publications_id";
            long p_id = getPublIdAfterInsert(title, query_p);
            addDigestPublication(p_id, d_id);
            return "ok";
        } catch (Exception e) {
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

    String addSubject(String caller_id, String subject) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query = "INSERT INTO subjects(title) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, subject);
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addMagazine(String caller_id, String title, String s_id, String o_id) {
        if (!checkUserType(
                caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query = "INSERT INTO magazines(title, subjects_id, organizations_id) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setLong(2, Long.parseLong(s_id));
            ps.setLong(3, Long.parseLong(o_id));
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addAuthor(String caller_id, String name, String surname, String patronymic, String sex, String birth_date,
                     String death_date, String phone_number, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query =
                "INSERT INTO authors(name, surname, patronymic, sex, birth_date, death_date, phone_number, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return addAuthorAndEditorCommonPart(query, name, surname, patronymic, sex, birth_date, death_date, phone_number,
                email);
    }

    String addEditor(String caller_id, String name, String surname, String patronymic, String sex, String birth_date,
                     String death_date, String phone_number, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query =
                "INSERT INTO editors(name, surname, patronymic, sex, birth_date, death_date, phone_number, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return addAuthorAndEditorCommonPart(query, name, surname, patronymic, sex, birth_date, death_date, phone_number,
                email);
    }

    String addAuthorAndEditorCommonPart(String query, String name, String surname, String patronymic, String sex,
                                        String birth_date, String death_date, String phone_number, String email) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            setAuthOrEditorInfoInPS(ps, name, surname, patronymic, sex, birth_date, death_date, phone_number, email);
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addOrganization(String caller_id, String title, String address, String phone, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO organizations(title, legal_address, phone_number, email) VALUES (?, ?, ?, ?)";
        return addOrgAndPublHouseCommonPart(query, title, address, phone, email);
    }

    String addPublHouse(String caller_id, String title, String address, String phone, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO publishing_houses(title, legal_address, phone_number, email) VALUES (?, ?, ?, ?)";
        return addOrgAndPublHouseCommonPart(query, title, address, phone, email);
    }

    private String addOrgAndPublHouseCommonPart(String query, String title, String address, String phone,
                                                String email) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            setOrgOrPublHouseInfoInPS(ps, title, address, phone, email);
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addKeyword(String caller_id, String keyword) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query = "INSERT INTO keywords(keyword) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, keyword);
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addUdc(String caller_id, String udc) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query = "INSERT INTO udc_codes(udc_code) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, udc);
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addVerf(String caller_id, String to_type, String a_id, String ph_id) {
        if (!checkUserType(caller_id, new int[]{U_READER})) {
            return "access error";
        }
        try {
            String query =
                    "INSERT INTO verifications(users_id, to_type, authors_id, publishing_houses_id) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(caller_id));
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
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addAuthorWorktime(String caller_id, String a_id, String o_id, String start, String finish) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query =
                    "INSERT INTO authors_organizations(authors_id, organizations_id, start, finish) VALUES " +
                    "(?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(a_id));
            ps.setLong(2, Long.parseLong(o_id));
            ps.setDate(3, java.sql.Date.valueOf(start));
            ps.setDate(4, java.sql.Date.valueOf(finish));
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String addAuthorToPubl(String caller_id, String p_id, String a_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO authors_publications(publications_id, authors_id) VALUES (?, ?)";
        return execQueryWithTwoIds(query, p_id, a_id);
    }

    String addEditorToPubl(String caller_id, String p_id, String e_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO editors_publications(publications_id, editors_id) VALUES (?, ?)";
        return execQueryWithTwoIds(query, p_id, e_id);
    }

    String addKeywordToPubl(String caller_id, String p_id, String k_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO publications_keywords(publications_id, keywords_id) VALUES (?, ?)";
        return execQueryWithTwoIds(query, p_id, k_id);
    }

    String addUdcToPubl(String caller_id, String p_id, String u_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO publications_udc_codes(publications_id, udc_codes_id) VALUES (?, ?)";
        return execQueryWithTwoIds(query, p_id, u_id);
    }

    String addUserToAuthor(String caller_id, String u_id, String a_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO users_authors(users_id, authors_id) VALUES (?, ?)";
        return execQueryWithTwoIds(query, u_id, a_id);
    }

    String addUserToPublHouse(String caller_id, String u_id, String ph_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "INSERT INTO users_publishing_houses(users_id, publishing_houses_id) VALUES (?, ?)";
        return execQueryWithTwoIds(query, u_id, ph_id);
    }

    String deleteUser(String caller_id, String u_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN})) {
            return "access error";
        }
        return deleteById("users", u_id);
    }

    String deletePublication(String caller_id, String p_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("publications", p_id);
    }

    String deleteSubject(String caller_id, String s_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("subjects", s_id);
    }

    String deleteMagazine(String caller_id, String m_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("magazines", m_id);
    }

    String deleteAuthor(String caller_id, String a_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("authors", a_id);
    }

    String deleteEditor(String caller_id, String e_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("editors", e_id);
    }

    String deleteOrganization(String caller_id, String o_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("organizations", o_id);
    }

    String deletePublHouse(String caller_id, String ph_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("publishing_houses", ph_id);
    }

    String deleteKeyword(String caller_id, String k_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("keywords", k_id);
    }

    String deleteUdc(String caller_id, String u_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("udc_codes", u_id);
    }

    String deleteVerf(String caller_id, String v_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("verifications", v_id);
    }

    String deleteAuthorWorktime(String caller_id, String ao_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        return deleteById("authors_organizations", ao_id);
    }

    private String deleteById(String table, String id) {
        try {
            String query = "DELETE FROM " + table + " WHERE " + table + "_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(id));
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String deleteAuthorFromPubl(String caller_id, String p_id, String a_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "DELETE FROM authors_publications WHERE publications_id = ? AND authors_id = ?";
        return execQueryWithTwoIds(query, p_id, a_id);
    }

    String deleteEditorFromPubl(String caller_id, String p_id, String e_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "DELETE FROM editors_publications WHERE publications_id = ? AND editors_id = ?";
        return execQueryWithTwoIds(query, p_id, e_id);
    }

    String deleteKeywordFromPubl(String caller_id, String p_id, String k_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "DELETE FROM publications_keywords WHERE publications_id = ? AND keywords_id = ?";
        return execQueryWithTwoIds(query, p_id, k_id);
    }

    String deleteUdcFromPubl(String caller_id, String p_id, String u_id) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query = "DELETE FROM publications_udc_codes WHERE publications_id = ? AND udc_codes_id = ?";
        return execQueryWithTwoIds(query, p_id, u_id);
    }

    String changeUserInfo(String caller_id, String u_id, String name, String surname, String patronymic,
                          String sex, String birth_date, String phone_number, String email) {
        if (!(checkUserType(caller_id, new int[]{U_ADMIN}) || caller_id.equals(u_id))) {
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
            ps.setLong(8, Long.parseLong(u_id));
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String changeUserPassword(String caller_id, String u_id, String password) {
        if (!(checkUserType(caller_id, new int[]{U_ADMIN}) || caller_id.equals(u_id))) {
            return "access error";
        }
        try {
            String query = "UPDATE users SET password = ? WHERE users_id = ?";
            changeStringById(query, password, u_id);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String changeBook(String caller_id, String p_id, String title, String year) {
        return changeBookAndDigestCommonPart(caller_id, p_id, title, year, P_BOOK);
    }

    String changeDigest(String caller_id, String p_id, String title, String year) {
        return changeBookAndDigestCommonPart(caller_id, p_id, title, year, P_DIGEST);
    }

    private String changeBookAndDigestCommonPart(String u_id, String p_id, String title, String year, int type) {
        try {
            String error = checkChangePublPermissions(u_id, p_id, type);
            if (!error.equals("ok")) {
                return error;
            }
            changePublTitle(p_id, title);
            changePublishingYear(p_id, year);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private void changePublishingYear(String p_id, String year) throws Exception {
        String query = "UPDATE publishing_years SET year = ? WHERE publications_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, Integer.parseInt(year));
        ps.setLong(2, Long.parseLong(p_id));
        ps.executeUpdate();
    }

    String changeArticle(String caller_id, String p_id, String title, String volume, String relase_number) {
        try {
            String error = checkChangePublPermissions(caller_id, p_id, P_ARTICLE);
            if (!error.equals("ok")) {
                return error;
            }
            changePublTitle(p_id, title);
            String query = "UPDATE magazine_article_info SET volume = ?, release_number = ? WHERE publications_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(volume));
            ps.setInt(2, Integer.parseInt(relase_number));
            ps.setLong(3, Long.parseLong(p_id));
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String changeTheses(String caller_id, String p_id, String title) {
        return changeThesesAndDocsCommonPart(caller_id, p_id, title, P_THESES);
    }

    String changeDocs(String caller_id, String p_id, String title) {
        return changeThesesAndDocsCommonPart(caller_id, p_id, title, P_DOCS);
    }

    private String changeThesesAndDocsCommonPart(String u_id, String p_id, String title, int type) {
        try {
            String error = checkChangePublPermissions(u_id, p_id, type);
            if (!error.equals("ok")) {
                return error;
            }
            changePublTitle(p_id, title);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String checkChangePublPermissions(String u_id, String p_id, int type) throws Exception {
        if (!(checkUserType(u_id, new int[]{U_ADMIN, U_LIBRARIAN}) || isUserOwner(u_id, p_id))) {
            return "access error";
        }
        if (!checkPublType(p_id, type)) {
            return "wrong publication type";
        }
        return "ok";
    }

    private void changePublTitle(String p_id, String title) throws Exception {
        String query = "UPDATE publications SET title = ? WHERE publications_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, title);
        ps.setLong(2, Long.parseLong(p_id));
        ps.executeUpdate();
    }

    String changeSubject(String caller_id, String s_id, String title) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query = "UPDATE subjects SET subject = ? WHERE subjects_id = ?";
            changeStringById(query, title, s_id);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String changeMagazine(String caller_id, String m_id, String title) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        try {
            String query = "UPDATE magazines SET title = ? WHERE magazines_id = ?";
            changeStringById(query, title, m_id);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String changeOrganization(String caller_id, String o_id, String title, String address, String phone, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query =
                "UPDATE organizations SET title = ?, legal_address = ?, phone_number = ?, email = ? " +
                "WHERE organizations_id = ?";
        return changeOrgOrPublHouseCommonPart(query, title, address, phone, email, o_id);
    }

    String changePublHouse(String caller_id, String ph_id, String title, String address, String phone, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query =
                "UPDATE publishing_houses SET title = ?, legal_address = ?, phone_number = ?, email = ? " +
                "WHERE organizations_id = ?";
        return changeOrgOrPublHouseCommonPart(query, title, address, phone, email, ph_id);
    }

    private String changeOrgOrPublHouseCommonPart(String query, String title, String address, String phone,
                                                  String email, String id) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            setOrgOrPublHouseInfoInPS(ps, title, address, phone, email);
            ps.setLong(4, Long.parseLong(id));
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    String changeAuthor(String caller_id, String a_id, String name, String surname, String patronymic,
                        String sex, String birth_date, String death_date, String phone_number, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query =
                "UPDATE authors SET name = ?, surname = ?, patronymic = ?, sex = ?, birth_date = ?, " +
                "death_date = ?, phone_number = ?, email = ? " +
                "WHERE authors_id = ?";
        return changeAuthorOrEditorCommonPart(query, name, surname, patronymic, sex, birth_date, death_date,
                phone_number, email, a_id);
    }

    String changeEditor(String caller_id, String e_id, String name, String surname, String patronymic, String sex,
                        String birth_date, String death_date, String phone_number, String email) {
        if (!checkUserType(caller_id, new int[]{U_ADMIN, U_LIBRARIAN})) {
            return "access error";
        }
        String query =
                "UPDATE editors SET name = ?, surname = ?, patronymic = ?, sex = ?, birth_date = ?, " +
                "death_date = ?, phone_number = ?, email = ? " +
                "WHERE editors_id = ?";
        return changeAuthorOrEditorCommonPart(query, name, surname, patronymic, sex, birth_date, death_date,
                phone_number, email, e_id);
    }

    private String changeAuthorOrEditorCommonPart(String query, String name, String surname, String patronymic,
                                                  String sex, String birth_date, String death_date, String phone_number,
                                                  String email, String id) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            setAuthOrEditorInfoInPS(ps, name, surname, patronymic, sex, birth_date, death_date, phone_number, email);
            ps.setLong(9, Long.parseLong(id));
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private void changeStringById(String query, String string, String id) throws Exception {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, string);
        ps.setLong(2, Long.parseLong(id));
        ps.executeUpdate();
    }

    private void setAuthOrEditorInfoInPS(PreparedStatement ps, String name, String surname, String patronymic,
                                         String sex, String birth_date, String death_date, String phone_number,
                                         String email) throws Exception {
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
    }

    private void setOrgOrPublHouseInfoInPS(PreparedStatement ps, String title, String address, String phone,
                                           String email) throws Exception {
        ps.setString(1, title);
        ps.setString(2, address);
        setPhoneAndEmailInPs(ps, phone, email, 3);
    }

    private void setDateInPs(PreparedStatement ps, String date, int index) throws Exception {
        if (date.equals("NULL")) {
            ps.setNull(index, Types.DATE);
        } else {
            ps.setDate(index, java.sql.Date.valueOf(date));
        }
    }

    private void setPhoneAndEmailInPs(PreparedStatement ps, String phone, String email,
                                      int start_index) throws Exception {
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

    private boolean isUserOwner(String u_id, String p_id) throws Exception {
        String query =
                "SELECT 1 " +
                "FROM publications p " +
                "LEFT JOIN authors_publications ap ON p.publications_id = ap.publications_id " +
                "LEFT JOIN users_authors ua ON ua.authors_id = ap.authors_id " +
                "LEFT JOIN publishing_houses_publications php ON p.publications_id = php.publications_id " +
                "LEFT JOIN users_publishing_houses uph ON uph.publishing_houses_id = php.publishing_houses_id " +
                "WHERE (ua.users_id = ? OR uph.users_id = ?) AND p.publications_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setLong(1, Long.parseLong(u_id));
        ps.setLong(2, Long.parseLong(u_id));
        ps.setLong(3, Long.parseLong(p_id));
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    private boolean checkUserType(String u_id, int[] types) {
        int user_type;
        try {
            user_type = getUserType(u_id);
        } catch (Exception e) {
            return false;
        }
        for (int type : types) {
            if (user_type == type) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPublType(String p_id, int type) throws Exception {
        String query = "SELECT 1 FROM publications WHERE publications_id = ? AND type = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setLong(1, Long.parseLong(p_id));
        ps.setInt(2, type);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    private String execSelectQuery(String query) {
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return RSToString(rs);
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private String execPSWithId(String id, String query) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(id));
            ResultSet rs = ps.executeQuery();
            return RSToString(rs);
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    private String execQueryWithTwoIds(String query, String id1, String id2) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, Long.parseLong(id1));
            ps.setLong(2, Long.parseLong(id2));
            ps.executeUpdate();
            return "ok";
        } catch (Exception e) {
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
