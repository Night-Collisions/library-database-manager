package libapp.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.model.Author;
import libapp.model.PublishingHouse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static libapp.QueryParser.buildQuery;

public class GroupChangeRequestOverview {

    public GroupChangeRequestOverview(Main main) {
        this.main = main;
    }

    @FXML
    private ComboBox<String> group;
    @FXML
    private Label labelContacts;
    @FXML
    private Button accept;
    @FXML
    private Button reject;
    @FXML
    private ComboBox<String> want;
    @FXML
    private Label label;

    private ObservableList<String> grupsNameList =
            FXCollections.observableArrayList("Автор", "Издательство");
    private Main main;
    private ClientSocket socket;
    private Map<String, String> toID = new HashMap<String, String>();

    @FXML
    private void initialize() {
        group.setItems(grupsNameList);
        if(main.getUser().haveContacts()) {
            labelContacts.setText("есть");
        } else {
            labelContacts.setText("нет");
        }

        group.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                boolean isAuthor = t1.equals("Автор");
                if(isAuthor) {
                    want.getItems().clear();
                    label.setText("Автор:");
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
                            String s =  args[1] + " " + args[2];
                            toID.put(s, args[0]);
                            want.getItems().add(s);
                        }
                    } catch (Exception e) {
                        new MessageController(MessageController.titleErrorGetNewData,
                                MessageController.contentTextErrorGetNewData, e);
                    }
                } else {
                    label.setText("Издат.:");
                    want.getItems().clear();

                    try {
                        String result = "";
                        socket = ClientSocket.enableConnection(socket);
                        result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getPublHouses");

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
                            toID.put(args[1], args[0]);
                            want.getItems().add(args[1]);
                        }
                    } catch (Exception e) {
                        new MessageController(MessageController.titleErrorGetNewData,
                                MessageController.contentTextErrorGetNewData, e);
                    }
                }
            }
        });
    }

    @FXML
    private void send() {

    }

    @FXML
    private int closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }

    @FXML
    private void applyChange() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            String s = want.getValue();
            String b = toID.get(s);
            if (group.getValue().equals("Автор")) {
                String[] args = {
                        main.getUser().getId(),
                        "addVerf",
                        "3",
                        toID.get(want.getValue()),
                        "NULL"};
                result = socket.makeRequest(buildQuery(args));
            } else {
                String[] args = {
                        main.getUser().getId(),
                        "addVerf",
                        "2",
                        "NULL",
                        toID.get(want.getValue())};
                result = socket.makeRequest(buildQuery(args));
            }

            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e);
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
        closeWindow();
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
