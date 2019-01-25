package libapp.view.publication.oneColumnTable.Edithor;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.publication.oneColumnTable.OneColumnTableWinController;

public class EditorsOCTAddController extends OneColumnTableWinController {
    public EditorsOCTAddController(String publicationID, Main main, TableView<OneColumnTable> table) {
        this.publicationID = publicationID;
        this.main = main;
        this.table = table;
    }

    protected void initialize() {
        initialize("Редакторы:");
    }

    protected ObservableList<String> fillComboBox() {
        return fillComboBox("getEditorsNotOfPubl", 3);
    }

    protected void applyChange() {
        addRow("addEditorToPubl");
        super.applyChange();
    }
}
