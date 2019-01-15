package libapp.view.publication.Work;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WorkAddController extends WorkWinController {
    protected void initialize() {
        super.initialize();
        name.setText("add");
    }

    protected void applyChange() {
        super.applyChange();
    }
}
