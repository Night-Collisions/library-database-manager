package libapp.model;

public class OneColumnTable extends Table{
    private String id;
    private String data;

    public OneColumnTable(String id, String code) {
        this.id = id;
        this.data = code;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }
}
