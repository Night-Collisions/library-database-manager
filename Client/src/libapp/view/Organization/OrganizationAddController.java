package libapp.view.Organization;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libapp.view.PhoneField;

public class OrganizationAddController {

    @FXML
    private TextField name;
    @FXML
    private TextField address;
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
    private void initialize() {
        name.setText("Создал");
        PhoneField.setPhoneField(phone);
    }

    @FXML
    private int closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }

}
