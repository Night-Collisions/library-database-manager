package libapp.view.publication.oneColumnTable.UDC;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import libapp.ClientSocket;
import libapp.model.OneColumnTable;
import libapp.view.MessageController;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UDCOCTController extends OneColumnTableController<OneColumnTable> {
    @FXML
    public void initialize() {
        columnName = "УДК:";
        super.initialize();
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getAllUdc" +
                            ClientSocket.argSep);

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

        table.setItems(dataList);
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
                dataList.add(new OneColumnTable(i.get(0).toString(), i.get(1).toString()));
            }

            table.setItems(dataList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }

        table.setItems(dataList);
    }
}
