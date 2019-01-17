package libapp.view.publication.Article;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import libapp.ClientSocket;
import libapp.model.Publication;
import libapp.model.PublishingHouse;
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.RegularForField;
import libapp.view.PropertyWin;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ArticleWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected RadioButton typeMagazine;
    @FXML
    protected RadioButton typeCollection;
    @FXML
    protected ComboBox<PublishingHouse> where;
    @FXML
    protected TextField issue;
    @FXML
    protected TextField number;

    public ObservableList<PublishingHouse> dataList = FXCollections.observableArrayList();

    protected enum type {Magazine, Collection}
    protected type currentType;

    protected Main main;
    protected ClientSocket socket;

    public ArticleWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
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

                where.getItems().addAll(dataList);
            }
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }


    @FXML
    protected void initialize() {
        RegularForField.setIntField(number);
        where.setEditable(false);
        final ToggleGroup group = new ToggleGroup();
        typeMagazine.setToggleGroup(group);
        typeCollection.setToggleGroup(group);
        typeMagazine.setSelected(true);
        fillPubHouseCombobox();
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    if (group.getSelectedToggle() == typeMagazine) {
                        currentType = type.Magazine;
                        issue.setDisable(false);
                        issue.clear();
                        number.setDisable(false);
                        number.clear();
                    } else {
                        currentType = type.Collection;
                        issue.setDisable(true);
                        issue.clear();
                        number.setDisable(true);
                        number.clear();
                    }
                }
            }
        });

    }
}
