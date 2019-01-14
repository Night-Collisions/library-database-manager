package libapp.view.magazine;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MagazineChangeController {

    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> subject;
    @FXML
    private ComboBox<String> organization;
    @FXML
    private Button accept;
    @FXML
    private Button reject;

    @FXML
    private void initialize() {
        name.setText("редактируется");
    }

    @FXML
    private int closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }

}
