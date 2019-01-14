package libapp.view.magazine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import libapp.model.Magazine;
import libapp.view.Main;
import libapp.view.TebleProperty;

public class MagazineController extends TebleProperty<Magazine> {
    @FXML
    private TableColumn<Magazine, String> id;
    @FXML
    private TableColumn<Magazine, String> name;
    @FXML
    private TableColumn<Magazine, String> topic;

    @FXML
    private void initialize() {
        createMenu(new MagazineAddController(), new MagazineChangeController(), "magazine\\MagazineAddOverview.fxml");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        topic.setCellValueFactory(
                new PropertyValueFactory<>("topic"));

        table.setItems(dataList);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
        dataList.add(new Magazine(
                "1",
                "Витек и Пяточки против волка",
                "БлэкСтар"));
    }

    public void deleteRow(String id) {
        // TODO: ебашим запрос к серверу на удаление
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
