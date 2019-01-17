package libapp.view.publication.oneColumnTable;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ProgramUser;
import libapp.model.Table;
import libapp.view.AllOneColumnTableController;
import libapp.view.Main;
import libapp.view.TableProperty;

public class OneColumnTableController<T> extends AllOneColumnTableController<T> {
    protected String publicationID;

    public void initialize() {
        if ((main.getUser().getType() == ProgramUser.UserType.Librarian)
            || (((main.getUser().getType() == ProgramUser.UserType.Author)
                || (main.getUser().getType() == ProgramUser.UserType.PublishingHouse)))
                && (main.getUser().getPublication().contains(publicationID))) {
            createMenu();
        }

        data.setCellValueFactory(new PropertyValueFactory<>("data"));

        table.setItems(dataList);
    }

    public void fillTable(String idFilter) {};
}
