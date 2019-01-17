package libapp.view.publication.TechnicalDoc;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class TechnicalDocChangeController extends TechnicalDocWinController {
    protected String ID;

    public TechnicalDocChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
        name.setText("Ch");
    }

    public TechnicalDocChangeController(Main main) {
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
                    "changeDocs",
                    ID,
                    name.getText(),
                    organization.getValue().getId()};

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
