package libapp.model;

public class TechnicalDoc extends PublicationTable {

    private String organization;

    public TechnicalDoc(String id, String name, String organization) {
        InitColumn(id, name);
        this.organization = organization;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
