package libapp.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.model.PublicationTable;
import libapp.model.TechnicalDoc;

import java.awt.*;
import java.io.IOException;

public class PublicationProperty<T>  extends TebleProperty<T> {

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

    void addMenu(javafx.scene.control.MenuItem moreTableProperty[], Object add, Object change, String form) {
        addMenu(moreTableProperty, add, change, form, form);
    }

    void addMenu(javafx.scene.control.MenuItem moreTableProperty[], Object add, Object change, String formAdd, String formChange) {
        javafx.scene.control.Menu more[] = {new Menu("Подробнее")};

        more[0].getItems().add(CreateUDC());
        more[0].getItems().add(CreateKeyWords());
        more[0].getItems().addAll(moreTableProperty);

        createMenu(more, add, change, formAdd, formChange);
    }

    void deleteRow(String id) {
        if (isDelete(id, "публикацию")) {
            //TODO:удалять туту
        }
    }

    public void fillTable() {}

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
