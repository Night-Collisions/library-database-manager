package libapp.view.publication.Thesis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.Thesis;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.PublicationProperty;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ThesisController extends PublicationProperty<Thesis> {

    @FXML
    private TableColumn<Thesis, String> magazineOrWork;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem menuPropertyTable[] = {CreateAuthors()};
        addMenu(menuPropertyTable);

        magazineOrWork.setCellValueFactory(
                new PropertyValueFactory<>("magazineOrWork"));

        table.setItems(dataList);
    }

    public void onAddMenu() {
        createWindow("publication" + File.separator + "Thesis" + File.separator + "ThesisAddOverview.fxml", new ThesisAddController());
    }

    public void onEditMenu() {
        createWindow("publication" + File.separator + "Thesis" + File.separator + "ThesisAddOverview.fxml", new ThesisChangeController());
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getTheses");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                if (i.get(2) == null) {
                    dataList.add(new Thesis(
                            i.get(0).toString(),
                            i.get(1).toString(),
                            i.get(3).toString()));
                } else {
                    dataList.add(new Thesis(
                            i.get(0).toString(),
                            i.get(1).toString(),
                            i.get(2).toString()));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
