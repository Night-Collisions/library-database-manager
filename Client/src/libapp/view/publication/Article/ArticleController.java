package libapp.view.publication.Article;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.Article;
import libapp.view.publication.PublicationProperty;

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
        addMenu(menuPropertyTable, new ArticleAddController(), new ArticleChangeController(), "publication" + File.separator
                + "Article" + File.separator + "ArticleAddOverview.fxml");

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
