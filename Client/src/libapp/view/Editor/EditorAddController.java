package libapp.view.Editor;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class EditorAddController extends EditorWinController {
    protected void initialize() {
        super.initialize();
        sexDict.put("женский", "1");
        sexDict.put("мужской ", "0");
        sex.setEditable(false);
        sex.getSelectionModel().selectFirst();
    }

    public EditorAddController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "addEditor",
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
//            System.out.println(buildQuery(args));
//            System.out.println(result);
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
