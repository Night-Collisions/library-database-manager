package libapp.model;

public class Thesis {
    private String id;
    private String name;
    private String magazineOrWork;

    public Thesis(String id, String name, String magazineOrWork) {
        this.id = id;
        this.name = name;
        this.magazineOrWork = magazineOrWork;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getMagazineOrWork() {
        return this.magazineOrWork;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMagazineOrWork(String name) {
        this.magazineOrWork = magazineOrWork;
    }
}
