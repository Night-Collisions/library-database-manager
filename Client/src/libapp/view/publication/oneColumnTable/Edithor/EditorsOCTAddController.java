package libapp.view.publication.oneColumnTable.Edithor;

import javafx.collections.ObservableList;
import libapp.view.Main;
import libapp.view.publication.oneColumnTable.OneColumnTableWinController;

public class EditorsOCTAddController extends OneColumnTableWinController {
    public EditorsOCTAddController(String publicationID, Main main) {
        this.publicationID = publicationID;
        this.main = main;
    }

    protected void initialize() {
        initialize("Редакторы:");
    }

    protected ObservableList<String> fillComboBox() {
        return fillComboBox("getEditorsOfPubl");
    }
}
