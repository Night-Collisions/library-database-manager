package libapp.view.AllOneColumnTable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.ProgramUser;
import libapp.model.OneColumnTable;
import libapp.model.Table;
import libapp.view.MessageController;
import libapp.view.TableProperty;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static libapp.view.MessageController.contentTextErrorDB;
import static libapp.view.MessageController.titleErrorDB;

public class AllOneColumnTableController<T> extends TableProperty<T> {
    protected String columnName;

    @FXML
    public TableColumn<OneColumnTable, String> data;
    @FXML
    public TableColumn<OneColumnTable, String> id;

    public void initialize() {
        if (main.getUser().getType() == ProgramUser.UserType.Librarian) {
            createMenu();
        }

        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        data.setText(columnName);

        table.setItems(dataList);
    }

    public ContextMenu createMenu() {
        MenuItem insert = new MenuItem("Добавить");
        MenuItem delete = new MenuItem("Удалить");

        insert.setOnAction(t -> {
            onAddMenu();
        });

        delete.setOnAction(t -> {
            if (table.getSelectionModel().getSelectedItem() != null)
                deleteWindow(((Table) table.getSelectionModel().getSelectedItem()).getId());
        });

        MenuItem items[] = {insert, delete};
        return createMenu(items);
    }

    public void setColumnText(String text) {
        data.setText(text);
    }

    void deleteRow(String id, String methodName) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            methodName +
                            ClientSocket.argSep +
                            id);

            if (result.equals("ok")) {
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            } else {
                //TODO: не удалиласб, пока кидаю просто эксепшн
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController("Оштбка удаления",
                    "Текущее поле используется в публикациях.", e);
        }
    }
}
