package libapp.view.author;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.Dictionary;
import libapp.model.Author;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.TableProperty;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class AuthorController extends TableProperty<Author> {
    @FXML
    private TableColumn<Author, String> id;
    @FXML
    private TableColumn<Author, String> surname;
    @FXML
    private TableColumn<Author, String> name;
    @FXML
    private TableColumn<Author, String> patronymic;
    @FXML
    private TableColumn<Author, String> birthday;
    @FXML
    private TableColumn<Author, String> deathday;
    @FXML
    private TableColumn<Author, String> phonenumber;
    @FXML
    private TableColumn<Author, String> email;

    @FXML
    private void initialize() {
        createMenu(new AuthorAddController(), new AuthorChangeController(), "author" + File.separator + "AuthorAddOverview.fxml");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        deathday.setCellValueFactory(new PropertyValueFactory<>("deathday"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(dataList);
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getAuthors");

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
                dataList.add(new Author(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4],
                        args[5],
                        args[6],
                        args[7]));
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
                            "getAuthors" +
                            ClientSocket.argSep +
                            idFilter);

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new Author(
                        i.get(0).toString(),
                        i.get(1).toString(),
                        i.get(2).toString(),
                        i.get(3).toString(),
                        i.get(4).toString(),
                        i.get(5).toString(),
                        i.get(6).toString(),
                        i.get(7).toString()));
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
