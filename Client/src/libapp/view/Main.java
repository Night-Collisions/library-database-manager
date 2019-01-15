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
import libapp.model.User;
import libapp.view.Editor.EditorController;
import libapp.view.organization.OrganizationController;
import libapp.view.publishingHouse.PublishingHouseController;
import libapp.view.author.AuthorController;
import libapp.view.magazine.MagazineController;
import libapp.view.publication.Article.ArticleController;
import libapp.view.publication.Book.BookController;
import libapp.view.publication.AllPublication.PublicationController;
import libapp.view.publication.TechnicalDoc.TechnicalDocController;
import libapp.view.publication.Thesis.ThesisController;
import libapp.view.publication.Work.WorkController;
import libapp.view.user.UserController;
import sun.plugin.util.UserProfile;

import java.awt.*;
import java.io.File;
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

    private User user;

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
            loader.setLocation(Main.class.getResource("publication" + File.separator + "AllPublication" + File.separator + "PublicationOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("publication" + File.separator + "Book" + File.separator + "BookOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("user" + File.separator + "UserOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("Editor" + File.separator + "EditorOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("PropertyTableWinOverview.fxml"));
            loader.setController(new KeywordPropertyController());
            AnchorPane table = loader.load();
            rootLayout.setCenter(table);

            KeywordPropertyController controller = loader.getController();
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
            loader.setLocation(Main.class.getResource("PropertyTableWinOverview.fxml"));
            loader.setController(new UDCPropertyController());
            AnchorPane table = loader.load();
            rootLayout.setCenter(table);

            UDCPropertyController controller = loader.getController();
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
            loader.setLocation(Main.class.getResource("publication" + File.separator + "Thesis" + File.separator + "ThesisOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("organization" + File.separator + "OrganizationOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("publishingHouse" + File.separator + "PublishingHouseOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("author" + File.separator + "AuthorOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("publication" + File.separator + "TechnicalDoc" + File.separator + "TechnicalDocOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("magazine" + File.separator + "MagazineOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("publication" + File.separator + "Work" + File.separator + "WorkOverview.fxml"));
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
            loader.setLocation(Main.class.getResource("publication" + File.separator + "Article" + File.separator + "ArticleOverview.fxml"));
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
    public void showUserProfile() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("UserProfileOverview.fxml"));
            loader.setController(new UserProfileOverview());
            AnchorPane profileWindow = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Профиль");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());

            Scene scene = new Scene(profileWindow);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
        } catch (IOException e) {
            new MessageController(MessageController.titleErrorOpenFXML,
                    MessageController.contentTextErrorOpenFXML, e);
        }
    }

    public void showGroupChangeRequest() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("GroupChangeRequestOverview.fxml"));
            AnchorPane requestWindow = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Запрос");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(getPrimaryStage());
            dialogStage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 5);
            dialogStage.setMinWidth(250);
            dialogStage.setHeight(150);

            Scene scene = new Scene(requestWindow);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
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
    private void showAboutProgram() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("AboutProgram.fxml"));
        AnchorPane abour = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("О программе");
        Scene scene = new Scene(abour);
        dialogStage.setScene(scene);
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

    public User getUser() {
        return user;
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
