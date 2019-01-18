package libapp.view.magazine;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class MagazineAddController extends MagazineWinController {
    protected void initialize() {
        super.initialize();
    }

    public MagazineAddController(Main main) {
        super(main);
    }

    protected void applyChange() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            if (subject.getValue() == null) {
                new MessageController(MessageController.MessageType.WARNING, "Не удалось создать журнал.",
                        "Не указанна тема журнала.");
                return;
            }

            if (organization.getValue() == null) {
                new MessageController(MessageController.MessageType.WARNING, "Не удалось создать журнал.",
                        "Не указанна организация.");
                return;
            }

            String[] args = {
                        main.getUser().getId(),
                        "addMagazine",
                        name.getText(),
                        subject.getValue().getId(),
                        organization.getValue().getId()};

            result = socket.makeRequest(buildQuery(args));
            System.out.println(result);
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
