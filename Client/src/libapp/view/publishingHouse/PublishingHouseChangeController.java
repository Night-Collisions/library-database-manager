package libapp.view.publishingHouse;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class PublishingHouseChangeController extends PublishingHouseWinController {
    protected String ID;

    public PublishingHouseChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
    }

    public PublishingHouseChangeController(Main main) {
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
                    "changePublHouse",
                    ID,
                    name.getText(),
                    address.getText(),
                    phone.getText(),
                    email.getText()};

            result = socket.makeRequest(buildQuery(args));
            System.out.println(buildQuery(args));
            System.out.println(result);
            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController("Ошибка",
                    "Не удалось вставить запись", e);
        }
    }
}
