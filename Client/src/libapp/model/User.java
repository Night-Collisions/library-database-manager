package libapp.model;

public class User extends Table {
    private String surname;
    private String name;
    private String patronymic;
    private String sex;
    private String login;
    private String birthday;
    private String type;
    private String phonenumber;
    private String email;

    public User(String id, String surname, String name, String patronymic,
                String sex, String login, String birthday, String type,
                String phonenumber, String email)
    {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.sex = sex;
        this.login = login;
        this.birthday = birthday;
        this.type = type;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public String getSex() {
        return this.sex;
    }

    public String getLogin() {
        return this.login;
    }

    public String getType() {
        return this.type;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
