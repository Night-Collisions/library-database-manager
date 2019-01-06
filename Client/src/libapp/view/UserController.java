package libapp.view;

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
import libapp.model.User;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class UserController {
    private ClientSocket socket;
    private Main main;
    private ObservableList<User> users =
            FXCollections.observableArrayList();

    @FXML
    private TableView<User> table;
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
        setEvents();

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

        table.setItems(users);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
    }

    public void fillTable(String idFilter) {
        // TODO: ебашим запрос к серверу и заполняем
    }

    private void setEvents() {
        ContextMenu context = new ContextMenu();
        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        context.getItems().add(insert);
        context.getItems().add(edit);
        context.getItems().add(delete);

        //TODO: нахуячить, если это библиотекарь или че то такое
        insert.setOnAction(t -> {
            //TODO: нахуячить
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        edit.setOnAction(t -> {
            //TODO: нахуячить
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        delete.setOnAction(t -> {
            //TODO: нахуячить
        });

        table.addEventHandler(MOUSE_CLICKED, t -> {
            if(t.getButton() == MouseButton.SECONDARY) {
                context.show(table, t.getScreenX(), t.getScreenY());
            } else {
                context.hide();
            }
        });
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
