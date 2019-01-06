package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.model.Notification;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class NotificationController {
    private Stage windowStage;
    private ClientSocket socket;
    private Main main;
    private ObservableList<Notification> notifications =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Notification> table;
    @FXML
    private TableColumn<Notification, String> userId;
    @FXML
    private TableColumn<Notification, String> surname;
    @FXML
    private TableColumn<Notification, String> name;
    @FXML
    private TableColumn<Notification, String> login;
    @FXML
    private TableColumn<Notification, String> potentialType;

    @FXML
    private void initialize() {
        setEvents();

        userId.setCellValueFactory(new PropertyValueFactory<>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        potentialType.setCellValueFactory(new PropertyValueFactory<>("potentialType"));

        table.setItems(notifications);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
    }

    public void setWindowStage(Stage stage) {
        windowStage = stage;
    }

    private void setEvents() {
        ContextMenu context = new ContextMenu();
        MenuItem edit = new MenuItem("Принять");
        MenuItem delete = new MenuItem("Отклонить");

        context.getItems().add(edit);
        context.getItems().add(delete);

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
        this.socket = main.getSocket();
    }
}
