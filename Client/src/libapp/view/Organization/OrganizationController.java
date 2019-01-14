package libapp.view.Organization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import libapp.ClientSocket;
import libapp.model.Organization;
import libapp.view.Main;
import libapp.view.TebleProperty;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class OrganizationController extends TebleProperty<Organization> {
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
        createMenu(new OrganizationAddController(), new OrganizationChangeController(), "Organization\\OrganizationAddOverview.fxml");


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
}
