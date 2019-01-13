package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import libapp.ClientSocket;
import libapp.model.TechnicalDoc;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class TechnicalDocController {
    private ClientSocket socket;
    private Main main;
    private ObservableList<TechnicalDoc> techdocs =
            FXCollections.observableArrayList();

    @FXML
    private TableView<TechnicalDoc> table;
    @FXML
    private TableColumn<TechnicalDoc, String> id;
    @FXML
    private TableColumn<TechnicalDoc, String> name;
    @FXML
    private TableColumn<TechnicalDoc, String> organization;

    @FXML
    private void initialize() {
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        organization.setCellValueFactory(
                new PropertyValueFactory<>("organization"));

        table.setItems(techdocs);
    }

    public void fillTable() {
        techdocs.add(new TechnicalDoc(
                "1",
                "Do you speak English?",
                "Yes, of coarse it is"));
        // TODO: ебашим запрос к серверу и заполняем
    }

    private void setEvents() {
        ContextMenu context = new ContextMenu();
        Menu more = new Menu("Подробнее");
        MenuItem keywords = new MenuItem("Ключевые слова");
        MenuItem udc = new MenuItem("УДК");
        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        more.getItems().add(keywords);
        more.getItems().add(udc);

        context.getItems().add(more);
        context.getItems().add(insert);
        context.getItems().add(edit);
        context.getItems().add(delete);

        keywords.setOnAction(t -> {
            PublicationProperty.KeyWordsProperty(table.getSelectionModel().getSelectedItem().getId());
        });

        udc.setOnAction(t -> {
            PublicationProperty.UDCProperty(table.getSelectionModel().getSelectedItem().getId());
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        insert.setOnAction(t -> {
            //TODO: нахуячить
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        edit.setOnAction(t -> {
            //TODO: нахуячить
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        delete.setOnAction(t -> {
            PublicationProperty.Delete(table.getSelectionModel().getSelectedItem().getId());
        });

        table.addEventHandler(MOUSE_CLICKED, t -> {
            if(t.getButton() == MouseButton.SECONDARY) {
                context.show(table, t.getScreenX(), t.getScreenY());
            } else {
                context.hide();
            }
        });
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
