package libapp;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    public static final Map<String,String> publicationType = new HashMap<String,String>();
    public static final Map<String,String> userType = new HashMap<String,String>();

    static {
        publicationType.put("0", "Книга");
        publicationType.put("1", "Сборник трудов");
        publicationType.put("2", "Статья");
        publicationType.put("3", "Тезисы");
        publicationType.put("4", "Техническая документация");

        userType.put("0", "Администратор");
        userType.put("1", "Библиотекарь");
        userType.put("2", "Издательство");
        userType.put("3", "Автор");
        userType.put("4", "Читатель");
    }

    public static final Map<String,String> publicationType2Server = new HashMap<String,String>();
    public static final Map<String,String> userType2Server = new HashMap<String,String>();
    static {
        publicationType2Server.put("Книга", "0");
        publicationType2Server.put("Сборник трудов", "1");
        publicationType2Server.put("Статья", "2");
        publicationType2Server.put("Тезисы", "3");
        publicationType2Server.put("Техническая документация", "4");

        userType2Server.put("Администратор", "0");
        userType2Server.put("Библиотекарь", "1");
        userType2Server.put("Издательство", "2");
        userType2Server.put("Автор", "3");
        userType2Server.put("Читатель", "4");
    }

    public static final Map<Boolean,String> sexToName = new HashMap<Boolean, String>();
    static {
        sexToName.put(false, "мужской");
        sexToName.put(true, "женский");
    }
}
