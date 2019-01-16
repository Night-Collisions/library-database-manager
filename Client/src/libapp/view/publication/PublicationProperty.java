package libapp.view.publication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.ProgramUser;
import libapp.model.PublicationTable;
import libapp.model.Table;
import libapp.model.TechnicalDoc;
import libapp.model.User;
import libapp.view.*;
import libapp.view.publication.oneColumnTable.Author.AuthorsOCTController;
import libapp.view.publication.oneColumnTable.Edithor.EditorsOCTController;
import libapp.view.publication.oneColumnTable.KeyWords.KeywordOCTController;
import libapp.view.publication.oneColumnTable.OneColumnTableController;
import libapp.view.publication.oneColumnTable.UDC.UDCOCTController;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static javafx.scene.input.MouseButton.SECONDARY;
import static libapp.ProgramUser.UserType.Librarian;

public class PublicationProperty<T>  extends TableProperty<T> {

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
            loader.setLocation(Main.class.getResource(
                    "publication" +
                            File.separator +
                            "oneColumnTable" +
                            File.separator +
                            "OneColumnTableOverview.fxml"));
            loader.setController(connect);
            AnchorPane udcTable = loader.load();

            OneColumnTableController controller = loader.getController();
            controller.fillTable(idFilter);
            controller.setColumnText(columnName + " " + idFilter);

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
            CreateTableProperty(new UDCOCTController(main,
                            ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId()),
                    "УДК для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return udc;
    }

    public javafx.scene.control.MenuItem CreateKeyWords() {
        MenuItem keywords = new MenuItem("Ключевые слова");
        keywords.setOnAction(t -> {
            CreateTableProperty(new KeywordOCTController(main,
                            ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId()),
                    "Ключевые слова для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return keywords;
    }

    public javafx.scene.control.MenuItem CreateAuthors() {
        MenuItem authors = new MenuItem("Авторы");
        authors.setOnAction(t -> {
            CreateTableProperty(new AuthorsOCTController(main,
                            ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId()),
                    "Авторы для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return authors;
    }

    public javafx.scene.control.MenuItem CreateEditors() {
        MenuItem editors = new MenuItem("Редакторы");
        editors.setOnAction(t -> {
            CreateTableProperty(new EditorsOCTController(main,
                            ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId()),
                    "Редакторы для id ",
                    ((PublicationTable)table.getSelectionModel().getSelectedItem()).getId());
        });
        return editors;
    }

    public void addMenu(javafx.scene.control.MenuItem moreTableProperty[]) {
        javafx.scene.control.Menu more[] = {new Menu("Подробнее")};

        more[0].getItems().add(CreateUDC());
        more[0].getItems().add(CreateKeyWords());
        more[0].getItems().addAll(moreTableProperty);

        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        insert.setOnAction(t -> {
            onAddMenu();
        });

        edit.setOnAction(t -> {
            if (table.getSelectionModel().getSelectedItem() != null)
                onEditMenu();
        });

        delete.setOnAction(t -> {
            if (table.getSelectionModel().getSelectedItem() != null)
                deleteWindow(((Table)table.getSelectionModel().getSelectedItem()).getId());
        });

        insert.setVisible(main.getUser().getType() == Librarian);
        edit.setVisible((main.getUser().getType() == Librarian) ||
                (main.getUser().getType() == ProgramUser.UserType.PublishingHouse) ||
                (main.getUser().getType() == ProgramUser.UserType.Author));
        delete.setVisible(main.getUser().getType() == Librarian);

        table.onMouseClickedProperty().set(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0) {
                if ((table.getSelectionModel().getSelectedItem() != null) && (arg0.getButton() == SECONDARY))
                    edit.setDisable(!(main.getUser().getType() == Librarian)
                            || (((main.getUser().getType() == ProgramUser.UserType.PublishingHouse)
                            || (main.getUser().getType() == ProgramUser.UserType.Author))
                            && (main.getUser().getPublication().contains(((Table) table.getSelectionModel().getSelectedItem()).getId()))));
            }
        });

        MenuItem items[] = {insert, edit, delete};
        createMenu(more, items);
    }

    public void deleteRow(String id) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "deletePublication" +
                            ClientSocket.argSep +
                            id);

            if (result.equals("ok")) {
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            } else {
                //TODO: не удалиласб, пока кидаю просто эксепшн
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController("Не удалоь удалить запись",
                    "Запись связана с другой таблицей", e);
        }
    }

    public void fillTable() {}
}
