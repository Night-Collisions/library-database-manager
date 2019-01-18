package libapp.view.publication.Book;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class BookChangeController extends BookWinController {
    protected String ID;

    public BookChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        ph.setDisable(true);
        super.initialize();
    }

    public BookChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "changeBook",
                    ID,
                    name.getText(),
                    date.getValue().format(DateTimeFormatter.ofPattern("yyyy"))};

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
