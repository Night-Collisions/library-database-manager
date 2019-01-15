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
    @FXML
    public void initialize() {
        columnName = "Ркдакторы:";
        super.initialize();
    }

    public void fillTable(String idFilter) {
    }
}
