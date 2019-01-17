package libapp.view.publication.Thesis;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class ThesisChangeController extends ThesisWinController {
    protected String ID;

    public ThesisChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
    }

    public ThesisChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            //TODO
            String[] args = {
                    main.getUser().getId(),
                    "changeTheses",
                    ID,
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
