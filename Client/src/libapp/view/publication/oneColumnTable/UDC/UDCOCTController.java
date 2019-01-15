package libapp.view.publication.oneColumnTable.UDC;

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
import libapp.model.UDC;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class UDCOCTController extends OneColumnTableController<UDC> {
//    public ClientSocket socket;
//    public Main main;
//    public ObservableList<UDC> udcs =
//            FXCollections.observableArrayList();
//
//    @FXML
//    public TableView<UDC> table;
//    @FXML
//    public TableColumn<UDC, String> column;
//
    @FXML
    public void initialize() {
//        column.setText("УДК:");
//        setEvents();
//        column.setCellValueFactory(new PropertyValueFactory<>("code"));
        columnName = "УДК:";
        PropertyValueFactory = "code";
        super.initialize();
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getAllUdc" +
                            ClientSocket.argSep);

            if (result.equals("wrong args")) {
                throw new Exception();
            }


            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new UDC(i.get(0).toString(), i.get(1).toString()));
            }

            table.setItems(dataList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }

        table.setItems(dataList);
    }

    public void fillTable(String idFilter) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getUdcOfPubl" +
                            ClientSocket.argSep + idFilter);

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new UDC(i.get(0).toString(), i.get(1).toString()));
            }

            table.setItems(dataList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }

        table.setItems(dataList);
    }

    public void setColumnText(String text) {
        column.setText(text);
    }

//    public void setEvents() {
//        ContextMenu context = new ContextMenu();
//        MenuItem insert = new MenuItem("Добавить");
//        MenuItem edit = new MenuItem("Редактировать");
//        MenuItem delete = new MenuItem("Удалить");
//
//        context.getItems().add(insert);
//        context.getItems().add(edit);
//        context.getItems().add(delete);
//
//        //TODO: нахуячить, если это библиотекарь или че то такое
//        insert.setOnAction(t -> {
//            //TODO: нахуячить
//        });
//
//        //TODO: нахуячить, если это библиотекарь или че то такое
//        edit.setOnAction(t -> {
//            //TODO: нахуячить
//        });
//
//        //TODO: нахуячить, если это библиотекарь или че то такое
//        delete.setOnAction(t -> {
//            //TODO: нахуячить
//        });
//
//        table.addEventHandler(MOUSE_CLICKED, t -> {
//            if(t.getButton() == MouseButton.SECONDARY) {
//                context.show(table, t.getScreenX(), t.getScreenY());
//            } else {
//                context.hide();
//            }
//        });
//    }
//
//    public void setMain(Main main) {
//        this.main = main;
//        this.socket = main.getSocket();
//    }
}
