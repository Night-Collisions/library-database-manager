package libapp.view.AllOneColumnTable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import libapp.ClientSocket;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.MessageController;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AllKeyWordsController extends AllOneColumnTableController<OneColumnTable> {
    public AllKeyWordsController(Main main) {
        this.main = main;
    }

    @FXML
    public void initialize() {
        columnName = "Ключевы слова:";
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

    public void onAddMenu() {
        createWindow("AllOneColumnTable" + File.separator + "AllOneColumnTableAddOverview.fxml", new AllKeyWordsAddController(main));
        table.getItems().clear();
        fillTable();
    }

    public void deleteRow(String id) {
        deleteRow(id, "deleteKeyword");
    }
}