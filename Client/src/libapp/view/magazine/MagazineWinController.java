package libapp.view.magazine;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import libapp.view.PropertyWin;

public class MagazineWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected ComboBox<String> subject;
    @FXML
    protected ComboBox<String> organization;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;
}
