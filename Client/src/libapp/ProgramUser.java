package libapp;

import java.util.HashSet;

public class ProgramUser {
    public enum UserType{Undefined, Reader, Author, PublishingHouse, Librarian, Admin}

    String id;
    String name;
    UserType type;
    HashSet<String> publicationsID;

    public ProgramUser(String id, String name, UserType type, HashSet<String> publicationsID) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.publicationsID = publicationsID;
    }

    public String getId() {
        return id;
    }
    public UserType getType() {
        return type;
    };
    public HashSet<String> getPublication() {
        return publicationsID;
    }
}
