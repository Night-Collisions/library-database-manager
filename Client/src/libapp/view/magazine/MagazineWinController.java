package libapp.view.magazine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.security.ntlm.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import libapp.ClientSocket;
import libapp.model.Organization;
import libapp.model.Subject;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.PropertyWin;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MagazineWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected ComboBox<Subject> subject;
    @FXML
    protected ComboBox<Organization> organization;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;

    protected Main main;
    protected ClientSocket socket;

    public MagazineWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

    protected void initialize() {
        super.initialize();
        fillOrganizationsCombobox();
        fillSubjectCombobox();
    }

    public ObservableList<Organization> orgList = FXCollections.observableArrayList();
    public ObservableList<Subject> subjectlList = FXCollections.observableArrayList();

    public void fillSubjectCombobox() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getSubjects");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>() {}.getType();
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
                subjectlList.add(new Subject(
                        args[0],
                        args[1]));
            }

            subject.getItems().addAll(subjectlList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void fillOrganizationsCombobox() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getOrganizations");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>() {
            }.getType();
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
                orgList.add(new Organization(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4]));
            }

            organization.getItems().addAll(orgList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }
}
