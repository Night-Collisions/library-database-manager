package libapp.view.publication.oneColumnTable.Edithor;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.Keyword;
import libapp.view.Main;

public class EditorsOCTController {
    private Main main;
    private ClientSocket socket;
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

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
