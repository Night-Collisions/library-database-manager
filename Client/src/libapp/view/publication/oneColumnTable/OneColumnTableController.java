package libapp.view.publication.oneColumnTable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.ProgramUser;
import libapp.model.OneColumnTable;
import libapp.view.AllOneColumnTable.AllOneColumnTableController;
import libapp.view.MessageController;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class OneColumnTableController extends AllOneColumnTableController<OneColumnTable> {
    protected String publicationID;

    public void initialize() {
        if ((main.getUser().getType() == ProgramUser.UserType.Librarian)
            || (((main.getUser().getType() == ProgramUser.UserType.Author)
                || (main.getUser().getType() == ProgramUser.UserType.PublishingHouse)))
                && (main.getUser().getPublication().contains(publicationID))) {
            createMenu();
        }

        data.setCellValueFactory(new PropertyValueFactory<>("data"));

        table.setItems(dataList);
    }

    public void fillTable(String idFilter) {};

    public void fillTable(String idFilter, String request, int numberFerstParam) {
        String result = "";
        try {
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            request +
                            ClientSocket.argSep +
                            idFilter);

            if (result.equals("wrong args")) {
                throw new Exception();
            }

            Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
            ArrayList<ArrayList<String>> parsed = new Gson().fromJson(result, type);

            for (ArrayList i : parsed) {
                String s = i.get(1).toString();
                for (int k = 2; k < numberFerstParam; k++)
                    s += (" " + i.get(k).toString());
                dataList.add(new OneColumnTable(i.get(0).toString(), s));
            }

            table.setItems(dataList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }
}
