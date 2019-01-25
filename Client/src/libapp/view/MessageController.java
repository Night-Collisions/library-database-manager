package libapp.view;

import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

public class MessageController extends Alert {

    public static enum MessageType {INFORMATION, WARNING, ERROR}

    public final static String titleErrorServerConnect = "Ошибка подключения к серверу!";
    public final static String contentTextErrorServerConnect = "Проверьте подключение к серверу или обратитесь к администратору.";

    public final static String titleErrorGetNewData = "Ошибка подключения к серверу!";
    public final static String contentTextErrorGetNewData = "Обратитесь к администратору.";

    public final static String titleErrorOpenFXML = "Ошибка целостности программы!";
    public final static String contentTextErrorOpenFXML = contentTextErrorGetNewData;

    public final static String titleErrorDB = "Ошибка базы данных!";
    public final static String contentTextErrorDB = "Обратитесь к Василенко Ивану - ivan991015@gmail.com.";

    private Map<MessageType, AlertType> MessageType2AlertType = new HashMap<MessageType, AlertType>() {{
        put(MessageType.INFORMATION, AlertType.INFORMATION);
        put(MessageType.WARNING, AlertType.WARNING);
        put(MessageType.ERROR, AlertType.ERROR);
    }};

     public MessageController(MessageType type, String title, String headerText, String contentText) {
        super(AlertType.INFORMATION);
        setAlertType(MessageType2AlertType.get(type));
        setTitle(title);
        setHeaderText(headerText);
        setContentText(contentText);
        showAndWait();
    }

    public MessageController(MessageType type, String title, String contentText) {
        super(AlertType.INFORMATION);
        setAlertType(MessageType2AlertType.get(type));
        setTitle(title);
        setHeaderText(null);
        setContentText(contentText);
        showAndWait();
    }

    public MessageController(String headerText, String contentText, Exception except) {
        super(AlertType.ERROR);
        setTitle("Ошибка!");
        setHeaderText(headerText);
        setContentText(contentText);
        //except.printStackTrace();
        showAndWait();
    }

    public MessageController(String contentText, Exception except) {
        super(AlertType.ERROR);
        setTitle("Ошибка!");
        setHeaderText(null);
        setContentText(contentText);
        //except.printStackTrace();
        showAndWait();
    }
}
