package libapp.view.publication.Article;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;import static libapp.view.publication.Article.ArticleWinController.type.Magazine;

public class ArticleChangeController extends ArticleWinController {
    protected String ID;
    libapp.model.Article item;

    public ArticleChangeController(Main main, libapp.model.Article item) {
        super(main);
        ID = item.getId();
        this.item = item;
    }

    protected void initialize() {
        super.initialize();
        where.setDisable(true);
        typeMagazine.setDisable(true);
        typeCollection.setDisable(true);
        if (item.getNumber().equals("")) {
            typeMagazine.setSelected(false);
//            typeCollection.setSelected(true);
//            labelType.setText("Сборник:");
//            volume.setDisable(true);
//            number.setDisable(true);
        } else {
//            typeMagazine.setSelected(true);
//            typeCollection.setSelected(false);
//            labelType.setText("Журнал:");
//            volume.setText(item.getVolume());
//            number.setText(item.getNumber());
        }
    }

    public ArticleChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        if(name.getText().equals("")) {
            new MessageController(MessageController.MessageType.WARNING, "Есть незаполненые поля.", "Заполните поле название.");
            return;
        }

        if((currentType == Magazine) && (volume.getText().equals(""))) {
            new MessageController(MessageController.MessageType.WARNING, "Есть незаполненые поля.", "Заполните поле том.");
            return;
        }

        if((currentType == Magazine) && (number.getText().equals(""))) {
            new MessageController(MessageController.MessageType.WARNING, "Есть незаполненые поля.", "Заполните поле номер.");
            return;
        }

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            if (currentType == Magazine) {
                String[] args = {
                        main.getUser().getId(),
                        "changeArticle",
                        ID,
                        name.getText(),
                        volume.getText(),
                        number.getText()
                };
                result = socket.makeRequest(buildQuery(args));
            } else {
                String[] args = {
                        main.getUser().getId(),
                        "changeArticle",
                        ID,
                        name.getText(),
                        "-1",
                        "-1"
                };
                result = socket.makeRequest(buildQuery(args));
            }

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
