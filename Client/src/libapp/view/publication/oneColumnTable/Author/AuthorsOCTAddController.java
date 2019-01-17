package libapp.view.publication.oneColumnTable.Author;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.publication.oneColumnTable.OneColumnTableWinController;

public class AuthorsOCTAddController extends OneColumnTableWinController {
    public AuthorsOCTAddController(String publicationID, Main main, TableView<OneColumnTable> table) {
        this.publicationID = publicationID;
        this.main = main;
        this.table = table;
    }

    protected void initialize() {
        initialize("Авторы:");
    }

    protected ObservableList<String> fillComboBox() {
        return fillComboBox("getAuthorsNotOfPubl", 3);
    }

    protected void applyChange() {
        addRow("addAuthorToPubl");
        super.applyChange();
    }
}
