package libapp.view.publication.AllPublication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.Book.BookAddController;

import java.io.IOException;

public class PublicationAddController {
    private ClientSocket socket;
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
        if (combobox.getValue().equals("Книга")) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("BookAddOverview.fxml"));
                loader.setController(new BookAddController());
                AnchorPane choice = loader.load();

                Stage window = new Stage();
                window.setTitle("Вставка новой книги");
                initWindow(window, choice);
            } catch (IOException e) {
                new MessageController(MessageController.titleErrorGetNewData,
                        MessageController.contentTextErrorGetNewData, e);
            }
        }
    }

    private void initWindow(Stage window, AnchorPane table) {
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(main.getPrimaryStage());

        Scene scene = new Scene(table);
        window.setScene(scene);
        setWindowStage(window);
        window.showAndWait();
    }

    public void setWindowStage(Stage stage) {
        windowStage = stage;
    }

    public void setMain(Main main) {
        this.main = main;
        socket = main.getSocket();
    }
}
