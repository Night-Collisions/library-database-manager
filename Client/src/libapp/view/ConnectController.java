package libapp.view;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.ProgramUser;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

                result = socket.makeRequest(
                        "-1" +
                        ClientSocket.argSep +
                        "authorizeUser" +
                        ClientSocket.argSep +
                        usernameTextField.getText() +
                        ClientSocket.argSep +
                        passwordTextField.getText().hashCode());

                if (result.equals("error: null")) {
                    throw new Exception();
                }


                Type type = new TypeToken<ArrayList<String>>(){}.getType();
                ArrayList<String> parsed = new Gson().fromJson(result, type);
                if (parsed.get(0).equals("-1")) {
                    throw new Exception();
                }

                String[] userData = new String[parsed.size()];
                for (int i = 0; i < parsed.size(); ++i) {
                    if (parsed.get(i) != null) {
                        userData[i] = parsed.get(i);
                    } else {
                        userData[i] = "";
                    }
                }

                String userID = userData[0];
                ProgramUser.UserType userType = ProgramUser.int2UserType(Integer.parseInt(userData[7]));
                HashSet<String> publicationsID = new HashSet<>();

                if((userType == ProgramUser.UserType.Author) || (userType == ProgramUser.UserType.PublishingHouse)) {
                    String IDPubls = socket.makeRequest(userID + ClientSocket.argSep + "getPublsOfUser");
                    parsed = new Gson().fromJson(IDPubls, type);

                    for (int i = 0; i < parsed.size(); ++i) {
                        if (parsed.get(i) != null) {
                            publicationsID.add(parsed.get(i));
                        }
                    }
                }

                main.ChangeUser(
                        userData[0],
                        userData[5],
                        userType,
                        publicationsID);

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
