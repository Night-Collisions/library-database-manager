package libapp.view.publication.Work;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import libapp.ClientSocket;
import libapp.model.PublishingHouse;
import libapp.view.Main;
import libapp.view.PropertyWin;

public class WorkWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected ComboBox<PublishingHouse> ph;
    @FXML
    protected DatePicker date;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;

    protected Main main;
    protected ClientSocket socket;

    public WorkWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

}
