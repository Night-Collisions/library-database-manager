package libapp.view.publication.Book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.Dictionary;
import libapp.model.Book;
import libapp.model.Publication;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.publication.PublicationProperty;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class BookController extends PublicationProperty<Book> {
    public BookController(Main m) {
        main = m;
    }

    @FXML
    private TableColumn<Book, String> publishingHouse;
    @FXML
    private TableColumn<Book, String> year;

    @FXML
    private void initialize() {
        initProperty();
        MenuItem[] menuPropertyTable = {CreateAuthors(), CreateEditors()};
        addMenu(menuPropertyTable);

        publishingHouse.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        table.setItems(dataList);
        table.setScaleShape(false);
    }

    public void onAddMenu() {
        createWindow(
                "publication" +
                        File.separator +
                        "Book" +
                        File.separator +
                        "BookAddOverview.fxml",
                new BookAddController(main));
        //TODO: это не прикольно
        table.getItems().clear();
        fillTable();
    }

    public void onEditMenu() {
        createWindow(
                "publication" +
                        File.separator +
                        "Book" +
                        File.separator +
                        "BookAddOverview.fxml",
                new BookChangeController(main));
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getBooks");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                dataList.add(new Book(
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
