package libapp;

public class ProgramUser {
    enum UserType{Undefined, Reader, Author, PublishingHouse, Librarian, Admin}

    String id;
    String name;

    public ProgramUser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
