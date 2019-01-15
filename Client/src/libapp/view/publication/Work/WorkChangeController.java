package libapp.view.publication.Work;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WorkChangeController {
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> ph;
    @FXML
    private DatePicker date;
    @FXML
    private Button accept;
    @FXML
    private Button reject;

    @FXML
    private void initialize() {
        name.setText("Изменилась");
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
