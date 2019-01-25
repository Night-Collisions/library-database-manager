package libapp.view.publication.Book;

import libapp.ClientSocket;
import libapp.model.PublishingHouse;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class BookChangeController extends BookWinController {
    protected String ID;
    libapp.model.Book item;

    public BookChangeController(Main main, libapp.model.Book item) {
        super(main);
        this.ID = item.getId();
        this.item = item;
    }

    protected void initialize() {
        ph.setDisable(true);
        super.initialize();
        name.setText(item.getName());
        ph.setValue( new PublishingHouse("-1", item.getPublishingHouse(), "", "", ""));
        ph.setDisable(true);
        date.setText(item.getYear());
    }

    public BookChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        if(date.getText().equals("")) {
            new MessageController(MessageController.MessageType.WARNING, "Есть не заполненые поля", "Заполните поле год.");
            return;
        }

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "changeBook",
                    ID,
                    name.getText(),
                    date.getText()};

            result = socket.makeRequest(buildQuery(args));

            if (!result.equals("ok")) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println(e);
            new MessageController("Ошибка",
                    "Не удалось вставить запись", e);
        }
        super.applyChange();
    }
}
