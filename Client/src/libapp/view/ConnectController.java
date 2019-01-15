package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.ProgramUser;

import java.io.IOException;

public class ConnectController {
    private Stage dialogStage;
    private Main main;
    private ClientSocket socket;

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
        error.setText("Не удалось подключиться.");
        error.setVisible(false);
    }

    @FXML
    private int connectToServer() {
        int res = 0;

            try {
                String result = "";
                String[] data;
                socket = ClientSocket.enableConnection(socket);

                error.setVisible(false);
                connect.setDisable(false);
                result = socket.makeRequest(" " +
                        ClientSocket.argSep +
                        "authUser" +
                        ClientSocket.argSep +
                        usernameTextField.getText() +
                        ClientSocket.argSep +
                        passwordTextField.getText());

                data = result.split(", ");
                if (data[0].equals("-1")) {
                    throw new Exception();
                }


                //TODO дохуячить
                //main.getUser() = new ProgramUser();

                dialogStage.close();
            } catch (Exception e) {
                error.setVisible(true);
            }

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
