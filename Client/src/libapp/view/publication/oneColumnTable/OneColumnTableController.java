package libapp.view.publication.oneColumnTable;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.Table;
import libapp.view.Main;
import libapp.view.TableProperty;

public class OneColumnTableController<T> extends TableProperty<T> {
    protected String columnName;

    @FXML
    public TableColumn<T, String> data;
    @FXML
    public TableColumn<T, String> id;


    public void initialize() {
        MenuItem insert = new MenuItem("Добавить");
        MenuItem delete = new MenuItem("Удалить");

        insert.setOnAction(t -> {
            onAddMenu();
        });

        delete.setOnAction(t -> {
            if (table.getSelectionModel().getSelectedItem() != null)
                deleteWindow(((Table)table.getSelectionModel().getSelectedItem()).getId());
        });

        data.setCellValueFactory(new PropertyValueFactory<>("data"));

        table.setItems(dataList);
    }

    public void setColumnText(String text) {
        data.setText(text);
    }

    public void fillTable(String idFilter) {}

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
