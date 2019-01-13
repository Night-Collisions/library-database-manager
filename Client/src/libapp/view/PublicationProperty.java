package libapp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class PublicationProperty {
    protected static void create(Object conect, String columnName, String idFilter) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PropertyTableWinOverview.fxml"));
            loader.setController(conect);
            AnchorPane udcTable = loader.load();

            if (conect instanceof UDCPropertyController) {
                UDCPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (conect instanceof KeywordPropertyController) {
                KeywordPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (conect instanceof AuthorsPropertyController) {
                AuthorsPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            } else if (conect instanceof EditorsPropertyController) {
                EditorsPropertyController controller = loader.getController();
                controller.fillTable(idFilter);
                controller.setColumnText(columnName + " " + idFilter);
            }

            Stage window = new Stage();
            window.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 5);
            window.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.5);
            window.initModality(Modality.WINDOW_MODAL);

            Scene scene = new Scene(udcTable);
            window.setScene(scene);
            window.showAndWait();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorGetNewData,
                    MessageController.contentTextErrorGetNewData, e);
        }
    }

    public static void UDCProperty(String idFilter) {
        create(new UDCPropertyController(), "УДК для id ", idFilter);
    }

    public static void KeyWordsProperty(String idFilter) {
        create(new KeywordPropertyController(), "Ключевые слова для id ", idFilter);
    }

    public static void AuthorsProperty(String idFilter) {
        create(new AuthorsPropertyController(), "Авторы для id ", idFilter);
    }

    public static void EditorsProperty(String idFilter) {
        create(new EditorsPropertyController(), "Редакторы для id ", idFilter);
    }
}
