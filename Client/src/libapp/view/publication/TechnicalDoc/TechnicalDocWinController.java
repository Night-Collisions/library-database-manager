package libapp.view.publication.TechnicalDoc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import libapp.ClientSocket;
import libapp.model.Organization;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.PropertyWin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TechnicalDocWinController extends PropertyWin {    
    @FXML
    protected TextField name;
    @FXML
    protected ComboBox<Organization> organization;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;

    protected Main main;
    protected ClientSocket socket;

    public ObservableList<Organization> dataList = FXCollections.observableArrayList();

    public TechnicalDocWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }


    @FXML
    protected void initialize() {
        organization.setEditable(false);
        fillPubHouseCombobox();
    }

    public void fillPubHouseCombobox() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getOrganizations");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>() {}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                String[] args = new String[i.size()];
                for (int j = 0; j < i.size(); ++j) {
                    if (i.get(j) != null) {
                        args[j] = i.get(j).toString();
                    } else {
                        args[j] = "";
                    }
                }
                dataList.add(new Organization(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4]));

                organization.getItems().addAll(dataList);
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }
}
