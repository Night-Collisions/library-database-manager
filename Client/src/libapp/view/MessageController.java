package libapp.view;

import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

class MessageController extends Alert {

    public enum MessageType {INFORMATION, WARNING, ERROR}

    final static String titleErrorServerConnect = "Ошибка подключения к серверу!";
    final static String contentTextErrorServerConnect = "Проверьте подключение к серверу или обратитесь к администратору.";

    final static String titleErrorGetNewData = "Ошибка подключения к серверу!";
    final static String contentTextErrorGetNewData = "Обратитесь к администратору.";

    final static String titleErrorOpenFXML = "Ошибка целостности программы!";
    final static String contentTextErrorOpenFXML = contentTextErrorGetNewData;

    private Map<MessageType, AlertType> MessageType2AlertType = new HashMap<MessageType, AlertType>() {{
        put(MessageType.INFORMATION, AlertType.INFORMATION);
        put(MessageType.WARNING, AlertType.WARNING);
        put(MessageType.ERROR, AlertType.ERROR);
    }};

     MessageController(MessageType type, String title, String headerText, String contentText) {
        super(AlertType.INFORMATION);
        setAlertType(MessageType2AlertType.get(type));
        setTitle(title);
        setHeaderText(headerText);
        setContentText(contentText);
        showAndWait();
    }

    MessageController(MessageType type, String title, String contentText) {
        super(AlertType.INFORMATION);
        setAlertType(MessageType2AlertType.get(type));
        setTitle(title);
        setHeaderText(null);
        setContentText(contentText);
        showAndWait();
    }

    MessageController(String headerText, String contentText, Exception except) {
        super(AlertType.ERROR);
        setTitle("Ошибка!");
        setHeaderText(headerText);
        setContentText(contentText);
        except.printStackTrace();
        showAndWait();
    }

    MessageController(String contentText, Exception except) {
        super(AlertType.ERROR);
        setTitle("Ошибка!");
        setHeaderText(null);
        setContentText(contentText);
        except.printStackTrace();
        showAndWait();
    }
}
