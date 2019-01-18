package libapp.view.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.ProgramUser;
import libapp.model.User;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.TableProperty;
import libapp.view.UserProfileOverview;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

import static libapp.Dictionary.userType;
import static libapp.ProgramUser.StrToUserType;

public class UserController extends TableProperty<User> {

    @FXML
    private TableColumn<User, String> id;
    @FXML
    private TableColumn<User, String> surname;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> patronymic;
    @FXML
    private TableColumn<User, String> birthday;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> sex;
    @FXML
    private TableColumn<User, String> login;
    @FXML
    private TableColumn<User, String> phonenumber;
    @FXML
    private TableColumn<User, String> email;

    @FXML
    private void initialize() {
        createMenu();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(dataList);
    }

    public void onAddMenu() {
        createWindow("user" + File.separator + "UserAddOverview.fxml", new UserAddController(main));
        table.getItems().clear();
        fillTable();
    }

    public void onEditMenu() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            //createWindow("UserProfileOverview.fxml", new UserChangeController(main, table.getSelectionModel().getSelectedItem().getId()));
            HashSet<String> hs = new HashSet<>();
            createWindow("UserProfileOverview.fxml", new UserProfileOverview(
                    new ProgramUser(table.getSelectionModel().getSelectedItem().getId(),
                            table.getSelectionModel().getSelectedItem().getLogin(),
                            StrToUserType.get(table.getSelectionModel().getSelectedItem().getType()),
                            table.getSelectionModel().getSelectedItem().getName(),
                            table.getSelectionModel().getSelectedItem().getSurname(),
                            table.getSelectionModel().getSelectedItem().getPatronymic(),
                            hs,
                            (table.getSelectionModel().getSelectedItem().getSex().equals("женский")),
                            table.getSelectionModel().getSelectedItem().getPhonenumber(),
                            table.getSelectionModel().getSelectedItem().getEmail()
                            )
                    , main, socket));
            table.getItems().clear();
            fillTable();
        }
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getUsers");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                String[] args = new String[i.size()];
                for (int j = 0; j < i.size(); ++j) {
                    if (i.get(j) != null) {
                        args[j] = i.get(j).toString();
                    } else {
                        args[j] = "";
                    }
                }
                dataList.add(new User(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4].equals("1") ? "женский" : "мужской",
                        args[5],
                        args[6],
                        userType.get(args[7]),
                        args[8],
                        args[9]));
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void fillTable(String idFilter) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "getUsers" +
                            ClientSocket.argSep +
                            idFilter);

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                String[] args = new String[i.size()];
                for (int j = 0; j < i.size(); ++j) {
                    if (i.get(j) != null) {
                        args[j] = i.get(j).toString();
                    } else {
                        args[j] = "";
                    }
                }
                dataList.add(new User(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4],
                        args[5],
                        args[6],
                        args[7],
                        args[8],
                        args[9]));
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

}
