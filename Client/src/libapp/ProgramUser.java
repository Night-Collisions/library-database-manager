package libapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ProgramUser {
    public enum UserType{Undefined, Admin, Librarian, PublishingHouse, Author, Reader}

    public static final Map<UserType,String> UserTypeToStr = new HashMap<UserType,String>();
    static {
        UserTypeToStr.put(UserType.Undefined, "Неопределёный");
        UserTypeToStr.put(UserType.Admin, "Администратор");
        UserTypeToStr.put(UserType.Librarian, "Библиотекарь");
        UserTypeToStr.put(UserType.PublishingHouse, "Издательство");
        UserTypeToStr.put(UserType.Author, "Автор");
        UserTypeToStr.put(UserType.Reader, "Читатель");
    }

    String id;
    String login;
    UserType type;
    String name;
    String surname;
    String patronymic;
    HashSet<String> publicationsID;
    boolean isWoman;
    String phone_number;
    String email;

    public ProgramUser(String id, String login, UserType type, String name, String surname, String patronymic,
                       HashSet<String> publicationsID, boolean isWoman, String phone_number, String email) {
        this.id = id;
        this.login = login;
        this.type = type;
        this.publicationsID = publicationsID;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.isWoman = isWoman;
        this.phone_number = phone_number;
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public UserType getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPatromic() {
        return patronymic;
    }
    public HashSet<String> getPublication() {
        return publicationsID;
    }
    public boolean isWoman() {
        return isWoman;
    }
    public String getPhone() {
        return phone_number;
    }
    public String getEmail() {
        return email;
    }

    public void setID(String id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setType(UserType type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setIsWoman(Boolean isWoman) {
        this.isWoman = isWoman;
    }
    public void setPublicationsID(HashSet<String> publicationsID) {
        this.publicationsID = publicationsID;
    }
    public void setPhone(String phone) {
        this.phone_number = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public static UserType int2UserType(int i) {
        return UserType.values()[i + 1];
    }
    public boolean haveContacts() {
        return !(phone_number.equals("")) || !(email.equals(""));
    }
}
