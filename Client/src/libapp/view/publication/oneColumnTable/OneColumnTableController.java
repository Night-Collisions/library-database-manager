package libapp.view.publication.oneColumnTable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.Dictionary;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.TableProperty;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OneColumnTableController<T> extends TableProperty<T> {
    protected String columnName;

    @FXML
    public TableColumn<T, String> column;


    public void initialize() {
        createMenu().getItems().get(1).setVisible(false);
        column.setText(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>("data"));
    }

    public void setColumnText(String text) {
        column.setText(text);
    }

    public void fillTable(String idFilter) {

    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
