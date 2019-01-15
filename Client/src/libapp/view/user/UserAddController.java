package libapp.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import libapp.view.RegularForField;

public class UserAddController extends UserWinController {
    @FXML
    protected TextField login;
    @FXML
    protected TextField password;
    @FXML
    protected ComboBox<String> type;

    protected void initialize() {
        RegularForField.setLoginField(login);
        RegularForField.setPhoneField(phone);
        RegularForField.setPasswordField(password);
        name.setText("add");
    }

    protected void applyChange() {
        super.applyChange();
    }
}
