package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.model.Book;

import java.io.IOException;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class BookController {
    private ClientSocket socket;
    private Main main;
    private ObservableList<Book> books =
            FXCollections.observableArrayList();

    @FXML
    private TableView<Book> table;
    @FXML
    private TableColumn<Book, String> id;
    @FXML
    private TableColumn<Book, String> name;
    @FXML
    private TableColumn<Book, String> publishingHouse;
    @FXML
    private TableColumn<Book, String> year;

    @FXML
    private void initialize() {
        setEvents();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        publishingHouse.setCellValueFactory(
                new PropertyValueFactory<>("publishingHouse"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.setItems(books);
        table.setScaleShape(false);
    }

    public void fillTable() {
        // TODO: ебашим запрос к серверу и заполняем
        books.add(new Book(
                "1",
                "Витек и Пяточки против волка",
                "БлэкСтар",
                "3019"));
        books.add(new Book(
                "4",
                "Японские сказки про осьминогов",
                "ХентайСтудио",
                "1941"));
        books.add(new Book(
                "11",
                "Жили у бабуси",
                "Два веселых гуся",
                "1999"));
        books.add(new Book(
                "5",
                "Do you speak English?",
                "Yes, of coarse it is",
                "XX в д.н.э."));
    }

    private void setEvents() {
        ContextMenu context = new ContextMenu();
        Menu more = new Menu("Подробнее");
        MenuItem keywords = new MenuItem("Ключевые слова");
        MenuItem udc = new MenuItem("УДК");
        MenuItem authors = new MenuItem("Авторы");
        MenuItem editors = new MenuItem("Редакторы");
        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        more.getItems().add(keywords);
        more.getItems().add(udc);
        more.getItems().add(authors);
        more.getItems().add(editors);

        context.getItems().add(more);
        context.getItems().add(insert);
        context.getItems().add(edit);
        context.getItems().add(delete);

        keywords.setOnAction(t -> {
            //TODO: нахуячить
        });

        udc.setOnAction(t -> {
            //TODO: нахуячить
        });

        authors.setOnAction(t -> {
            //TODO: нахуячить
        });

        editors.setOnAction(t -> {
            //TODO: нахуячить
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        insert.setOnAction(t -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("BookWinOverview.fxml"));
                loader.setController(new BookAddController());
                AnchorPane ap = loader.load();

                Stage window = new Stage();
                window.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(ap);
                window.setScene(scene);
                window.showAndWait();
            } catch (IOException e) {
                new MessageController(MessageController.titleErrorGetNewData,
                        MessageController.contentTextErrorGetNewData, e);
            }
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        edit.setOnAction(t -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getResource("BookWinOverview.fxml"));
                loader.setController(new BookChangeController());
                AnchorPane ap = loader.load();

                Stage window = new Stage();
                window.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(ap);
                window.setScene(scene);
                window.showAndWait();
            } catch (IOException e) {
                new MessageController(MessageController.titleErrorGetNewData,
                        MessageController.contentTextErrorGetNewData, e);
            }
        });

        //TODO: нахуячить, если это библиотекарь или че то такое
        delete.setOnAction(t -> {
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
        this.socket = main.getSocket();
    }
}
