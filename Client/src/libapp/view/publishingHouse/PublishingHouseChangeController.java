package libapp.view.publishingHouse;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;

public class PublishingHouseChangeController extends PublishingHouseWinController {
    protected String ID;
    libapp.model.PublishingHouse items;

    public PublishingHouseChangeController(Main main,
                                           libapp.model.PublishingHouse items) {
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

    public PublishingHouseChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            //TODO

            items.setName(name.getText());
            items.setEmail(email.getText());
            items.setPhonenumber(phone.getText());
            items.setAddress(email.getText());

            String[] args = {
                    main.getUser().getId(),
                    "changePublHouse",
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
            new MessageController("Ошибка",
                    "Не удалось вставить запись", e);
        }
    }
}
