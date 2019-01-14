package libapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.model.Thesis;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class ThesisController extends PublicationProperty<Thesis> {

    @FXML
    private TableColumn<Thesis, String> magazineOrWork;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {CreateAuthors()};
        CreateMenu(menuPropertyTable, null, null, "");

        magazineOrWork.setCellValueFactory(
                new PropertyValueFactory<>("magazineOrWork"));

        table.setItems(dataList);
    }

    public void fillTable() {
        dataList.add(new Thesis(
                "1",
                "Витек и Пяточки против волка",
                "БлэкСтар"));
        // TODO: ебашим запрос к серверу и заполняем
    }
}
