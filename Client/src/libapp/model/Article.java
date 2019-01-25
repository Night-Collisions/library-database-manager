package libapp.model;

public class Article extends PublicationTable {
    private String magazineOrWork;
    private String volume;
    private String number;

    public Article(String id, String name, String magazineOrWork,
                   String volume, String number)
    {
        InitColumn(id, name);
        this.volume = volume;
        this.magazineOrWork = magazineOrWork;
        this.number = number;
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
