package libapp.view.publishingHouse;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import libapp.view.PropertyWin;
import libapp.view.RegularForField;

public class PublishingHouseWinController extends PropertyWin {
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

    @FXML
    protected void initialize() {
        RegularForField.setPhoneField(phone);
    }
}
