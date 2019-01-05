package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectController {
    Stage dialogStage;
    Main main;

    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Button connect;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private int connectToServer() {
        System.out.println(username.getText() + ' ' + password.getText());

        /*Можно проверить корректность введеных данных,
        * типа чтоб уж не совсем жесть "№;%:?*()(*?:%;№"*/

        int res = 0;
        /*Здесь происходит подключение
        * к серверу через сокеты, сервер делает запрос к БД ...
        * Приходит ответ от сервера...*/

        // Если все по красоте, то клоуз
        dialogStage.close();

        return res;
    }

    public void disconnectFromServer() {

    }

    public void setMain(Main main) {
        this.main = main;
    }
}
