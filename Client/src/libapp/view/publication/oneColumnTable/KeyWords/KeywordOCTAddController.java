package libapp.view.publication.oneColumnTable.KeyWords;

import javafx.collections.ObservableList;
import libapp.view.Main;
import libapp.view.publication.oneColumnTable.OneColumnTableWinController;

public class KeywordOCTAddController extends OneColumnTableWinController {
    public KeywordOCTAddController(String publicationID, Main main) {
        this.publicationID = publicationID;
        this.main = main;
    }

    protected void initialize() {
        initialize("Ключ. слово:");
    }

    protected ObservableList<String> fillComboBox() {
        return fillComboBox("getKeywordsOfPubl");
    }
}
