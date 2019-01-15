package libapp.view.publication.oneColumnTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.TableProperty;

public class OneColumnTableController<T> extends TableProperty<T> {
    protected String columnName;
    protected String PropertyValueFactory;

    @FXML
    public TableColumn<T, String> column;


    public void initialize() {
        column.setText(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(PropertyValueFactory));
    }

    public void setColumnText(String text) {
        column.setText(text);
    }

    public void fillTable(String idFilter) {}

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
