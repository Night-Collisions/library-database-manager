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
        dataList.add(new Article(
                "1",
                "Витек и Пяточки против волка",
                "БлэкСтар",
                "3019",
                "122"));
        // TODO: ебашим запрос к серверу и заполняем
    }
}
