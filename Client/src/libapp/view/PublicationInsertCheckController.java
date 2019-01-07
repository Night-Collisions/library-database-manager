package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PublicationInsertCheckController {
    private Stage windowStage;
    private Main main;
    private ObservableList<String> types =
            FXCollections.observableArrayList(
                    "Книга", "Сборник трудов", "Статья", "Тезисы",
                    "Техническая документация");
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Button button;

    @FXML
    private void initialize() {
        combobox.setItems(types);
    }

    @FXML
    private void showCorrectWindow() {
        // В зависмости от того, какое поле в combobox мы выбрали
        // Мы открываем соответствующее окно для вставки
    }
}
