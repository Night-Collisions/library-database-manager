package libapp.view.author;

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
import libapp.model.Author;
import libapp.view.Main;
import libapp.view.TebleProperty;

import java.io.File;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class AuthorController extends TebleProperty<Author> {
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
}
