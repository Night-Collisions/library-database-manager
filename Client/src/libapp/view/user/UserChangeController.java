package libapp.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import libapp.view.RegularForField;

import java.util.Optional;

public class UserChangeController extends UserWinController {

    @FXML
    protected Label id;
    @FXML
    protected Label login;
    @FXML
    protected Button changePassword;
    @FXML
    protected Label type;
    
    @FXML
    protected void initialize() {
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
    protected void applyChange() {
        super.applyChange();
    }
}
