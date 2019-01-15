package libapp.view.organization;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.Organization;
import libapp.view.Main;
import libapp.view.TableProperty;

import java.io.File;

public class OrganizationController extends TableProperty<Organization> {
    @FXML
    private TableColumn<Organization, String> id;
    @FXML
    private TableColumn<Organization, String> name;
    @FXML
    private TableColumn<Organization, String> address;
    @FXML
    private TableColumn<Organization, String> phonenumber;
    @FXML
    private TableColumn<Organization, String> email;

    @FXML
    private void initialize() {
        createMenu(new OrganizationAddController(), new OrganizationChangeController(), "organization" + File.separator + "OrganizationAddOverview.fxml");


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
