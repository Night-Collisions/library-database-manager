package libapp.model;

public class Work extends PublicationTable {
    private String publishingHouse;
    private String year;

    public Work(String id, String name, String publishigHouse, String year) {
        InitColumn(id, name);
        this.publishingHouse = publishigHouse;
        this.year = year;
    }

    public String getPublishingHouse() {
        return this.publishingHouse;
    }

    public String getYear() {
        return this.year;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public void setYear(String publishingHouse) {
        this.year = year;
    }
}
