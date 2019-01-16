package libapp.view.publication.oneColumnTable;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.view.Main;
import libapp.view.TableProperty;

public class OneColumnTableController<T> extends TableProperty<T> {
    protected String columnName;

    @FXML
    public TableColumn<T, String> data;
    @FXML
    public TableColumn<T, String> id;


    public void initialize() {
        createMenu().getItems().get(1).setVisible(false);
        id.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        data.setCellValueFactory(new PropertyValueFactory<>("volume"));

        table.setItems(dataList);
    }

    public void setColumnText(String text) {
        data.setText(text);
    }

    public void fillTable(String idFilter) {

    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
