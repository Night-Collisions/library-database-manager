package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import libapp.model.Thesis;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class ThesisController {
    private Main main;
    private ObservableList<Thesis> articles =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Thesis> table;
    @FXML
    private TableColumn<Thesis, String> id;
    @FXML
    private TableColumn<Thesis, String> name;
    @FXML
    private TableColumn<Thesis, String> magazineOrWork;

    @FXML
    private void initialize() {
        fillTable();
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        magazineOrWork.setCellValueFactory(
                new PropertyValueFactory<>("magazineOrWork"));

        table.setItems(articles);
    }

    private void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
    }

    private void setEvents() {
        ContextMenu context = new ContextMenu();
        Menu more = new Menu("Подробнее");
        MenuItem keywords = new MenuItem("Ключевые слова");
        MenuItem udc = new MenuItem("УДК");
        MenuItem authors = new MenuItem("Авторы");
        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        more.getItems().add(keywords);
        more.getItems().add(udc);
        more.getItems().add(authors);

        context.getItems().add(more);
        context.getItems().add(insert);
        context.getItems().add(edit);
        context.getItems().add(delete);

        keywords.setOnAction(t -> {
            //TODO: нахуячить
        });

        udc.setOnAction(t -> {
            //TODO: нахуячить
        });

        authors.setOnAction(t -> {
            //TODO: нахуячить
        });

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
