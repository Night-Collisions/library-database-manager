package libapp.view.magazine;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class MagazineChangeController extends MagazineWinController {
    protected String ID;

    public MagazineChangeController(Main main, String id) {
        super(main);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
    }

    public MagazineChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args = {
                    main.getUser().getId(),
                    "changeMagazine",
                    ID,
                    name.getText()
            };

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
