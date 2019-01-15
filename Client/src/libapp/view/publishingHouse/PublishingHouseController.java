package libapp.view.publishingHouse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.PublishingHouse;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.TableProperty;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PublishingHouseController extends TableProperty<PublishingHouse> {
    @FXML
    private TableColumn<PublishingHouse, String> id;
    @FXML
    private TableColumn<PublishingHouse, String> name;
    @FXML
    private TableColumn<PublishingHouse, String> address;
    @FXML
    private TableColumn<PublishingHouse, String> phonenumber;
    @FXML
    private TableColumn<PublishingHouse, String> email;

    @FXML
    private void initialize() {
        createMenu(
                new PublishingHouseAddController(),
                new PublishingHouseChangeController(),
                "organization" + File.separator + "OrganizationAddOverview.fxml");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(dataList);
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getPublHouses");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
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
                dataList.add(new PublishingHouse(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4]));
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
