package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import libapp.model.Work;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class WorkController {
    private Main main;
    private ObservableList<Work> works =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Work> table;
    @FXML
    private TableColumn<Work, String> id;
    @FXML
    private TableColumn<Work, String> name;
    @FXML
    private TableColumn<Work, String> publishingHouse;
    @FXML
    private TableColumn<Work, String> year;

    @FXML
    private void initialize() {
        fillTable();
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        publishingHouse.setCellValueFactory(
                new PropertyValueFactory<>("publishingHouse"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.setItems(works);
    }

    private void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
        works.add(new Work(
                "1",
                "Применение аккустических систем для массажа ануса",
                "ДВФУ",
                "2018"));
        works.add(new Work(
                "2",
                "Жил был научный труд",
                "И сделал он пруд",
                "(напрудил)"));
        works.add(new Work(
                "В",
                "И",
                "Т",
                "Я"));
        works.add(new Work(
                "4",
                "Прикладное использование школьной алгебры при покупках в магазине",
                "MIT",
                "2018"));

    }

    private void setEvents() {
        ContextMenu context = new ContextMenu();
        Menu more = new Menu("Подробнее");
        MenuItem keywords = new MenuItem("Ключевые слова");
        MenuItem udc = new MenuItem("УДК");
        MenuItem editors = new MenuItem("Редакторы");
        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        more.getItems().add(keywords);
        more.getItems().add(udc);
        more.getItems().add(editors);

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

        editors.setOnAction(t -> {
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
