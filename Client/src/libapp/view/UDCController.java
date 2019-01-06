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
import libapp.model.UDC;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class UDCController {
    private Main main;
    private ObservableList<UDC> udcs =
            FXCollections.observableArrayList();

    @FXML
    private TableView<UDC> table;
    @FXML
    private TableColumn<UDC, String> code;

    @FXML
    private void initialize() {
        setEvents();

        code.setCellValueFactory(new PropertyValueFactory<>("code"));
    }

    //Для вывода всех слов
    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
        //...

        table.setItems(udcs);
    }

    //Для вывода для конкретной записи
    public void fillTable(String idFilter) {
        // TODO: ебашим запрос к серверу и заполняем

        //...

        table.setItems(udcs);
    }

    public void setColumnText(String text) {
        code.setText(text);
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
