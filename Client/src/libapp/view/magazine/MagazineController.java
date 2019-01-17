package libapp.view.magazine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import libapp.ClientSocket;
import libapp.model.Magazine;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.TableProperty;


import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static libapp.ProgramUser.UserType.Librarian;

public class MagazineController extends TableProperty<Magazine> {
    public MagazineController(Main main) {
        this.main = main;
    }

    @FXML
    private TableColumn<Magazine, String> id;
    @FXML
    private TableColumn<Magazine, String> name;
    @FXML
    private TableColumn<Magazine, String> topic;

    @FXML
    private void initialize() {
        if (main.getUser().getType() == Librarian)
            createMenu();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        topic.setCellValueFactory(
                new PropertyValueFactory<>("topic"));

        table.setItems(dataList);
    }

    public void onAddMenu() {
        createWindow(
                "magazine" +
                        File.separator +
                        "MagazineAddOverview.fxml",
                new MagazineAddController(main));
        table.getItems().clear();
        fillTable();
    }

    public void onEditMenu() {
        createWindow(
                "magazine" +
                        File.separator +
                        "MagazineAddOverview.fxml",
                new MagazineAddController(main));
        table.getItems().clear();
        fillTable();
    }

    public void fillTable() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getMagazines");

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
                dataList.add(new Magazine(
                        args[0],
                        args[1],
                        args[2]));
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void deleteRow() {
        try {
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(
                    main.getUser().getId() +
                            ClientSocket.argSep +
                            "deleteMagazine" +
                            ClientSocket.argSep +
                            id);

            if (result.equals("ok")) {
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
            } else {
                //TODO: не удалиласб, пока кидаю просто эксепшн
                throw new Exception();
            }
        } catch (Exception e) {
            new MessageController("Не удалоь удалить запись",
                    "Запись связана с другой таблицей", e);
        }
    }

    public void setMain(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }
}
