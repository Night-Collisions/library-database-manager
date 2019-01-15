package libapp.view.publication.Article;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ArticleChangeController {
    @FXML
    private TextField name;
    @FXML
    private RadioButton typeMagazine;
    @FXML
    private RadioButton typeCollection;
    @FXML
    private ComboBox<String> where;
    @FXML
    private TextField issue;
    @FXML
    private Button accept;
    @FXML
    private Button reject;

    private enum type {Magazine, Collection}
    private type currentType;

    @FXML
    private void initialize() {
        name.setText("редактируется");

        final ToggleGroup group = new ToggleGroup();
        typeMagazine.setToggleGroup(group);
        typeCollection.setToggleGroup(group);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    if (group.getSelectedToggle() == typeMagazine) {
                        currentType = type.Magazine;
                        System.out.println("Magaz");
                        issue.setDisable(false);
                        issue.clear();
                    } else {
                        currentType = type.Collection;
                        System.out.println("collect");
                        issue.setDisable(true);
                        issue.clear();
                    }
                }
            }
        });
    }

    @FXML
    private int applyChange() {
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }

    @FXML
    private int closeWindow(){
        Stage stage = (Stage) reject.getScene().getWindow();
        stage.close();
        return 0;
    }
}
