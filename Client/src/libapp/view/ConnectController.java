package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.ProgramUser;

import java.io.IOException;
import java.util.HashSet;

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
                        "authorizeUser" +
                        ClientSocket.argSep +
                        usernameTextField.getText() +
                        ClientSocket.argSep +
                        passwordTextField.getText());

                data = result.split(","); //TODO: ВИТЯ ТУТ НЕ ПРАВИЛЬНО
                if (data[0].equals("-1")) {
                    throw new Exception();
                }

                String userLogin = data[5];
                String userID = data[4];
                ProgramUser.UserType userType = ProgramUser.int2UserType(Integer.parseInt(data[7]));
                HashSet<String> userPublicationsID = new HashSet<String>();
                if((userType == ProgramUser.UserType.Author) || (userType == ProgramUser.UserType.PublishingHouse)) {
                    String[] IDPubls = socket.makeRequest(userID + ClientSocket.argSep + "getPublsOfUser").split(", ");
                    System.out.print(123);
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
