package libapp.view.author;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.Author;
import libapp.view.Main;
import libapp.view.TableProperty;

import java.io.File;

public class AuthorController extends TableProperty<Author> {
    @FXML
    private TableColumn<Author, String> id;
    @FXML
    private TableColumn<Author, String> surname;
    @FXML
    private TableColumn<Author, String> name;
    @FXML
    private TableColumn<Author, String> patronymic;
    @FXML
    private TableColumn<Author, String> birthday;
    @FXML
    private TableColumn<Author, String> deathday;
    @FXML
    private TableColumn<Author, String> phonenumber;
    @FXML
    private TableColumn<Author, String> email;

    @FXML
    private void initialize() {
        createMenu(new AuthorAddController(), new AuthorChangeController(), "author" + File.separator + "AuthorAddOverview.fxml");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        deathday.setCellValueFactory(new PropertyValueFactory<>("deathday"));
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
