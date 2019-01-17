package libapp.view.AllOneColumnTable;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import libapp.ClientSocket;
import libapp.view.MessageController;
import libapp.view.PropertyWin;

import static libapp.view.MessageController.contentTextErrorDB;
import static libapp.view.MessageController.titleErrorDB;

public class AllOneColumnTableWinController extends PropertyWin {
    @FXML
    Label label;
    @FXML
    public TextField name;

    protected void initialize(String labelName) {
        super.initialize();
        label.setText(labelName);
    }

    public void createRow(String method) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            method +
                            ClientSocket.argSep +
                            name.getText());
            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController(titleErrorDB,
                    contentTextErrorDB, e);
        }
    }
}
