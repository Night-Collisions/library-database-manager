package libapp.view.organization;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.PropertyWin;
import libapp.view.RegularForField;

public class OrganizationWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected TextField address;
    @FXML
    protected DatePicker bornDate;
    @FXML
    protected TextField phone;
    @FXML
    protected TextField email;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;

    protected Main main;
    protected ClientSocket socket;

    public OrganizationWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

    @FXML
    protected void initialize() {
        RegularForField.setPhoneField(phone);
    }
}
