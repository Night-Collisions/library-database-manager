package libapp.view.publication.Book;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import libapp.view.PropertyWin;

public class BookWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected ComboBox<String> publishingHouses;
    @FXML
    protected DatePicker date;

    @FXML
    protected void initialize() {
    }
}
