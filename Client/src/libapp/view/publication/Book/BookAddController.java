package libapp.view.publication.Book;

import javafx.fxml.FXML;

import javafx.scene.control.*;

public class BookAddController {

    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> ph;
    @FXML
    private DatePicker date;

    @FXML
    private void initialize() {
        name.setText("Хуйя создалась");
    }
}
