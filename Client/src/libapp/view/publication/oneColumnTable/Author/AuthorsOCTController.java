package libapp.view.publication.oneColumnTable.Author;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.Keyword;

public class AuthorsOCTController {

    @FXML
    private TableView<Keyword> table;
    @FXML
    private TableColumn<Keyword, String> column;

    @FXML
    private void initialize() {
        column.setCellValueFactory(new PropertyValueFactory<>("word"));
    }

    //Для вывода всех слов
    public void fillTable(String idFilter) {
    }

    public void setColumnText(String text) {
        column.setText(text);
    }
}
