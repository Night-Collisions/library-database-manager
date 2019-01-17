package libapp.view.author;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class AuthorAddController extends AuthorWinController {
    protected void initialize() {
        super.initialize();
        sexDict.put("мужской", "0");
        sexDict.put("женский", "1");
        sex.setEditable(false);
    }

    public AuthorAddController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "addAuthor",
                    surname.getText(),
                    name.getText(),
                    patronymic.getText(),
                    sexDict.get(sex.getValue()),
                    bornDate.getValue().format(DateTimeFormatter.ofPattern("dd--mm--yyyy")),
                    deathDate.getValue().format(DateTimeFormatter.ofPattern("dd--mm--yyyy")),
                    phone.getText(),
                    email.getText()
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
    }
}
