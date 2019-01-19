package libapp.view.publication.Work;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.Work;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.PublicationProperty;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class WorkController extends PublicationProperty<Work> {
    public WorkController(Main m) {
        main = m;
    }
    @FXML
    private TableColumn<Work, String> publishingHouse;
    @FXML
    private TableColumn<Work, String> year;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {CreateEditors()};
        addMenu(menuPropertyTable);

        publishingHouse.setCellValueFactory(
                new PropertyValueFactory<>("publishingHouse"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.setItems(dataList);
    }

    public void onAddMenu() {
        createWindow(
                "publication" +
                        File.separator +
                        "Book" +
                        File.separator +
                        "BookAddOverview.fxml",
                new WorkAddController(main));
        table.getItems().clear();
        fillTable();
    }

    public void onEditMenu() {
        if (table.getSelectionModel().getSelectedItem() != null)
            createWindow(
                    "publication" +
                            File.separator +
                            "Book" +
                            File.separator +
                            "BookAddOverview.fxml",
                    new WorkChangeController(main, table.getSelectionModel().getSelectedItem()));
        table.getItems().clear();
        fillTable();
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getDigests");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new Work(
                        i.get(0).toString(),
                        i.get(1).toString(),
                        i.get(2).toString(),
                        i.get(3).toString()));
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
