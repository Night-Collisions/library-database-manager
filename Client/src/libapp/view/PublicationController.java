package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.model.Publication;

import java.io.IOException;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class PublicationController {
    private Stage windowStage;
    private Main main;
    private ClientSocket socket;
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
            try {
                String idFilter =
                        table.getSelectionModel().getSelectedItem().toString().split(",")[0].substring(1);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("KeywordOverview.fxml"));
                AnchorPane keywordsTable = loader.load();

                KeywordController controller = loader.getController();
                controller.fillTable(idFilter);

                Stage window = new Stage();
                initWindow(window, keywordsTable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        udc.setOnAction(t -> {
            try {
                String idFilter =
                        table.getSelectionModel().getSelectedItem().toString().split(",")[0].substring(1);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("UDCOverview.fxml"));
                AnchorPane udcTable = loader.load();

                UDCController controller = loader.getController();
                controller.fillTable(idFilter);

                Stage window = new Stage();
                initWindow(window, udcTable);
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public void setSocket(ClientSocket socket) {
        this.socket = socket;
    }

    public void setWindowStage(Stage stage) {
        windowStage = stage;
    }

    private void initWindow(Stage window, AnchorPane table) {
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(main.getPrimaryStage());

        Scene scene = new Scene(table);
        window.setScene(scene);
        //controller.setSocket(socket);
        setWindowStage(window);
        window.showAndWait();
    }
}
