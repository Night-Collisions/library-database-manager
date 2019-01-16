package libapp.view.publication.oneColumnTable.KeyWords;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import libapp.ClientSocket;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class KeywordOCTController extends OneColumnTableController<OneColumnTable> {
    public KeywordOCTController(Main main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
        columnName = "Ключевые слова:";
        super.initialize();
    }

    public void fillTable() {
        String result = "";
        try {
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getKeywords");

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new OneColumnTable(i.get(0).toString(), i.get(1).toString()));
            }

            table.setItems(dataList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void fillTable(String idFilter) {
        String result = "";
        try {
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getUdcOfPubl" +
                            ClientSocket.argSep +
                            idFilter);

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new OneColumnTable(i.get(0).toString(), i.get(1).toString()));
            }

            table.setItems(dataList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }
}
