package libapp.view.organization;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class OrganizationChangeController extends OrganizationWinController {
    protected String ID;
    libapp.model.Organization items;

    public OrganizationChangeController(Main main, libapp.model.Organization items) {
        super(main);
        ID = items.getId();
        this.items = items;
    }

    protected void initialize() {
        super.initialize();
        name.setText(items.getName());
        address.setText(items.getAddress());
        phone.setText(items.getPhonenumber());
        email.setText(items.getEmail());
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
                    phone.getText().equals("") ? "NULL" : phone.getText(),
                    email.getText().equals("") ? "NULL" : email.getText()};

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
