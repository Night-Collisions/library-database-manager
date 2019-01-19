package libapp.view.publication.Work;

import libapp.ClientSocket;
import libapp.model.PublishingHouse;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class WorkChangeController extends WorkWinController {
    protected String ID;
    libapp.model.Work item;

    public WorkChangeController(Main main, libapp.model.Work item) {
        super(main);
        ID = item.getId();
        this.item = item;
    }

    protected void initialize() {
        super.initialize();
        name.setText(item.getName());
        ph.setValue( new PublishingHouse("-1", item.getPublishingHouse(), "", "", ""));
        ph.setDisable(true);
        date.setText(item.getYear());
    }

    public WorkChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            if(date.getText().equals("")) {
                new MessageController(MessageController.MessageType.WARNING, "Есть не заполненые поля", "Заполните поле год.");
                return;
            }

            String[] args = {
                    main.getUser().getId(),
                    "changeDigest",
                    ID,
                    name.getText(),
                    date.getText()
            };

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
