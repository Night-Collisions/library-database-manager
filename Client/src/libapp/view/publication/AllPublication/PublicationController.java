package libapp.view.publication.AllPublication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.Dictionary;
import libapp.model.Publication;
import libapp.view.MessageController;
import libapp.view.publication.PublicationProperty;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PublicationController extends PublicationProperty<Publication> {
    @FXML
    private TableColumn<Publication, String> type;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {};
        addMenu(
                menuPropertyTable,
                new PublicationAddController(),
                null,
                "publication\\AllPublication\\PublicationAddOverview.fxml",
                "");

        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.setItems(dataList);
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest("<empty>, getPublications");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new Publication(
                        i.get(0).toString(),
                        Dictionary.publicationType.get(i.get(1).toString()),
                        i.get(2).toString()));
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }
}
