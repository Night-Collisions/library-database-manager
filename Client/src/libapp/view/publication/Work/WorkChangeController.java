package libapp.view.publication.Work;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class WorkChangeController {
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> ph;
    @FXML
    private DatePicker date;

    @FXML
    private void initialize() {
        name.setText("Изменилась");
    }
}
