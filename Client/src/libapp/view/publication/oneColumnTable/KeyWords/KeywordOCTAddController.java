package libapp.view.publication.oneColumnTable.KeyWords;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.publication.oneColumnTable.OneColumnTableWinController;

public class KeywordOCTAddController extends OneColumnTableWinController {
    public KeywordOCTAddController(String publicationID, Main main, TableView<OneColumnTable> table) {
        this.publicationID = publicationID;
        this.main = main;
        this.table = table;
    }

    protected void initialize() {
        initialize("Ключ. слово:");
    }

    protected ObservableList<String> fillComboBox() {
        return fillComboBox("getKeywordsNotOfPubl", 1);
    }

    protected void applyChange() {
        addRow("addKeywordToPubl");
        super.applyChange();
    }
}
