package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libapp.ClientSocket;

import java.io.IOException;

public class ConnectController {
    private Stage dialogStage;
    private Main main;
    private ClientSocket socket;

    private String message = "Нет соединения. Новая попытка через ";

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button connect;
    @FXML
    private Label error;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() throws InterruptedException {
        RegularForField.setLoginField(usernameTextField);
        RegularForField.setPasswordField(passwordTextField);
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

        socket.makeRequest("123, authUser, pizda228, passwordTextField");

        //TODO: если что, сообщить, что пароль неправильый

        dialogStage.close();

        return res;
    }

    public void disconnectFromServer() throws IOException {
        socket.getSocket().close();
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

}
