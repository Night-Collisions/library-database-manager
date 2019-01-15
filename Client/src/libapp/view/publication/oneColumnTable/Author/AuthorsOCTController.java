package libapp.view.publication.oneColumnTable.Author;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.OneColumnTable;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

public class AuthorsOCTController extends OneColumnTableController<OneColumnTable> {
    @FXML
    public void initialize() {
        columnName = "Авторы:";
        super.initialize();
    }

    public void fillTable(String idFilter) {
    }
}
