package libapp.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libapp.ClientSocket;
import libapp.model.Notification;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {
    private ClientSocket socket;
    private Stage primaryStage;
    @FXML
    private BorderPane rootLayout;
    @FXML
    MenuBar mainMenu;
    @FXML
    Menu library;
    @FXML
    Menu tables;
    @FXML
    Menu publications;
    @FXML
    Menu functions;
    @FXML
    Menu other;
    @FXML
    Menu info;
    @FXML
    MenuItem connect;
    @FXML
    MenuItem disconnect;
    @FXML
    MenuItem allPublications;
    @FXML
    MenuItem books;
    @FXML
    MenuItem works;
    @FXML
    MenuItem articles;
    @FXML
    MenuItem theses;
    @FXML
    MenuItem techdocs;
    @FXML
    MenuItem magazines;
    @FXML
    MenuItem authors;
    @FXML
    MenuItem organizations;
    @FXML
    MenuItem editors;
    @FXML
    MenuItem publishHouses;
    @FXML
    MenuItem udcs;
    @FXML
    MenuItem keywords;
    @FXML
    MenuItem users;
    @FXML
    MenuItem myProfile;
    @FXML
    MenuItem notifications;
    @FXML
    MenuItem sendRequest;
    @FXML
    MenuItem about;

    @FXML
    private void initialize() {
       /* users.setVisible(false);
        notifications.setVisible(false);
        sendRequest.setVisible(false);*/
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MainLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.5);
            primaryStage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.5);
            primaryStage.show();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showPublications() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PublicationOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            PublicationController controller = loader.getController();
            controller.setMain(this);
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showNotifications() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("NotificationOverview.fxml"));
            AnchorPane table = loader.load();

            Stage window = new Stage();
            window.setTitle("Уведомления");
            window.initModality(Modality.WINDOW_MODAL);
            window.initOwner(getPrimaryStage());
            window.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4);
            window.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.5);

            Scene scene = new Scene(table);
            window.setScene(scene);

            NotificationController controller = loader.getController();
            controller.setMain(this);
            controller.fillTable();
            controller.setWindowStage(window);

            window.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showBooks() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("BookOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);
            ScrollPane sc = new ScrollPane();
            sc.setContent(rootLayout);

            BookController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showUsers() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("UserOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);
            ScrollPane sc = new ScrollPane();
            sc.setContent(rootLayout);

            UserController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showEditors() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("EditorOverview.fxml"));
            AnchorPane table = loader.load();
            rootLayout.setCenter(table);

            EditorController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showKeywords() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("KeywordOverview.fxml"));
            AnchorPane table = loader.load();
            rootLayout.setCenter(table);

            KeywordController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showUDCs() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("UDCOverview.fxml"));
            AnchorPane table = loader.load();
            rootLayout.setCenter(table);

            UDCController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showTheses() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ThesisOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            ThesisController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showOrganizations() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("OrganizationOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            OrganizationController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showPublishingHouses() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PublishingHouseOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            PublishingHouseController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showAuthors() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("AuthorOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            AuthorController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showTechnicalDocs() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("TechnicalDocOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            TechnicalDocController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showMagazines() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MagazineOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            MagazineController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showWorks() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("WorkOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            WorkController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    public void showArticles() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("ArticleOverview.fxml"));
            AnchorPane table = loader.load();

            rootLayout.setCenter(table);

            ArticleController controller = loader.getController();
            controller.fillTable();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    @FXML
    private void showConnectDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ConnectOverview.fxml"));
        AnchorPane connectWindow = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Авторизация");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(getPrimaryStage());
        dialogStage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4);
        dialogStage.setMinWidth(200);
        dialogStage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 5);

        Scene scene = new Scene(connectWindow);
        dialogStage.setScene(scene);

        ConnectController controller = loader.getController();
        controller.setMain(this);
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();
    }

    @FXML
    private void disconnect() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ConnectOverview.fxml"));

        ConnectController controller = loader.getController();
        controller.disconnectFromServer();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Library client");

        initRootLayout();
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public ClientSocket getSocket() {
        return socket;
    }

}
