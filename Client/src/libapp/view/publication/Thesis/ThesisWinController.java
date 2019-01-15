package libapp.view.publication.Thesis;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import libapp.view.PropertyWin;

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

    protected enum type {Magazine, Collection}
    protected type currentType;

    @FXML
    protected void initialize() {
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
                    } else {
                        currentType = type.Collection;
                        System.out.println("collect");
                    }
                }
            }
        });
    }
}
