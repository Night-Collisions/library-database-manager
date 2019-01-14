package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.Work;

public class WorkController extends PublicationProperty<Work> {

    @FXML
    private TableColumn<Work, String> publishingHouse;
    @FXML
    private TableColumn<Work, String> year;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {CreateEditors()};
        CreateMenu(menuPropertyTable, new WorkAddController(), new WorkChangeController(), "BookWinOverview.fxml");

        publishingHouse.setCellValueFactory(
                new PropertyValueFactory<>("publishingHouse"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.setItems(dataList);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
        dataList.add(new Work(
                "1",
                "Применение аккустических систем для массажа ануса",
                "ДВФУ",
                "2018"));
        dataList.add(new Work(
                "2",
                "Жил был научный труд",
                "И сделал он пруд",
                "(напрудил)"));
        dataList.add(new Work(
                "В",
                "И",
                "Т",
                "Я"));
        dataList.add(new Work(
                "4",
                "Прикладное использование школьной алгебры при покупках в магазине",
                "MIT",
                "2018"));

    }
}
