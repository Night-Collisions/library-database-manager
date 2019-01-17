package libapp.view.publication.TechnicalDoc;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class TechnicalDocAddController extends TechnicalDocWinController {
    protected void initialize() {
        super.initialize();
    }

    public TechnicalDocAddController(Main main) {
        super(main);
      //  organization.getSelectionModel().selectFirst();
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "addDocs",
                    name.getText(),
                    organization.getValue().getId()};

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
