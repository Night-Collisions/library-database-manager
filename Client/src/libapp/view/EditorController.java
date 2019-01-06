package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import libapp.model.Editor;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class EditorController {
    private Main main;
    private ObservableList<Editor> editors =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Editor> table;
    @FXML
    private TableColumn<Editor, String> id;
    @FXML
    private TableColumn<Editor, String> surname;
    @FXML
    private TableColumn<Editor, String> name;
    @FXML
    private TableColumn<Editor, String> patronymic;
    @FXML
    private TableColumn<Editor, String> birthday;
    @FXML
    private TableColumn<Editor, String> deathday;
    @FXML
    private TableColumn<Editor, String> phonenumber;
    @FXML
    private TableColumn<Editor, String> email;

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

        table.setItems(editors);
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
