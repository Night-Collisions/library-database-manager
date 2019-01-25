package libapp.view.publication.oneColumnTable;

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
import libapp.view.MessageController;
import libapp.view.PropertyWin;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static libapp.view.MessageController.contentTextErrorDB;
import static libapp.view.MessageController.titleErrorDB;

public class OneColumnTableWinController extends PropertyWin {
    @FXML
    Label label;
    @FXML
    public ComboBox<String> combobox;

    protected TableView<OneColumnTable> table;

    protected Map<String, String> ComboBoxItemsID = new HashMap<>();
    protected String publicationID;

    protected void initialize(String labelName) {
        super.initialize();
        label.setText(labelName);
        combobox.setItems(fillComboBox());
    }

    protected ObservableList<String> fillComboBox() { return FXCollections.observableArrayList(); }

    protected ObservableList<String> fillComboBox(String functionName, int num) {
        ObservableList<String> items = FXCollections.observableArrayList();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            functionName +
                            ClientSocket.argSep + publicationID);

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>() {
            }.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (int i = 0; i < parsed.size(); i++) {
                String s = parsed.get(i).get(1).toString();
                for(int k = 2; k < num; k++)
                    s += (" " + parsed.get(i).get(k).toString());
                ComboBoxItemsID.put(s, parsed.get(i).get(0).toString());
                items.add(s);
            }

        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
        return items;
    }

    public void addRow(String method) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            method +
                            ClientSocket.argSep +
                            publicationID +
                            ClientSocket.argSep +
                            ComboBoxItemsID.get(combobox.getValue()));

            if (result.equals("ok")) {
                table.getItems().add(new OneColumnTable( ComboBoxItemsID.get(combobox.getValue()), combobox.getValue()));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController(titleErrorDB,
                    contentTextErrorDB, e);
        }
    }
}
