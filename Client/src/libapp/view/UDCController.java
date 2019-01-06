package libapp.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import libapp.model.Keyword;
import libapp.model.UDC;

import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class UDCController {
    private ClientSocket socket;
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
        try {
            socket = ClientSocket.enableConnection(socket);
        } catch (Exception e) {
            // Чет не удалось подключиться
            e.printStackTrace();
        }

        String result;
        try {
            result = socket.makeRequest("<empty> , getKeywordsOfPubl, " + idFilter);

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                udcs.add(new UDC(i.get(1).toString()));
            }

            table.setItems(udcs);
        } catch (Exception e) {
            //Оп, какая то проблемочка
            e.printStackTrace();
        }

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
        this.socket = main.getSocket();
    }
}
