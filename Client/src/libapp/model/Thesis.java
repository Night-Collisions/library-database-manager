package libapp.model;

public class Thesis extends PublicationTable {
    private String magazineOrWork;

    public Thesis(String id, String name, String magazineOrWork) {
        InitColumn(id, name);
        this.magazineOrWork = magazineOrWork;
    }

    public String getMagazineOrWork() {
        return this.magazineOrWork;
    }

    public void setMagazineOrWork(String name) {
        this.magazineOrWork = magazineOrWork;
    }
}
