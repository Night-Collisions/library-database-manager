package libapp.view.publication.Book;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.Book;
import libapp.view.publication.PublicationProperty;

import java.io.File;


public class BookController extends PublicationProperty<Book> {

    @FXML
    private TableColumn<Book, String> publishingHouse;
    @FXML
    private TableColumn<Book, String> year;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {CreateAuthors(), CreateEditors()};
        addMenu(menuPropertyTable, new BookAddController(), new BookChangeController(), "publication" + File.separator + "Book" + File.separator + "BookAddOverview.fxml");

        publishingHouse.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.setItems(dataList);
        table.setScaleShape(false);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
        dataList.add(new Book(
                "1",
                "Витек и Пяточки против волка",
                "БлэкСтар",
                "3019"));
        dataList.add(new Book(
                "4",
                "Японские сказки про осьминогов",
                "ХентайСтудио",
                "1941"));
        dataList.add(new Book(
                "11",
                "Жили у бабуси",
                "Два веселых гуся",
                "1999"));
        dataList.add(new Book(
                "5",
                "Do you speak English?",
                "Yes, of coarse it is",
                "XX в д.н.э."));
    }
}
