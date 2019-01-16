package libapp.view.publication.oneColumnTable.Edithor;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

public class EditorsOCTController extends OneColumnTableController<OneColumnTable> {
    public EditorsOCTController(Main main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
        columnName = "Редакторы:";
        super.initialize();
    }

    public void fillTable(String idFilter) {
    }
}
