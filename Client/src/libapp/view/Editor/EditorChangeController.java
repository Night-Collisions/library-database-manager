package libapp.view.Editor;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class EditorChangeController extends EditorWinController{
    protected String ID;
    libapp.model.Editor items;

    public EditorChangeController(Main main, libapp.model.Editor items) {
        super(main);
        ID = items.getId();
        this.items = items;
        sexDict.put("женский", "1");
        sexDict.put("мужской", "0");
    }

    protected void initialize() {
        super.initialize();
        name.setText(items.getName());
        surname.setText(items.getSurname());
        patronymic.setText(items.getPatronymic());
        sex.setValue(items.getSex().equals("1") ? "женский" : "мужской");
        if (items.getBirthday().equals("")) {
            bornDate.getEditor().setText("");
        } else {
            bornDate.setValue(LocalDate.parse(items.getBirthday(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (items.getDeathday().equals("")) {
            deathDate.getEditor().setText("");
        } else {
            deathDate.setValue(LocalDate.parse(items.getDeathday(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        phone.setText(items.getPhonenumber());
        email.setText(items.getEmail());
    }

    public EditorChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            if (name.getText().equals("")) {
                new MessageController(MessageController.MessageType.WARNING, "Ошибка изменения", "Поля имя должно быть заполнено");
                return;
            }

            String[] args = {
                    main.getUser().getId(),
                    "changeEditor",
                    ID,
                    name.getText(),
                    surname.getText().equals("") ? "NULL" : surname.getText(),
                    patronymic.getText().equals("") ? "NULL" : patronymic.getText(),
                    sexDict.get(sex.getValue()),
                    (bornDate.getValue() == null) ? "NULL" : bornDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    (deathDate.getValue() == null) ? "NULL" : deathDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    phone.getText().equals("") ? "NULL" : phone.getText(),
                    email.getText().equals("") ? "NULL" : email.getText()
            };

            result = socket.makeRequest(buildQuery(args));

            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e);
            new MessageController("Ошибка",
                    "Не удалось вставить запись", e);
        }
        super.applyChange();
    }
}
