package libapp.model;

public class Notification extends Table {
    private String userId;
    private String login;
    private String phonenumber;
    private String email;
    private String authorPrefID;
    private String phPrefID;

    public Notification(String id, String userId, String login,
                        String phonenumber, String email,
                        String authorPrefID, String phPrefID)
    {
        this.id = id;
        this.userId = userId;
        this.login = login;
        this.phonenumber = phonenumber;
        this.email = email;
        this.authorPrefID = authorPrefID;
        this.phPrefID = phPrefID;
    }

    public String getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthorPrefID() {
        return authorPrefID;
    }

    public String getPhPrefID() {
        return phPrefID;
    }

    void setUserId(String userId) {
        this.userId = userId;
    }

    void setLogin(String login) {
        this.login = login;
    }

    void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    void setEmail(String email) {
        this.email = email;
    }

    void setAuthorPrefID(String authorPrefID) {
        this.authorPrefID = authorPrefID;
    }

    void setPhPrefID(String phPrefID) {
        this.phPrefID = phPrefID;
    }
}
