package libapp.view.publication;

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
import libapp.view.*;

import java.awt.*;
import java.io.IOException;

public class PublicationProperty<T>  extends TebleProperty<T> {

    @FXML
    public TableColumn<T, String> id;
    @FXML
    public TableColumn<TechnicalDoc, String> name;

    public void initProperty() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    protected static void CreateTableProperty(Object connect, String columnName, String idFilter) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PropertyTableWinOverview.fxml"));
            loader.setController(connect);
            AnchorPane udcTable = loader.load();

            //Todo: переделать этот кастыль
            if (connect instanceof UDCPropertyController) {
                UDCPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (connect instanceof KeywordPropertyController) {
                KeywordPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (connect instanceof AuthorsPropertyController) {
                AuthorsPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (connect instanceof EditorsPropertyController) {
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

    public javafx.scene.control.MenuItem CreateUDC() {
        javafx.scene.control.MenuItem udc = new MenuItem("УДК");
        udc.setOnAction(t -> {
            CreateTableProperty(new UDCPropertyController(), "УДК для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return udc;
    }

    public javafx.scene.control.MenuItem CreateKeyWords() {
        MenuItem keywords = new MenuItem("Ключевые слова");
        keywords.setOnAction(t -> {
            CreateTableProperty(new KeywordPropertyController(), "Ключевые слова для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return keywords;
    }

    public javafx.scene.control.MenuItem CreateAuthors() {
        MenuItem authors = new MenuItem("Авторы");
        authors.setOnAction(t -> {
            CreateTableProperty(new AuthorsPropertyController(), "Авторы для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return authors;
    }

    public javafx.scene.control.MenuItem CreateEditors() {
        MenuItem editors = new MenuItem("Редакторы");
        editors.setOnAction(t -> {
            CreateTableProperty(new EditorsPropertyController(), "Редакторы для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return editors;
    }

    public void addMenu(javafx.scene.control.MenuItem moreTableProperty[], Object add, Object change, String form) {
        addMenu(moreTableProperty, add, change, form, form);
    }

    public void addMenu(javafx.scene.control.MenuItem moreTableProperty[], Object add, Object change, String formAdd, String formChange) {
        javafx.scene.control.Menu more[] = {new Menu("Подробнее")};

        more[0].getItems().add(CreateUDC());
        more[0].getItems().add(CreateKeyWords());
        more[0].getItems().addAll(moreTableProperty);

        createMenu(more, add, change, formAdd, formChange);
    }

    public void deleteRow(String id) {
            //TODO:удалять туту
    }

    public void fillTable() {}

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
