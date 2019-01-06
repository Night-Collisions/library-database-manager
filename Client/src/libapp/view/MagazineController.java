package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import libapp.model.Magazine;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class MagazineController {
    private Main main;
    private ObservableList<Magazine> magazines =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Magazine> table;
    @FXML
    private TableColumn<Magazine, String> id;
    @FXML
    private TableColumn<Magazine, String> name;
    @FXML
    private TableColumn<Magazine, String> topic;

    @FXML
    private void initialize() {
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        topic.setCellValueFactory(
                new PropertyValueFactory<>("topic"));

        table.setItems(magazines);
    }

    public void fillTable() {
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
