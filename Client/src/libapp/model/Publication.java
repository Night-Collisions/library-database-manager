package libapp.model;

public class Publication extends PublicationTable {
    private String type;

    public Publication(String id, String type, String name) {
        InitColumn(id, name);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
