package libapp.view.publication.AllPublication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import libapp.view.PropertyWin;

public class PublicationAddController extends PropertyWin {
    private ObservableList<String> types =
            FXCollections.observableArrayList(
                    "Книга", "Сборник трудов", "Статья", "Тезисы",
                    "Техническая документация");
    @FXML
    private ComboBox<String> combobox;

    //TODO: данила переделать
    @FXML
    protected void initialize() {
        combobox.setItems(types);
    }

    protected void applyChange() {
        super.applyChange();
    }
}
