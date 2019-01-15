package libapp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.model.PublicationTable;
import libapp.model.Table;
import libapp.view.publication.PublicationProperty;


import java.io.IOException;
import java.util.Optional;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;


public class TableProperty<T> {

    public ClientSocket socket;
    public Main main;
    public ObservableList<T> dataList = FXCollections.observableArrayList();

    @FXML
    public TableView<T> table;

    public static void createWindow(String name, Object controller) {
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
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    public void createMenu(Object add, Object change, String form) {
        javafx.scene.control.Menu[] a = {};
        createMenu(a, add, change, form, form);
    }

    public void createMenu(Object add, Object change, String formAdd, String formChange) {
        javafx.scene.control.Menu[] a = {};
        createMenu(a, add, change, formAdd, formChange);
    }

    public void createMenu(javafx.scene.control.Menu[] cascadingMenu, Object add, Object change, String formAdd, String formChange) {
        ContextMenu context = new ContextMenu();

        MenuItem insert = new MenuItem("Добавить");
        MenuItem edit = new MenuItem("Редактировать");
        MenuItem delete = new MenuItem("Удалить");

        context.getItems().addAll(cascadingMenu);
        context.getItems().add(insert);
        context.getItems().add(edit);
        context.getItems().add(delete);

        //TODO: нахуячить, если это библиотекарь или че то такое
        if (add != null) {
            insert.setOnAction(t -> {
                PublicationProperty.createWindow(formAdd, add);
            });
        }

        //TODO: нахуячить, если это библиотекарь или че то такое
        if (change != null) {
            edit.setOnAction(t -> {
                PublicationProperty.createWindow(formChange, change);
            });
        }

        //TODO: нахуячить, если это библиотекарь или че то такое
        delete.setOnAction(t -> {
            if (table.getSelectionModel().getSelectedItem() != null)
                deleteWindow(((Table)table.getSelectionModel().getSelectedItem()).getId());
        });

        table.addEventHandler(MOUSE_CLICKED, t -> {
            if(t.getButton() == MouseButton.SECONDARY) {
                context.show(table, t.getScreenX(), t.getScreenY());
            } else {
                context.hide();
            }
        });
    }

    public void deleteWindow(String id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Удаление.");
        alert.setHeaderText(null);
        alert.setContentText("Вы действительно хотите удалить?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            deleteRow(id);
        }
    }

    public void deleteRow(String id) {}
}
