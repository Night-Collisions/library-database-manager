package libapp.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libapp.view.RegularForField;

import java.util.Optional;

public class UserChangeController {

    @FXML
    private Label id;
    @FXML
    private Label login;
    @FXML
    private Button changePassword;
    @FXML
    private Label type;
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
        RegularForField.setPhoneField(phone);
    }

    @FXML
    int changePassword(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Изменить пароль");
        dialog.setHeaderText(null);
        dialog.setContentText("Новый пароль:");

        RegularForField.setPasswordField(dialog.getEditor());

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }
        return 0;
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
