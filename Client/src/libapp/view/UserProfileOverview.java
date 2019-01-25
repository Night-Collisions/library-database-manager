package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.ProgramUser;
import libapp.view.RegularForField;

import java.util.Optional;

import static com.sun.org.apache.xalan.internal.xslt.EnvironmentCheck.WARNING;
import static libapp.Dictionary.sexToName;
import static libapp.QueryParser.buildQuery;

public class UserProfileOverview {
    ProgramUser user;
    Main main;
    ClientSocket socket;

    public UserProfileOverview(ProgramUser user, Main main, ClientSocket socket) {
        this.user = user;
        this.main = main;
        this.socket = socket;
    }

    @FXML
    private Label id;
    @FXML
    private Label login;
    @FXML
    private Button changePassword;
    @FXML
    private Label type;
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField patronymic;
    @FXML
    private ComboBox<String> sex;
    @FXML
    private DatePicker bornDate;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private Button accept;
    @FXML
    private Button reject;

    String newPassword;

    @FXML
    private void initialize() throws InterruptedException {
        RegularForField.setPhoneField(phone);

        newPassword = "";

        id.setText(user.getId());
        login.setText(user.getLogin());
        type.setText(ProgramUser.UserTypeToStr.get(user.getType()));
        surname.setText(user.getSurname());
        name.setText(user.getName());
        patronymic.setText(user.getPatromic());
        sex.setValue(sexToName.get(user.isWoman()));
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
    }

    @FXML
    void changePassword(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Изменить пароль");
        dialog.setHeaderText(null);
        dialog.setContentText("Новый пароль:");

        RegularForField.setPasswordField(dialog.getEditor());

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            newPassword =  Integer.toString(result.get().hashCode());
        }
    }

    @FXML
    private void applyChange() {
        if (name.equals("")) {
            new MessageController(MessageController.MessageType.WARNING, "Не заполнено обязательное поле!", "Поле имя является обязательным для заполнения.");
            return;
        }
        if (!newPassword.equals("")) {
            try {
                socket = ClientSocket.enableConnection(socket);
                String[] args = {
                        main.getUser().getId(),
                        "changeUserPassword",
                        user.getId(),
                        newPassword
                };
                String result = socket.makeRequest(buildQuery(args));
                if (!result.equals("ok")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(e);
                new MessageController(MessageController.titleErrorGetNewData,
                        MessageController.contentTextErrorGetNewData, e);
            }
        }
        user.setSurname(surname.getText());
        user.setName(name.getText());
        user.setPatronymic(patronymic.getText());
        user.setIsWoman(sex.getValue().equals("женский"));
        user.setPhone(phone.getText());
        user.setEmail(email.getText());
        try {
            socket = ClientSocket.enableConnection(socket);
            String[] args = {
                    main.getUser().getId(),
                    "changeUserInfo",
                    user.getId(),
                    user.getName(),
                    user.getSurname().equals("") ? "NULL" : user.getSurname(),
                    user.getPatromic().equals("") ? "NULL" : user.getPatromic(),
                    (user.isWoman()) ? "1" : "0",
                    "NULL",
                    user.getPhone().equals("") ? "NULL" : user.getPhone(),
                    user.getEmail().equals("") ? "NULL" : user.getEmail()
            };
            String result = socket.makeRequest(buildQuery(args));
            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e);
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }

        closeWindow();
    }

    @FXML
    private void closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
    }
}
