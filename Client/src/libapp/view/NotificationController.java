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
    private TableColumn<Notification, String> id;
    @FXML
    private TableColumn<Notification, String> userId;
    @FXML
    private TableColumn<Notification, String> login;
    @FXML
    private TableColumn<Notification, String> potentialType;
    @FXML
    private TableColumn<Notification, String> phonenumber;
    @FXML
    private TableColumn<Notification, String> email;

    @FXML
    private void initialize() {
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        potentialType.setCellValueFactory(new PropertyValueFactory<>("potentialType"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

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
