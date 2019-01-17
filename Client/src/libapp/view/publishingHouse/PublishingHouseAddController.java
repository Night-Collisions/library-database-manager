package libapp.view.publishingHouse;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class PublishingHouseAddController extends PublishingHouseWinController {
    protected void initialize() {
        super.initialize();

    }

    public PublishingHouseAddController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "addPublHouse",
                    name.getText(),
                    address.getText(),
                    phone.getText(),
                    email.getText()};

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
