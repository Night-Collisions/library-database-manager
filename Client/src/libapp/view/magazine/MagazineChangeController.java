package libapp.view.magazine;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class MagazineChangeController extends MagazineWinController {
    protected String ID;
    libapp.model.Magazine item;

    public MagazineChangeController(Main main, libapp.model.Magazine item) {
        super(main);
        ID = item.getId();
        this.item = item;
    }

    protected void initialize() {
        super.initialize();
        name.setText(item.getName());
        subject.setDisable(true);
        organization.setDisable(true);
    }

    public MagazineChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
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
        super.applyChange();
    }
}
