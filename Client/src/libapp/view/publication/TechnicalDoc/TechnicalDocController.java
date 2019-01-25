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
    public TechnicalDocController(Main m) {
        main = m;
    }

    @FXML
    private TableColumn<TechnicalDoc, String> organization;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {};
        addMenu(menuPropertyTable);

        organization.setCellValueFactory(
                new PropertyValueFactory<>("organization"));

        table.setItems(dataList);
    }

    public void onAddMenu() {
        createWindow(
                "publication" +
                        File.separator +
                        "TechnicalDoc" +
                        File.separator +
                        "TechnicalDocAddOverview.fxml",
                new TechnicalDocAddController(main));
        table.getItems().clear();
        fillTable();
    }

    public void onEditMenu() {
        if (table.getSelectionModel().getSelectedItem() != null)
            createWindow(
                    "publication" +
                            File.separator +
                            "TechnicalDoc" +
                            File.separator +
                            "TechnicalDocAddOverview.fxml",
                    new TechnicalDocChangeController(main, table.getSelectionModel().getSelectedItem().getId()));
        table.getItems().clear();
        fillTable();
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getDocs");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                System.out.println(result);
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
