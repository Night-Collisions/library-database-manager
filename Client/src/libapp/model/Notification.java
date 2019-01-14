package libapp.model;

public class Notification extends Table {
    private String userId;
    private String login;
    private String potentialType;
    private String phonenumber;
    private String email;

    public Notification(String id, String userId, String login,
                        String potentialType, String phonenumber, String email)
    {
        this.id = id;
        this.userId = userId;
        this.login = login;
        this.potentialType = potentialType;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPotentialType() {
        return potentialType;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    void setUserId(String userId) {
        this.userId = userId;
    }

    void setLogin(String login) {
        this.login = login;
    }

    void setPotentialType(String potentialType) {
        this.potentialType = potentialType;
    }

    void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    void setEmail(String email) {
        this.email = email;
    }
}
