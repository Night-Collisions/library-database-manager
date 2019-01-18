package libapp.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.model.Notification;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static libapp.Dictionary.userType;
import static libapp.QueryParser.buildQuery;

public class NotificationController extends TableProperty<Notification> {
    private Stage windowStage;

    @FXML
    private TableColumn<Notification, String> id;
    @FXML
    private TableColumn<Notification, String> userId;
    @FXML
    private TableColumn<Notification, String> login;
    @FXML
    private TableColumn<Notification, String> phonenumber;
    @FXML
    private TableColumn<Notification, String> email;
    @FXML
    private TableColumn<Notification, String> prefType;
    @FXML
    private TableColumn<Notification, String> prefID;

    @FXML
    private void initialize() {
        MenuItem commit = new MenuItem("Принять");
        MenuItem refuse = new MenuItem("Отклонить");

        commit.setOnAction(t -> {
            try {
                String result = "";
                socket = ClientSocket.enableConnection(socket);
                if (table.getSelectionModel().getSelectedItem().getType().equals("3")) {
                    String[] args = {
                            main.getUser().getId(),
                            "addUserToAuthor",
                            table.getSelectionModel().getSelectedItem().getUserId(),
                            table.getSelectionModel().getSelectedItem().getPrefID()};
                    result = socket.makeRequest(buildQuery(args));
                } else {
                    String[] args = {
                            main.getUser().getId(),
                            "addUserToPublHouse",
                            table.getSelectionModel().getSelectedItem().getUserId(),
                            table.getSelectionModel().getSelectedItem().getPrefID()};
                    result = socket.makeRequest(buildQuery(args));
                }

                if (!result.equals("ok")) {
                    throw new Exception();
                }

                String[] args = {
                        main.getUser().getId(),
                        "deleteVerf",
                        table.getSelectionModel().getSelectedItem().getId()};

                result = socket.makeRequest(buildQuery(args));

                if (!result.equals("ok")) {
                    throw new Exception();
                }


                table.getItems().clear();
                fillTable();
            } catch (Exception e) {
                System.out.println(e);
                new MessageController(MessageController.titleErrorGetNewData,
                        MessageController.contentTextErrorGetNewData, e);
            }
        });

        refuse.setOnAction(t -> {
            try {
                String result = "";
                socket = ClientSocket.enableConnection(socket);
                String[] args = {
                        main.getUser().getId(),
                        "deleteVerf",
                        table.getSelectionModel().getSelectedItem().getId()};

                result = socket.makeRequest(buildQuery(args));

                if (!result.equals("ok")) {
                    throw new Exception();
                }

                table.getItems().clear();
                fillTable();
            } catch (Exception e) {
                System.out.println(e);
                new MessageController(MessageController.titleErrorGetNewData,
                        MessageController.contentTextErrorGetNewData, e);
            }
        });

        MenuItem[] items = {commit, refuse};
        createMenu(items);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        prefType.setCellValueFactory(new PropertyValueFactory<>("type"));
        prefID.setCellValueFactory(new PropertyValueFactory<>("prefID"));

        table.setItems(dataList);
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getVerfs");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);
            System.out.println(parsed);
            for (ArrayList i : parsed) {
                String[] args = new String[i.size()];
                for (int j = 0; j < i.size(); ++j) {
                    if (i.get(j) != null) {
                        args[j] = i.get(j).toString();
                    } else {
                        args[j] = "";
                    }
                }
                dataList.add(new Notification(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4],
                        userType.get(args[5]),
                        args[5].equals("3") ? args[6] : args[7]));
            }
        } catch (Exception e) {
            System.out.println(e);
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void setWindowStage(Stage stage) {
        windowStage = stage;
    }
}
