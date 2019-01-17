package libapp.view.publication.Article;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import java.time.format.DateTimeFormatter;

import static libapp.QueryParser.buildQuery;

public class ArticleAddController extends ArticleWinController{
    protected void initialize() {
        super.initialize();
    }

    public ArticleAddController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();

        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);

            String[] args;
            if (typeMagazine.isSelected()) {
                args = new String[]{
                        main.getUser().getId(),
                        "addMagazineArticle",
                        name.getText(),
                        where.getValue().getId(),
                        issue.getText(),
                        number.getText()};
            } else {
                args = new String[]{
                        main.getUser().getId(),
                        "addDigestArticle",
                        name.getText(),
                        where.getValue().getId()};
            }

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
