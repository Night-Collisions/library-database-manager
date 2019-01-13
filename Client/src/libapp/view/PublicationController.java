package libapp.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.Dictionary;
import libapp.model.Publication;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest("<empty>, getPublications");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                publications.add(new Publication(
                        i.get(0).toString(),
                        Dictionary.publicationType.get(i.get(1).toString()),
                        i.get(2).toString()));
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
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
            PublicationProperty.KeyWordsProperty(table.getSelectionModel().getSelectedItem().getId());
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        udc.setOnAction(t -> {
            PublicationProperty.UDCProperty(table.getSelectionModel().getSelectedItem().getId());
        });

        insert.setOnAction(t -> {
            PublicationProperty.CreateWindow("PublicationInsertCheckOverview.fxml", new PublicationInsertCheckController());
        });

        edit.setOnAction(t -> {

        });

        delete.setOnAction(t -> {
            PublicationProperty.Delete(table.getSelectionModel().getSelectedItem().getId());
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
