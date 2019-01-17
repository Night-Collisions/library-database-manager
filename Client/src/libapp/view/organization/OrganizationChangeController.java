package libapp.view.organization;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class OrganizationChangeController extends OrganizationWinController {
    protected String ID;

    public OrganizationChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
    }

    public OrganizationChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "changeOrganization",
                    ID,
                    name.getText(),
                    address.getText(),
                    phone.getText(),
                    email.getText()};

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
