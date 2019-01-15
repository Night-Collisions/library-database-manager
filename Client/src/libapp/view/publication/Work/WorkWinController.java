package libapp.view.publication.Work;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import libapp.view.PropertyWin;

public class WorkWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected ComboBox<String> ph;
    @FXML
    protected DatePicker date;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;

}
