package libapp.view.publication.Article;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.Article;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.PublicationProperty;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.io.File;

public class ArticleController extends PublicationProperty<Article> {
    @FXML
    private TableColumn<Article, String> magazineOrWork;
    @FXML
    private TableColumn<Article, String> volume;
    @FXML
    private TableColumn<Article, String> number;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {CreateAuthors()};
        addMenu(menuPropertyTable, null, null, "");

        magazineOrWork.setCellValueFactory(
                new PropertyValueFactory<>("magazineOrWork"));
        volume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));

        table.setItems(dataList);
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getArticles");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                String[] args = new String[i.size()];
                for (int j = 0; j < i.size(); ++j) {
                    if (i.get(j) != null) {
                        args[j] = i.get(j).toString();
                    } else {
                        args[j] = "";
                    }
                }
                dataList.add(new Article(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4]));
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }

    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
