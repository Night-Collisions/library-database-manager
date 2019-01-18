package libapp.model;

public class Notification extends Table {
    private String userId;
    private String login;
    private String phonenumber;
    private String email;
    private String type;
    private String prefID;

    public Notification(String id, String userId, String login,
                        String phonenumber, String email,
                        String authorPrefID, String prefID)
    {
        this.id = id;
        this.userId = userId;
        this.login = login;
        this.phonenumber = phonenumber;
        this.email = email;
        this.type = authorPrefID;
        this.prefID = prefID;
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

    public String getType() {
        return type;
    }

    public String getPrefID() {
        return prefID;
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

    void setType(String authorPrefID) {
        this.type = authorPrefID;
    }

    void setPrefID(String prefID) {
        this.prefID = prefID;
    }
}
