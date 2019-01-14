package libapp.view.Editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import libapp.ClientSocket;
import libapp.model.Editor;
import libapp.view.Main;
import libapp.view.TebleProperty;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class EditorController extends TebleProperty<Editor> {
    @FXML
    private TableColumn<Editor, String> id;
    @FXML
    private TableColumn<Editor, String> surname;
    @FXML
    private TableColumn<Editor, String> name;
    @FXML
    private TableColumn<Editor, String> patronymic;
    @FXML
    private TableColumn<Editor, String> birthday;
    @FXML
    private TableColumn<Editor, String> deathday;
    @FXML
    private TableColumn<Editor, String> phonenumber;
    @FXML
    private TableColumn<Editor, String> email;

    @FXML
    private void initialize() {
        createMenu(new EditorAddController(), new EditorChangeController(), "author\\AuthorAddOverview.fxml");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        deathday.setCellValueFactory(new PropertyValueFactory<>("deathday"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(dataList);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
    }

    public void fillTable(String idFilter) {
        // TODO: ебашим запрос к серверу и заполняем
    }

}
