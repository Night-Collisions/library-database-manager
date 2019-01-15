package libapp.view.publishingHouse;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.PublishingHouse;
import libapp.view.Main;
import libapp.view.TableProperty;

import java.io.File;

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
        createMenu(new PublishingHouseAddController(), new PublishingHouseChangeController(), "organization" + File.separator + "OrganizationAddOverview.fxml");


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(dataList);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
