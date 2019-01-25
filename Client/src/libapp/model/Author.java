package libapp.model;

public class Author extends Table {
    private String id;
    private String surname;
    private String name;
    private String patronymic;
    private String sex;
    private String birthday;
    private String deathday;
    private String phonenumber;
    private String email;

    public Author(String id, String surname, String name, String patronymic, String sex,
                  String birthday, String deathday, String phonenumber,
                  String email)
    {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.sex = sex;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.deathday = deathday;
        this.phonenumber = phonenumber;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public String getSex() {
        return this.sex;
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

    public String getBirthday() {
        return this.birthday;
    }

    public String getDeathday() {
        return this.deathday;
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

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
