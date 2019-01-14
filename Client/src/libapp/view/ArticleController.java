package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.Article;

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
        CreateMenu(menuPropertyTable, null, null, "");

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
