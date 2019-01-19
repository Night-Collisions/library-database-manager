package libapp.view.publication.Book;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import libapp.ClientSocket;
import libapp.model.PublishingHouse;
import libapp.view.Main;
import libapp.view.PropertyWin;

public class BookWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected ComboBox<PublishingHouse> ph;
    @FXML
    protected TextField date;

    protected Main main;
    protected ClientSocket socket;

    public BookWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

    @FXML
    protected void initialize() {
    }
}
