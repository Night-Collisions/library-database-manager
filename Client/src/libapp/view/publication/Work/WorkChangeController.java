package libapp.view.publication.Work;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class WorkChangeController extends WorkWinController {
    protected String ID;

    public WorkChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
        name.setText("ch");
    }

    public WorkChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "changeDigest",
                    ID,
                    name.getText(),
                    ph.getValue().getId(),
                    date.getValue().format(DateTimeFormatter.ofPattern("yyyy"))};

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
