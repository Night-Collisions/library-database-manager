package libapp.view.publication.oneColumnTable.UDC;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import libapp.ClientSocket;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static libapp.view.MessageController.contentTextErrorDB;
import static libapp.view.MessageController.titleErrorDB;

public class UDCOCTController extends OneColumnTableController {
    public UDCOCTController(Main main, String publicationID) {
        this.main = main;
        this.publicationID = publicationID;
    }

    @FXML
    public void initialize() {
        columnName = "УДК:";
        super.initialize();
        //table.setItems(publList);
    }

    public void fillTable(String idFilter) {
        fillTable(idFilter, "getUdcOfPubl",1);
    }

    public void onAddMenu() {
        createWindow("publication" + File.separator + "oneColumnTable" + File.separator + "OneColumnAddOverview.fxml", new UDCOCTAddController(publicationID, main, table));
    }

    public void deleteRow(String id) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "deleteUdcFromPubl" +
                            ClientSocket.argSep +
                            publicationID +
                            ClientSocket.argSep +
                            id);

            if (result.equals("ok")) {
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController(titleErrorDB,
                    contentTextErrorDB, e);
        }
    }
}
