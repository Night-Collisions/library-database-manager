package libapp.view.publication.TechnicalDoc;

import libapp.view.PropertyWin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TechnicalDocWinController extends PropertyWin {    
    @FXML
    protected TextField name;
    @FXML
    protected ComboBox<String> organization;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;
}
