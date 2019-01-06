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
import libapp.model.Author;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class AuthorController {
    private Main main;
    private ObservableList<Author> authors =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Author> table;
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
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        deathday.setCellValueFactory(new PropertyValueFactory<>("deathday"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(authors);
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
    }
}
