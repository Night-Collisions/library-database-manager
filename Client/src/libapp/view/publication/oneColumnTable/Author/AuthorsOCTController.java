package libapp.view.publication.oneColumnTable.Author;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.OneColumnTable;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.oneColumnTable.Edithor.EditorsOCTAddController;
import libapp.view.publication.oneColumnTable.OneColumnTableController;

import java.io.File;

import static libapp.view.MessageController.contentTextErrorDB;
import static libapp.view.MessageController.titleErrorDB;

public class AuthorsOCTController extends OneColumnTableController {
    public AuthorsOCTController(Main main, String publicationID) {
        this.main = main;
        this.publicationID = publicationID;
    }

    @FXML
    public void initialize() {
        columnName = "Авторы:";
        super.initialize();
    }

    public void fillTable(String idFilter) {
        fillTable(idFilter, "getAuthorsOfPubl", 4);
    }

    public void onAddMenu() {
        createWindow("publication" + File.separator + "oneColumnTable" + File.separator + "OneColumnAddOverview.fxml", new AuthorsOCTAddController(publicationID, main, table));
    }

    public void deleteRow(String id) {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "deleteAuthorFromPubl" +
                            ClientSocket.argSep +
                            publicationID +
                            ClientSocket.argSep +
                            id);

            if (result.equals("ok")) {
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController(titleErrorDB,
                    contentTextErrorDB, e);
        }
    }
}
