package libapp.view.publication.oneColumnTable.UDC;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import libapp.ClientSocket;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.PropertyWin;
import libapp.view.publication.oneColumnTable.OneColumnTableWinController;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static libapp.view.MessageController.contentTextErrorDB;
import static libapp.view.MessageController.titleErrorDB;

public class UDCOCTAddController extends OneColumnTableWinController {
    public UDCOCTAddController(String publicationID, Main main, TableView<OneColumnTable> table) {
        this.publicationID = publicationID;
        this.main = main;
        this.table = table;
    }

    protected void initialize() {
        initialize("УДК:");
    }

    protected ObservableList<String> fillComboBox() {
        return fillComboBox("getUdcNotOfPubl", 1);
    }

    protected void applyChange() {
        addRow("addUdcToPubl");
        super.applyChange();
    }
}
