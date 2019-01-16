package libapp.view.publication.Book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import libapp.ClientSocket;
import libapp.model.PublishingHouse;
import libapp.view.Main;
import libapp.view.MessageController;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BookAddController extends BookWinController{
    public ObservableList<PublishingHouse> dataList = FXCollections.observableArrayList();

    protected void initialize() {
        super.initialize();

        fillPubHouseCombobox();
    }

    public BookAddController(Main main) {
        super(main);
    }

    public void fillPubHouseCombobox() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getPublHouses");

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                String[] args = new String[i.size()];
                for (int j = 0; j < i.size(); ++j) {
                    if (i.get(j) != null) {
                        args[j] = i.get(j).toString();
                    } else {
                        args[j] = "";
                    }
                }
                dataList.add(new PublishingHouse(
                        args[0],
                        args[1],
                        args[2],
                        args[3],
                        args[4]));

                ph.getItems().addAll(dataList);
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    protected void applyChange() {
        super.applyChange();
    }
}
