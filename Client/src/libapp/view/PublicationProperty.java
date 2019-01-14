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
import libapp.model.PublicationTable;
import libapp.model.TechnicalDoc;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;

public class PublicationProperty<T> {

    ClientSocket socket;
    Main main;
    ObservableList<T> dataList = FXCollections.observableArrayList();

    @FXML
    TableView<T> table;
    @FXML
    TableColumn<T, String> id;
    @FXML
    TableColumn<TechnicalDoc, String> name;

    void initProperty() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    protected static void CreateTableProperty(Object conect, String columnName, String idFilter) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PropertyTableWinOverview.fxml"));
            loader.setController(conect);
            AnchorPane udcTable = loader.load();

            if (conect instanceof UDCPropertyController) {
                UDCPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (conect instanceof KeywordPropertyController) {
                KeywordPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (conect instanceof AuthorsPropertyController) {
                AuthorsPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (conect instanceof EditorsPropertyController) {
                EditorsPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            }

            Stage window = new Stage();
            window.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 5);
            window.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.5);
            window.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(udcTable);
            window.setScene(scene);
            window.showAndWait();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    javafx.scene.control.MenuItem CreateUDC() {
        javafx.scene.control.MenuItem udc = new MenuItem("УДК");
        udc.setOnAction(t -> {
            CreateTableProperty(new UDCPropertyController(), "УДК для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return udc;
    }

    javafx.scene.control.MenuItem CreateKeyWords() {
        MenuItem keywords = new MenuItem("Ключевые слова");
        keywords.setOnAction(t -> {
            CreateTableProperty(new KeywordPropertyController(), "Ключевые слова для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return keywords;
    }

    javafx.scene.control.MenuItem CreateAuthors() {
        MenuItem authors = new MenuItem("Авторы");
        authors.setOnAction(t -> {
            CreateTableProperty(new AuthorsPropertyController(), "Авторы для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return authors;
    }

    javafx.scene.control.MenuItem CreateEditors() {
        MenuItem editors = new MenuItem("Редакторы");
        editors.setOnAction(t -> {
            CreateTableProperty(new EditorsPropertyController(), "Редакторы для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return editors;
    }

    void CreateMenu(javafx.scene.control.MenuItem moreTableProperty[], Object add, Object change, String form) {
        CreateMenu(moreTableProperty, add, change, form, form);
    }

    void CreateMenu(javafx.scene.control.MenuItem moreTableProperty[], Object add, Object change, String formAdd, String formChange) {
        ContextMenu context = new ContextMenu();

        javafx.scene.control.Menu more = new Menu("Подробнее");

        more.getItems().add(CreateUDC());
        more.getItems().add(CreateKeyWords());

        for(int i = 0; i < moreTableProperty.length; i++) {
            more.getItems().add(moreTableProperty[i]);
        }

        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        context.getItems().add(more);
        context.getItems().add(insert);
        context.getItems().add(edit);
        context.getItems().add(delete);

        //TODO: нахуячить, если это библиотекарь или че то такое
        if (add != null) {
            insert.setOnAction(t -> {
                PublicationProperty.CreateWindow(formAdd, add);
            });
        }

        //TODO: нахуячить, если это библиотекарь или че то такое
        if (change != null) {
            edit.setOnAction(t -> {
                PublicationProperty.CreateWindow(formChange, change);
            });
        }

        //TODO: нахуячить, если это библиотекарь или че то такое
        delete.setOnAction(t -> {
            Delete(((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });

        table.addEventHandler(MOUSE_CLICKED, t -> {
            if(t.getButton() == MouseButton.SECONDARY) {
                context.show(table, t.getScreenX(), t.getScreenY());
            } else {
                context.hide();
            }
        });
    }

    public static void UDCProperty(String idFilter) {
        CreateTableProperty(new UDCPropertyController(), "УДК для id ", idFilter);
    }

    public static void KeyWordsProperty(String idFilter) {
        CreateTableProperty(new KeywordPropertyController(), "Ключевые слова для id ", idFilter);
    }

    public static void AuthorsProperty(String idFilter) {
        CreateTableProperty(new AuthorsPropertyController(), "Авторы для id ", idFilter);
    }

    public static void EditorsProperty(String idFilter) {
        CreateTableProperty(new EditorsPropertyController(), "Редакторы для id ", idFilter);
    }

    public static void CreateWindow(String name, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(name));
            loader.setController(controller);
            AnchorPane ap = loader.load();

            Scene scene = new Scene(ap);

            Stage window = new Stage();
            window.initModality(Modality.WINDOW_MODAL);
            window.setScene(scene);
            window.showAndWait();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public static void Delete(String id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удалить публикацию?");
        alert.setHeaderText(null);
        alert.setContentText("Вы действительно хотите удалить публикацию с id: " + id + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //TODO: Витя захуярь запрос на удаление
        } else {
            alert.close();
        }
    }

    public void fillTable() {}

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
