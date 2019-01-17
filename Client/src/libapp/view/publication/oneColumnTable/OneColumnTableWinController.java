package libapp.view.publication.oneColumnTable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import libapp.ClientSocket;
import libapp.view.MessageController;
import libapp.view.PropertyWin;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OneColumnTableWinController extends PropertyWin {
    @FXML
    Label label;
    @FXML
    public ComboBox<String> combobox;

    protected String ComboBoxItemsID[];
    protected String publicationID;

    protected void initialize(String labelName) {
        super.initialize();
        label.setText(labelName);
        combobox.setItems(fillComboBox());
    }

    protected ObservableList<String> fillComboBox() { return FXCollections.observableArrayList(); }

    protected ObservableList<String> fillComboBox(String functionName) {
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

            ComboBoxItemsID = new String[parsed.size()];
            for (int i = 0; i < parsed.size(); i++) {
                ComboBoxItemsID[i] = parsed.get(i).get(0).toString();
                items.add(parsed.get(i).get(1).toString());
            }

        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
        return items;
    }
}
