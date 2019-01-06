package libapp.model;

public class Notification {
    String userId;
    String surname;
    String name;
    String login;
    String potentialType;

    public Notification(String userId, String surname, String name,
                        String login, String potentialType)
    {
        this.userId = userId;
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.potentialType = potentialType;
    }

    public String getUserId() {
        return userId;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPotentialType() {
        return potentialType;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setName(String name) {
        this.name = name;
    }

    void setLogin(String login) {
        this.login = login;
    }

    void setPotentialType(String potentialType) {
        this.potentialType = potentialType;
    }
}
