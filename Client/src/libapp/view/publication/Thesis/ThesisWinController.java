package libapp.view.publication.Thesis;

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
import libapp.view.PropertyWin;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThesisWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected RadioButton typeMagazine;
    @FXML
    protected RadioButton typeCollection;
    @FXML
    protected ComboBox<String> where;
    @FXML
    protected Button accept;
    @FXML
    protected Button reject;

    public ObservableList<String> publList = FXCollections.observableArrayList();

    protected Map<String,String> idDict = new HashMap<String,String>();

    protected enum type {Magazine, Collection}
    protected type currentType;

    public ThesisWinController(Main main) {
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
        final ToggleGroup group = new ToggleGroup();
        typeMagazine.setToggleGroup(group);
        typeCollection.setToggleGroup(group);
        fillMagazinesCombobox();
        typeMagazine.setSelected(true);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    if (group.getSelectedToggle() == typeMagazine) {
                        currentType = type.Magazine;
                        fillMagazinesCombobox();
                    } else {
                        currentType = type.Collection;
                        fillDigestsCombobox();
                    }
                }
            }
        });
    }
}
