package libapp.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class RegularForField {
    private static void createRegular(TextField field, String regular) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if ((!newValue.matches(regular))) {
                    field.setText(oldValue);
                }
            }
        });
    }

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

    public static void setPasswordField(TextField field) {
        createRegular(field, "[0-9A-Za-z~!@#$%^&*()_+`\\-={}\\[\\]:;<>./\\\\]*");
    }

    public static void setLoginField(TextField field) {
        createRegular(field, "[A-Za-z0-9_]*");
    }
}
