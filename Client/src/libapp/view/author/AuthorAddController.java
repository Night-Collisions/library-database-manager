package libapp.view.author;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libapp.view.PhoneField;

public class AuthorAddController {

    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField patronymic;
    @FXML
    private DatePicker bornDate;
    @FXML
    private DatePicker deathDate;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private Button accept;
    @FXML
    private Button reject;

    @FXML
    private void initialize() {
        name.setText("создалась");
        PhoneField.setPhoneField(phone);
    }

    @FXML
    private int closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }

}
