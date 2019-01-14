package libapp.model;

public class Magazine extends Table {
    private String name;
    private String topic;

    public Magazine(String id, String name, String topic) {
        this.id = id;
        this.name = name;
        this.topic = topic;
    }

    public String getName() {
        return this.name;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
