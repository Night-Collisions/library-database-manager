package libapp.view.publication.AllPublication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.Pair;
import libapp.view.MessageController;
import libapp.view.PropertyWin;
import libapp.view.TableProperty;
import libapp.view.publication.Article.ArticleAddController;
import libapp.view.publication.Book.BookAddController;
import libapp.view.publication.TechnicalDoc.TechnicalDocAddController;
import libapp.view.publication.Thesis.ThesisAddController;
import libapp.view.publication.Work.WorkAddController;

import java.io.File;

public class PublicationAddController extends PropertyWin {
    private ObservableList<String> types =
            FXCollections.observableArrayList(
                    "Книга", "Сборник трудов", "Статья", "Тезисы",
                    "Техническая документация");
    private ObservableList<Pair<String, Object>> windows =
            FXCollections.observableArrayList(
                    new Pair<>("publication" + File.separator + "Book" + File.separator + "BookAddOverview.fxml", new BookAddController()),
                    new Pair<>("publication" + File.separator + "Book" + File.separator + "BookAddOverview.fxml", new WorkAddController()),
                    new Pair<>("publication" + File.separator + "Article" + File.separator + "ArticleAddOverview.fxml", new ArticleAddController()),
                    new Pair<>("publication" + File.separator + "Thesis" + File.separator + "ThesisAddOverview.fxml", new ThesisAddController()),
                    new Pair<>("publication" + File.separator + "TechnicalDoc" + File.separator + "TechnicalDocAddOverview.fxml", new TechnicalDocAddController())
            );
    @FXML
    private ComboBox<String> combobox;

    @FXML
    protected void initialize() {
        combobox.setItems(types);
    }

    protected void applyChange() {
        int index = combobox.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            TableProperty.createWindow(windows.get(index).getKey(), windows.get(index).getValue());
        } else {
            new MessageController(MessageController.MessageType.WARNING,
                    "Не удалось создать публикацию.",
                    "Не указан тип публикациеи.",
                    "Требуется указать тип создаваемой публикации.");
            return;
        }

        super.applyChange();
    }
}
