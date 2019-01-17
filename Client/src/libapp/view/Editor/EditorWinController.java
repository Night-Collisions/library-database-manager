package libapp.view.Editor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.PropertyWin;
import libapp.view.RegularForField;

import java.util.HashMap;
import java.util.Map;

public class EditorWinController extends PropertyWin {
    @FXML
    protected TextField surname;
    @FXML
    protected TextField name;
    @FXML
    protected TextField patronymic;
    @FXML
    protected ComboBox<String> sex;
    @FXML
    protected DatePicker bornDate;
    @FXML
    protected DatePicker deathDate;
    @FXML
    protected TextField phone;
    @FXML
    protected TextField email;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;

    protected Map<String,String> sexDict = new HashMap<String,String>();

    protected Main main;
    protected ClientSocket socket;

    public EditorWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

    @FXML
    protected void initialize() {
        RegularForField.setPhoneField(phone);
    }
}
