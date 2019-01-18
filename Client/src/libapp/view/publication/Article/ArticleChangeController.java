package libapp.view.publication.Article;

import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;

import static libapp.QueryParser.buildQuery;import static libapp.view.publication.Article.ArticleWinController.type.Magazine;

public class ArticleChangeController extends ArticleWinController {
    protected String ID;

    public ArticleChangeController(Main main, String id) {
        super(main);
        where.setDisable(true);
        ID = id;
    }

    protected void initialize() {
        super.initialize();
    }

    public ArticleChangeController(Main main) {
        super(main);
    }

    protected void applyChange() {
        super.applyChange();
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            if (currentType == Magazine) {
                String[] args = {
                        main.getUser().getId(),
                        "changeArticle",
                        ID,
                        name.getText(),
                        issue.getText(),
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
    }
}
