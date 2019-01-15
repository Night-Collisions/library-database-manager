package libapp.view.publication.TechnicalDoc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.TechnicalDoc;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.PublicationProperty;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class TechnicalDocController extends PublicationProperty<TechnicalDoc> {

    @FXML
    private TableColumn<TechnicalDoc, String> organization;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {};
        addMenu(menuPropertyTable, new TechnicalDocAddController(), new TechnicalDocChange(), "publication" + File.separator + "TechnicalDoc" + File.separator + "TechnicalDocAddOverview.fxml");

        organization.setCellValueFactory(
                new PropertyValueFactory<>("organization"));

        table.setItems(dataList);
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getDoc");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new TechnicalDoc(
                        i.get(0).toString(),
                        i.get(1).toString(),
                        i.get(2).toString()));
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
