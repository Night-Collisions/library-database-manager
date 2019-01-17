package libapp.view.publication.Thesis;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class ThesisAddController extends ThesisWinController {
    protected void initialize() {
        super.initialize();
    }

    public ThesisAddController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                        main.getUser().getId(),
                        "addDigestTheses",
                        name.getText(),
                        idDict.get(where.getValue())};

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
