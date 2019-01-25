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
import libapp.view.Main;
import libapp.view.MessageController;
import libapp.view.RegularForField;
import libapp.view.PropertyWin;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArticleWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected RadioButton typeMagazine;
    @FXML
    protected RadioButton typeCollection;
    @FXML
    protected Label labelType;
    @FXML
    protected ComboBox<String> where;
    @FXML
    protected TextField volume;
    @FXML
    protected TextField number;

    public ObservableList<String> publList = FXCollections.observableArrayList();

    protected Map<String,String> idDict = new HashMap<String,String>();

    protected enum type {Magazine, Collection}
    protected type currentType;

    protected Main main;
    protected ClientSocket socket;

    public ArticleWinController(Main main) {
        this.main = main;
        this.socket = main.getSocket();
    }

    public void fillMagazinesCombobox() {
        try {
            where.getItems().clear();
            idDict.clear();
            publList.clear();
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
                publList.add(args[1]);
                idDict.put(args[1], args[0]);
            }

            where.getItems().addAll(publList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public void fillDigestsCombobox() {
        try {
            where.getItems().clear();
            idDict.clear();
            publList.clear();
            String result = "";
            socket = ClientSocket.enableConnection(socket);
            result = socket.makeRequest(main.getUser().getId() + ClientSocket.argSep + "getDigests");

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
                publList.add(args[1]);
                idDict.put(args[1], args[0]);
            }

            where.getItems().addAll(publList);
        } catch (Exception e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }


    @FXML
    protected void initialize() {
        RegularForField.setIntField(number);

        final ToggleGroup group = new ToggleGroup();
        typeMagazine.setToggleGroup(group);
        typeCollection.setToggleGroup(group);
        typeMagazine.setSelected(true);
        fillMagazinesCombobox();
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    if (group.getSelectedToggle() == typeMagazine) {
                        currentType = type.Magazine;
                        volume.setDisable(false);
                        volume.clear();
                        number.setDisable(false);
                        number.clear();
                        labelType.setText("Журнал:");
                        fillMagazinesCombobox();
                    } else {
                        currentType = type.Collection;
                        volume.setDisable(true);
                        volume.clear();
                        number.setDisable(true);
                        number.clear();
                        labelType.setText("Сборник:");
                        fillDigestsCombobox();
                    }
                }
            }
        });

    }
}
