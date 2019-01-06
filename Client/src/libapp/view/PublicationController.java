package libapp.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.Dictionary;
import libapp.model.Publication;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class PublicationController {
    private Stage windowStage;
    private Main main;
    private ClientSocket socket;
    private ObservableList<Publication> publications =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Publication> table;
    @FXML
    private TableColumn<Publication, String> id;
    @FXML
    private TableColumn<Publication, String> type;
    @FXML
    private TableColumn<Publication, String> name;

    @FXML
    private void initialize() {
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(publications);
    }

    public void fillTable() {
        try {
            socket = ClientSocket.enableConnection(socket);
        } catch (Exception e) {
            // Чет не удалось подключиться
            e.printStackTrace();
        }

        String result = "";
        try {
            result = socket.makeRequest("<empty>, getPublications");
        } catch (Exception e) {
            //Оп, какая то проблемочка
            e.printStackTrace();
        }

        Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
        ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

        for (ArrayList i : parsed) {
            publications.add(new Publication(
                    i.get(0).toString(),
                    Dictionary.publicationType.get(i.get(1).toString()),
                    i.get(2).toString()));
        }
    }

    private void setEvents() {
        ContextMenu context = new ContextMenu();
        MenuItem keywords = new MenuItem("Ключевые слова");
        MenuItem udc = new MenuItem("УДК");
        Menu more = new Menu("Подробнее");
        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        more.getItems().add(keywords);
        more.getItems().add(udc);

        context.getItems().add(more);
        context.getItems().add(insert);
        context.getItems().add(edit);
        context.getItems().add(delete);

        keywords.setOnAction(t -> {
            try {
                String idFilter =
                        table.getSelectionModel().getSelectedItem().getId();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("KeywordOverview.fxml"));
                AnchorPane keywordsTable = loader.load();

                KeywordController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText("Ключевые слова для id " + idFilter);

                Stage window = new Stage();
                initWindow(window, keywordsTable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        udc.setOnAction(t -> {
            try {
                String idFilter =
                        table.getSelectionModel().getSelectedItem().getId();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("UDCOverview.fxml"));
                AnchorPane udcTable = loader.load();

                UDCController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText("УДК для id " + idFilter);

                Stage window = new Stage();
                initWindow(window, udcTable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        insert.setOnAction(t -> {

        });

        edit.setOnAction(t -> {

        });

        delete.setOnAction(t -> {

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

    public void setSocket(ClientSocket socket) {
        this.socket = socket;
    }

    public void setWindowStage(Stage stage) {
        windowStage = stage;
    }

    private void initWindow(Stage window, AnchorPane table) {
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(main.getPrimaryStage());

        Scene scene = new Scene(table);
        window.setScene(scene);
        //controller.setSocket(socket);
        setWindowStage(window);
        window.showAndWait();
    }
}
