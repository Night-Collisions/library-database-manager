package libapp.model;

public class Work {
    private String id;
    private String name;
    private String publishingHouse;
    private String year;

    public Work(String id, String name, String publishigHouse, String year) {
        this.id = id;
        this.name = name;
        this.publishingHouse = publishigHouse;
        this.year = year;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPublishingHouse() {
        return this.publishingHouse;
    }

    public String getYear() {
        return this.year;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public void setYear(String publishingHouse) {
        this.year = year;
    }
}
