package libapp.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libapp.Specification;

import java.util.Optional;

public class UserProfileOverview {

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
        PhoneField.setPhoneField(phone);
    }

    @FXML
    int changePassword(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Изменить пароль");
        dialog.setHeaderText(null);
        dialog.setContentText("Новый пароль:");

        TextField pass = dialog.getEditor();
        pass.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if ((!newValue.matches(Specification.passwordReg))) {
                    pass.setText(oldValue);
                }
            }
        });

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
