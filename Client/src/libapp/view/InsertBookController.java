package libapp.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.Dictionary;
import libapp.model.PublishingHouse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InsertBookController {
    private Map<String,String> house_dict = new HashMap<String,String>();
    private Stage windowStage;
    private Main main;
    private ClientSocket socket;
    private ObservableList<PublishingHouse> publishingHouses =
            FXCollections.observableArrayList();
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> publishingHouse;
    @FXML
    private DatePicker year;
    @FXML
    private Button insertButton;

    @FXML
    private void insert() {
        //***
    }

    public void setMain(Main main) {
        this.main = main;
        socket = main.getSocket();
        initPublishingHouses();
    }

    private void initPublishingHouses() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest("<empty>, getPublHouses");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                house_dict.put(i.get(1).toString(), i.get(0).toString());
                publishingHouse.getItems().add(i.get(1).toString());
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }
}
