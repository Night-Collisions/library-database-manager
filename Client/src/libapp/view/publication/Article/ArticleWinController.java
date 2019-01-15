package libapp.view.publication.Article;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import libapp.view.RegularForField;
import libapp.view.PropertyWin;

public class ArticleWinController extends PropertyWin {
    @FXML
    protected TextField name;
    @FXML
    protected RadioButton typeMagazine;
    @FXML
    protected RadioButton typeCollection;
    @FXML
    protected ComboBox<String> where;
    @FXML
    protected TextField issue;
    @FXML
    protected TextField number;

    protected enum type {Magazine, Collection}
    protected type currentType;

    @FXML
    protected void initialize() {
        RegularForField.setIntField(number);

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
                        number.setDisable(false);
                        number.clear();
                    } else {
                        currentType = type.Collection;
                        System.out.println("collect");
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
