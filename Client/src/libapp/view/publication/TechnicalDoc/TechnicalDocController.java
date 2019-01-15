package libapp.view.publication.TechnicalDoc;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.TechnicalDoc;
import libapp.view.publication.PublicationProperty;

public class TechnicalDocController extends PublicationProperty<TechnicalDoc> {

    @FXML
    private TableColumn<TechnicalDoc, String> organization;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {};
        addMenu(menuPropertyTable, new TechnicalDocAddController(), new TechnicalDocChange(), "publication\\TechnicalDoc\\TechnicalDocAddOverview.fxml");

        organization.setCellValueFactory(
                new PropertyValueFactory<>("organization"));

        table.setItems(dataList);
    }

    public void fillTable() {
        dataList.add(new TechnicalDoc(
                "1",
                "Do you speak English?",
                "Yes, of coarse it is"));
        // TODO: ебашим запрос к серверу и заполняем
    }
}
