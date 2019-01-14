package libapp.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libapp.view.RegularForField;

import java.util.Optional;

public class UserAddController {

    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField patronymic;
    @FXML
    private ComboBox<String> sex;
    @FXML
    private DatePicker bornDate;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private Button accept;
    @FXML
    private Button reject;

    @FXML
    private void initialize() throws InterruptedException {
        RegularForField.setLoginField(login);
        RegularForField.setPhoneField(phone);
        RegularForField.setPasswordField(password);
    }

    @FXML
    private int applyChange() {

        return 0;
    }

    @FXML
    private int closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }

}
