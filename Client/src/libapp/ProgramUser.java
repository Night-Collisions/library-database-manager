package libapp;

public class ProgramUser {
    public enum UserType{Undefined, Reader, Author, PublishingHouse, Librarian, Admin}

    String id;
    String name;
    UserType type;

    public ProgramUser(String id, String name, UserType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }
}
