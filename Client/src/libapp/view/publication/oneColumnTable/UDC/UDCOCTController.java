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

public class UDCOCTController extends OneColumnTableController<OneColumnTable> {
    public UDCOCTController(Main main, String publicationID) {
        this.main = main;
        this.publicationID = publicationID;
    }

    @FXML
    public void initialize() {
        columnName = "УДК:";
        super.initialize();
        //table.setItems(dataList);
    }

    public void fillTable(String idFilter) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getUdcOfPubl" +
                            ClientSocket.argSep + idFilter);

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                String a0 = i.get(0).toString();
                String a1 = i.get(1).toString();
                dataList.add(new OneColumnTable(a0, a1));
            }

        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void onAddMenu() {
        createWindow("publication" + File.separator + "oneColumnTable" + File.separator + "OneColumnAddOverview.fxml", new UDCOCTAddController(publicationID, main));
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
                //TODO: не удалиласб, пока кидаю просто эксепшн
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController(titleErrorDB,
                    contentTextErrorDB, e);
        }
    }
}
