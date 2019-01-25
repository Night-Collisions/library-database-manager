package libapp.view.user;

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

public class UserWinController extends PropertyWin {
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
    protected TextField phone;
    @FXML
    protected TextField email;

    protected String ID;

    protected ClientSocket socket;
    protected Main main;

    public UserWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

    protected Map<String,String> sexDict = new HashMap<String,String>();
    protected Map<String,String> typeDict = new HashMap<String,String>();
}
