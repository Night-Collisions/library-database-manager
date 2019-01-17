package libapp.view.publication.AllPublication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;
import libapp.ClientSocket;
import libapp.Dictionary;
import libapp.model.Publication;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.TableProperty;
import libapp.view.publication.Article.ArticleAddController;
import libapp.view.publication.Article.ArticleChangeController;
import libapp.view.publication.Book.BookAddController;
import libapp.view.publication.Book.BookChangeController;
import libapp.view.publication.PublicationProperty;
import libapp.view.publication.TechnicalDoc.TechnicalDocAddController;
import libapp.view.publication.TechnicalDoc.TechnicalDocChangeController;
import libapp.view.publication.Thesis.ThesisAddController;
import libapp.view.publication.Thesis.ThesisChangeController;
import libapp.view.publication.Work.WorkAddController;
import libapp.view.publication.Work.WorkChangeController;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static libapp.Dictionary.publicationType2Server;
import static libapp.Dictionary.userType2Server;

public class PublicationController extends PublicationProperty<Publication> {
    public PublicationController(Main m) {
        main = m;
        windows = FXCollections.observableArrayList(
                new Pair<>("publication" + File.separator + "Book" + File.separator + "BookAddOverview.fxml", new BookChangeController(main)),
                new Pair<>("publication" + File.separator + "Book" + File.separator + "BookAddOverview.fxml", new WorkChangeController()),
                new Pair<>("publication" + File.separator + "Article" + File.separator + "ArticleAddOverview.fxml", new ArticleChangeController(main)),
                new Pair<>("publication" + File.separator + "Thesis" + File.separator + "ThesisAddOverview.fxml", new ThesisChangeController()),
                new Pair<>("publication" + File.separator + "TechnicalDoc" + File.separator + "TechnicalDocAddOverview.fxml", new TechnicalDocChangeController())
        );
    }

    @FXML
    private TableColumn<Publication, String> type;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem[] menuPropertyTable = {};
        addMenu(menuPropertyTable);

        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        table.setItems(dataList);
    }

    public void onAddMenu() {
        createWindow(
                "publication" +
                        File.separator +
                        "AllPublication" +
                        File.separator +
                        "PublicationAddOverview.fxml",
                new PublicationAddController(main));

        //TODO: это не прикольно
        table.getItems().clear();
        fillTable();
    }

    private ObservableList<Pair<String, Object>> windows;

    public void onEditMenu() {
        if (table.getSelectionModel().getSelectedItem() != null) {
            int type = Integer.parseInt(publicationType2Server.get(table.getSelectionModel().getSelectedItem().getType()));
            if (type >= 0) {
                TableProperty.createWindow(windows.get(type).getKey(), windows.get(type).getValue());
            } else {
                new MessageController(MessageController.MessageType.ERROR, MessageController.titleErrorDB,
                        MessageController.titleErrorDB, MessageController.contentTextErrorDB);
            }
        }
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getPublications");

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

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}