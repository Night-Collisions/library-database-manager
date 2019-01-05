package libapp.model;

public class TechnicalDoc {
    private String id;
    private String name;
    private String organization;

    public TechnicalDoc(String id, String name, String organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
