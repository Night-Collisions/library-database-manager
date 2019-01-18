package libapp.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.RegularForField;

import java.time.format.DateTimeFormatter;

import static libapp.Dictionary.userType2Server;
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
        typeDict.put("читатель", "4");
        type.getItems().addAll(
                "администратор",
                "библиотекарь",
                "читатель");
        sex.getSelectionModel().selectFirst();
        type.getSelectionModel().selectFirst();
        name.setText("add");
    }

    protected void applyChange() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            if(name.getText().equals("")) {
                new MessageController(MessageController.MessageType.WARNING, "Ошибка создания пользователя.", "Поле имя обязательно должно быть заполненым.");
                return;
            }

            if(password.getText().length() <= 8) {
                new MessageController(MessageController.MessageType.WARNING, "Ошибка создания пользователя.", "Длина пароля должна быть более 8 символов.");
                return;
            }

            if(login.getText().equals("")) {
                new MessageController(MessageController.MessageType.WARNING, "Ошибка создания пользователя.", "Поле логин обязательно должно быть заполненым.");
                return;
            }

            String[] args = {
                    main.getUser().getId(),
                    "addUser",
                    name.getText(),
                    surname.getText().equals("") ? "NULL" : surname.getText(),
                    patronymic.getText().equals("") ? "NULL" : patronymic.getText(),
                    sexDict.get(sex.getValue()),
                    login.getText(),
                    Integer.toString(password.getText().hashCode()),
                    (bornDate.getValue() == null) ? "NULL" : bornDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    typeDict.get(type.getValue()),
                    phone.getText().equals("") ? "NULL" : phone.getText(),
                    email.getText().equals("") ? "NULL" :  email.getText()};

            String s = buildQuery(args);
            result = socket.makeRequest(s);

            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController("Ошибка",
                    "Не удалось вставить запись", e);
        }
        super.applyChange();
    }
}
