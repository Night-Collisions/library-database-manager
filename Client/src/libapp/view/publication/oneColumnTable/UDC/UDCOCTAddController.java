package libapp.view.publication.oneColumnTable.UDC;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    public UDCOCTAddController(String publicationID, Main main) {
        this.publicationID = publicationID;
        this.main = main;
    }

    protected void initialize() {
        initialize("УДК:");
    }

    protected ObservableList<String> fillComboBox() {
        return fillComboBox("getUdcOfPubl");
    }

    protected void applyChange() {
        System.out.print(combobox.getI);
    }
}
