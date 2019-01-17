package libapp.view.publication.oneColumnTable.Author;

import javafx.collections.ObservableList;
import libapp.view.Main;
import libapp.view.publication.oneColumnTable.OneColumnTableWinController;

public class AuthorsOCTAddController extends OneColumnTableWinController {
    public AuthorsOCTAddController(String publicationID, Main main) {
        this.publicationID = publicationID;
        this.main = main;
    }

    protected void initialize() {
        initialize("Авторы:");
    }

    protected ObservableList<String> fillComboBox() {
        return fillComboBox("getAuthorsOfPubl");
    }
}
