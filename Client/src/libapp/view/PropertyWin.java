package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import libapp.ClientSocket;

public class PropertyWin {
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;

    protected Main main;
    protected ClientSocket socket;

    @FXML
    protected void initialize() {}

    @FXML
    protected void applyChange() {
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
    }
}
