package libapp.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.User;
import libapp.view.Main;
import libapp.view.TableProperty;

import java.io.File;

public class UserController extends TableProperty<User> {

    @FXML
    private TableColumn<User, String> id;
    @FXML
    private TableColumn<User, String> surname;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> patronymic;
    @FXML
    private TableColumn<User, String> birthday;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> sex;
    @FXML
    private TableColumn<User, String> login;
    @FXML
    private TableColumn<User, String> phonenumber;
    @FXML
    private TableColumn<User, String> email;

    @FXML
    private void initialize() {
        createMenu(new UserAddController(), new UserChangeController(), "user" + File.separator + "UserAddOverview.fxml", "UserProfileOverview.fxml");

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(dataList);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
    }

    public void fillTable(String idFilter) {
        // TODO: ебашим запрос к серверу и заполняем
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

}
