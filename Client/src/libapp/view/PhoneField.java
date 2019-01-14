package libapp.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class PhoneField {
    public static void setPhoneField(TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {

                if ((!newValue.matches("[0-9]*")) || (newValue.length() > 11)) {
                    field.setText(oldValue);
                }
            }
        });
    }
}
