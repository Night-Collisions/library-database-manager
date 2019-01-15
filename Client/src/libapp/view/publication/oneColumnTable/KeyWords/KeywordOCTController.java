package libapp.view.publication.oneColumnTable.KeyWords;

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
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class KeywordOCTController extends OneColumnTableController<Keyword> {
//    private ClientSocket socket;
//    private Main main;
//    private ObservableList<Keyword> keywords =
//            FXCollections.observableArrayList();
//
//    @FXML
//    private TableView<Keyword> table;
//    @FXML
//    private TableColumn<Keyword, String> column;
//
    @FXML
    public void initialize() {
//        setEvents();
//
//        column.setCellValueFactory(new PropertyValueFactory<>("word"));
        columnName = "Ключевые слова:";
        PropertyValueFactory = "word";
        super.initialize();
    }

    public void fillTable() {
        String result = "";
        try {
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getAllKeywords");

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new Keyword(i.get(0).toString(), i.get(1).toString()));
            }

            table.setItems(dataList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void fillTable(String idFilter) {
        String result = "";
        try {
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "GetKeywordsOfPubl" +
                            ClientSocket.argSep +
                            idFilter);

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new Keyword(i.get(0).toString(), i.get(1).toString()));
            }

            table.setItems(dataList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

//    public void setColumnText(String text) {
//        column.setText(text);
//    }
//
//    private void setEvents() {
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
