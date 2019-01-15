package libapp.view.publication.Book;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.stage.Stage;

public class BookAddController {

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
        name.setText("Хуйя создалась");
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
