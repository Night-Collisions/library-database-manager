package libapp.model;

public class Article {
    private String id;
    private String name;
    private String magazineOrWork;
    private String volume;
    private String number;

    public Article(String id, String name, String magazineOrWork,
                   String volume, String number)
    {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.magazineOrWork = magazineOrWork;
        this.number = number;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getVolume() {
        return this.volume;
    }

    public String getMagazineOrWork() {
        return this.magazineOrWork;
    }

    public String getNumber() {
        return this.number;
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

    public void setVolume(String publishingHouse) {
        this.volume = volume;
    }

    public void setNumber(String UDC) {
        this.number = number;
    }
}
