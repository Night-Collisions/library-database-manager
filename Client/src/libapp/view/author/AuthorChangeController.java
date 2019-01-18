package libapp.view.author;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class AuthorChangeController extends AuthorWinController {
    protected String authorID;
    libapp.model.Author item;

    public AuthorChangeController(Main main, libapp.model.Author item) {
        super(main);
        authorID = item.getId();
        this.item = item;
        sexDict.put("женский", "1");
        sexDict.put("мужской", "0");
    }

    protected void initialize() {
        super.initialize();
        name.setText(item.getName());
        surname.setText(item.getSurname());
        patronymic.setText(item.getPatronymic());
        sex.setValue(item.getSex().equals("1") ? "женский" : "мужской");
        if (item.getBirthday().equals("")) {
            bornDate.getEditor().setText("");
        } else {
            bornDate.setValue(LocalDate.parse(item.getBirthday(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (item.getDeathday().equals("")) {
            deathDate.getEditor().setText("");
        } else {
            deathDate.setValue(LocalDate.parse(item.getDeathday(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        phone.setText(item.getPhonenumber());
        email.setText(item.getEmail());
    }

    public AuthorChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "changeAuthor",
                    authorID,
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
            System.out.println(buildQuery(args));
            System.out.println(result);
            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e);
            new MessageController("Ошибка",
                    "Не удалось вставить запись", e);
        }
    }
}
