package libapp.view.author;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import libapp.view.PropertyWin;
import libapp.view.RegularForField;

public class AuthorWinController extends PropertyWin {
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

    @FXML
    protected void initialize() {
        RegularForField.setPhoneField(phone);
    }
}
