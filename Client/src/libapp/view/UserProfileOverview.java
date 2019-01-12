package libapp.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserProfileOverview {

    @FXML
    private Label id;
    @FXML
    private Label login;
    @FXML
    private Label type;
    @FXML
    private Label surname;
    @FXML
    private Label name;
    @FXML
    private Label patronymic;
    @FXML
    private Label sex;
    @FXML
    private Label bornDate;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private Button apply;

    @FXML
    private void initialize() throws InterruptedException {
        sex.setText("Хуй есть");
        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                if ((!newValue.matches("[0-9]*")) || (newValue.length() > 11)) {
                    phone.setText(oldValue);
                }
            }
        });
    }

    @FXML
    private int applyChange() {

        return 0;
    }
}
