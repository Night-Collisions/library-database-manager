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
}
