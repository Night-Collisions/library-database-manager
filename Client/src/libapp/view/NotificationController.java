package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import libapp.model.Notification;

public class NotificationController extends TableProperty<Notification> {
    private Stage windowStage;

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
        MenuItem commit = new MenuItem("Принять");
        MenuItem refuse = new MenuItem("Отклонить");

        commit.setOnAction(t -> {

        });

        refuse.setOnAction(t -> {

        });

        MenuItem items[] = {commit, refuse};
        createMenu(items);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        potentialType.setCellValueFactory(new PropertyValueFactory<>("potentialType"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(dataList);
    }

    public void fillTable() {

    }

    public void setWindowStage(Stage stage) {
        windowStage = stage;
    }
}
