package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libapp.ClientSocket;

public class GroupChangeRequestOverview {

    @FXML
    private ComboBox<String> group;
    @FXML
    private Label labelContacts;
    @FXML
    private Button accept;
    @FXML
    private Button reject;

    private ObservableList<String> grupsNameList =
            FXCollections.observableArrayList("Автор", "Издательство");
    private Main main;
    private ClientSocket socket;

    @FXML
    private void initialize() {
        group.setItems(grupsNameList);
    }

    @FXML
    private int closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
