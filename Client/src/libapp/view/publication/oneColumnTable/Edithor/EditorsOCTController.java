package libapp.view.publication.oneColumnTable.Edithor;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

import java.io.File;

import static libapp.view.MessageController.contentTextErrorDB;
import static libapp.view.MessageController.titleErrorDB;

public class EditorsOCTController extends OneColumnTableController<OneColumnTable> {
    public EditorsOCTController(Main main, String publicationID) {
        this.main = main;
        this.publicationID = publicationID;
    }

    @FXML
    public void initialize() {
        columnName = "Редакторы:";
        super.initialize();
    }

    public void fillTable(String idFilter) {
    }

    public void onAddMenu() {
        createWindow("publication" + File.separator + "oneColumnTable" + File.separator + "OneColumnAddOverview.fxml", new EditorsOCTAddController(publicationID, main));
    }

    public void deleteRow(String id) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "deleteEditorFromPubl" +
                            ClientSocket.argSep +
                            publicationID +
                            ClientSocket.argSep +
                            id);

            if (result.equals("ok")) {
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            } else {
                //TODO: не удалиласб, пока кидаю просто эксепшн
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController(titleErrorDB,
                    contentTextErrorDB, e);
        }
    }
}
