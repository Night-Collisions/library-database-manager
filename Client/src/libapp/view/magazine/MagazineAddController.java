package libapp.view.magazine;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;

public class MagazineAddController {

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
        name.setText("Хуйя создалась");
    }

    @FXML
    private int closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }

}
