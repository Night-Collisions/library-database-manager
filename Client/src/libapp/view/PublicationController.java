package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import libapp.model.Publication;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class PublicationController {
    private Main main;
    private ObservableList<Publication> publications =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Publication> table;
    @FXML
    private TableColumn<Publication, String> id;
    @FXML
    private TableColumn<Publication, String> type;
    @FXML
    private TableColumn<Publication, String> name;

    @FXML
    private void initialize() {
        fillTable();
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        table.setItems(publications);
    }

    private void fillTable() {
        // TODO: ебашим запрос к серверу я заполняем
        publications.add(new Publication("1", "епта", "ывавыа"));
        publications.add(new Publication("2", "123", "хуепывата"));
        publications.add(new Publication("3", "fsdsdf", "хуеывапта"));
        publications.add(new Publication("4", "еxxптаxxx", "хуеыыыпта"));

    }

    private void setEvents() {
        ContextMenu context = new ContextMenu();
        MenuItem keywords = new MenuItem("Ключевые слова");
        MenuItem udc = new MenuItem("УДК");
        context.getItems().add(keywords);
        context.getItems().add(udc);

        keywords.setOnAction(t -> {
            //TODO: нахуячить
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        udc.setOnAction(t -> {
            //TODO: нахуячить
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
    }
}
