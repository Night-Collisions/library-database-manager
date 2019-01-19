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
    public ArticleController(Main m) {
        main = m;
    }
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
        addMenu(menuPropertyTable);

        magazineOrWork.setCellValueFactory(
                new PropertyValueFactory<>("magazineOrWork"));
        volume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));

        table.setItems(dataList);
    }

    public void onAddMenu() {
        createWindow(
                "publication" +
                        File.separator +
                        "Article" +
                        File.separator +
                        "ArticleAddOverview.fxml",
                new ArticleAddController(main));
        table.getItems().clear();
        fillTable();
    }

    public void onEditMenu() {
        if (table.getSelectionModel().getSelectedItem() != null)
            createWindow(
                    "publication" +
                            File.separator +
                            "Article" +
                            File.separator +
                            "ArticleAddOverview.fxml",
                    new ArticleChangeController(main, table.getSelectionModel().getSelectedItem()));
        table.getItems().clear();
        fillTable();
    }


    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getArticles");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                System.out.println(i);
                if (i.get(2) == null) {
                    dataList.add(new Article(
                            i.get(0).toString(),
                            i.get(1).toString(),
                            i.get(3).toString(),
                            "",
                            ""));
                } else {
                    dataList.add(new Article(
                            i.get(0).toString(),
                            i.get(1).toString(),
                            i.get(2).toString(),
                            i.get(4).toString(),
                            i.get(5).toString()));
                }
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
