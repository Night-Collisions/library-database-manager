package libapp.view.organization;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class OrganizationAddController extends OrganizationWinController {
    protected void initialize() {
        super.initialize();

    }

    public OrganizationAddController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                        main.getUser().getId(),
                        "addOrganization",
                        name.getText(),
                        address.getText(),
                        phone.getText(),
                        email.getText()};

            result = socket.makeRequest(buildQuery(args));
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
