package libapp.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.RegularForField;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class UserAddController extends UserWinController {
    @FXML
    protected TextField login;
    @FXML
    protected TextField password;
    @FXML
    protected ComboBox<String> type;

    public UserAddController(Main main) {
        super(main);
    }

    protected void initialize() {
        RegularForField.setLoginField(login);
        RegularForField.setPhoneField(phone);
        RegularForField.setPasswordField(password);
        sexDict.put("женский", "1");
        sexDict.put("мужской", "0");
        typeDict.put("администратор", "0");
        typeDict.put("библиотекарь", "1");
        typeDict.put("издательство", "2");
        typeDict.put("автор", "3");
        typeDict.put("читатель", "4");
        type.getItems().addAll(
                "администратор",
                "библиотекарь",
                "издательство",
                "автор",
                "читатель");
        sex.getSelectionModel().selectFirst();
        type.getSelectionModel().selectFirst();
        name.setText("add");
    }

    protected void applyChange() {
        super.applyChange();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            //TODO
            String[] args = {
                    main.getUser().getId(),
                    "addUser",
                    name.getText(),
                    surname.getText(),
                    patronymic.getText(),
                    sexDict.get(sex.getValue()),
                    bornDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    phone.getText(),
                    email.getText()};

            result = socket.makeRequest(buildQuery(args));

            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController("Ошибка",
                    "Не удалось вставить запись", e);
        }
    }
}
