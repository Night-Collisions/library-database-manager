package libapp.view.author;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class AuthorChangeController extends AuthorWinController {
    protected String authorID;

    public AuthorChangeController(Main main, String id) {
        super(main);
        authorID = id;
        sexDict.put("женский", "1");
        sexDict.put("мужской", "0");
    }

    protected void initialize() {
        super.initialize();
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
                    surname.getText(),
                    patronymic.getText(),
                    sexDict.get(sex.getValue()),
                    bornDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    deathDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    phone.getText(),
                    email.getText()
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
