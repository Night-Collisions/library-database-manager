package libapp.model;

public class UDC extends Table{
    private String code;

    public UDC(String id, String code) {
        this.id = id;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
