package libapp.view;

import com.sun.security.ntlm.Client;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import libapp.ClientSocket;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectController {
    private Stage dialogStage;
    private Main main;
    private ClientSocket socket;

    private String message = "Нет соединения. Новая попытка через ";

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button connect;
    @FXML
    private Label error;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() throws InterruptedException {

    }

    @FXML
    private int connectToServer() throws IOException {
        int res = 0;

        connect.setDisable(true);

        while (true) {
            try {
                socket = ClientSocket.enableConnection(socket);

                error.setVisible(false);
                connect.setDisable(false);
                break;
            } catch (Exception e) {
                //TODO: можно зациклить попытки уставновить соединение
            }
        }

        socket.makeRequest("123, authUser, pizda228, password");

        //TODO: если что, сообщить, что пароль неправильый

        dialogStage.close();

        return res;
    }

    public void disconnectFromServer() throws IOException {
        socket.getSocket().close();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSocket(ClientSocket socket) {
        this.socket = socket;
    }
}
