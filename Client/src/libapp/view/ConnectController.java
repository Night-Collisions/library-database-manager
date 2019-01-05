package libapp.view;

import com.sun.security.ntlm.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libapp.ClientSocket;

import java.io.IOException;

public class ConnectController {
    private Stage dialogStage;
    private Main main;
    private ClientSocket socket;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button connect;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private int connectToServer() throws IOException {
        System.out.println(username.getText() + ' ' + password.getText());
        int res = 0;

        socket = new ClientSocket("localhost", 9012);
        socket.makeRequest("123, authUser, pizda228, password");

        //TODO: если что, сообщить, что пароль неправильый

        dialogStage.close();

        return res;
    }

    public void disconnectFromServer() {

    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSocket(ClientSocket socket) {
        this.socket = socket;
    }
}
