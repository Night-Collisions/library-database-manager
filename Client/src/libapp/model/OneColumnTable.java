package libapp.model;

public class OneColumnTable extends Table{
    private String data;

    public OneColumnTable(String id, String code) {
        this.id = id;
        this.data = code;
    }

    public String getCode() {
        return data;
    }

    public void setCode(String data) {
        this.data = data;
    }
}
