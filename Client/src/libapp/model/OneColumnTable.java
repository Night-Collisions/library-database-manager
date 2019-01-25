package libapp.model;

public class OneColumnTable extends Table{
    private String data;

    public OneColumnTable(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
