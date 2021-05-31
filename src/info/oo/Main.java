package info.oo;

import info.oo.control.LoginScene;
import info.oo.control.MenuScene;
import info.oo.control.SignInScene;
import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.JDBCArmor;
import info.oo.model.daos.JDBCAttributes;
import info.oo.model.daos.JDBCUser;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.model.daos.interfaces.AttributesDAO;
import info.oo.model.daos.interfaces.UserDAO;
import info.oo.model.repository.ArmorAttributesRepositoryImpl;
import info.oo.model.repository.ArmorRepositoryImpl;
import info.oo.model.repository.interfaces.ArmorAttributesRepository;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    public static final String LOGIN = "/fxml/LoginScene.fxml";
    public static final String REGISTER = "/fxml/SignInScene.fxml";
    public static final String CHARACTER = "/fxml/CharacterScene.fxml";
    public static final String MENU = "/fxml/MenuScene.fxml";
    public static final String REGISTER_ARMORS = "/fxml/ArmorRegisterScene.fxml";
    public static final String VIEWS_ARMORS = "/fxml/ViewArmorScene.fxml";
    public static final String CALCULE_ARMORS =  "/fxml/CalculateArmorScene.fxml";

    private static StackPane stackPane;

    private ConnectionsFactory connectionsFactory;
    private UserDAO userDAO;
    private AttributesDAO attributesDAO;
    private ArmorDAO armorDAO;

    private static AuthService authService;
    private static ArmorRepository armorRepository;
    private static ArmorAttributesRepository armorAttributesRepository;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        initDAOS();
        initRepository();

        stackPane = new StackPane();
        primaryStage.setScene(new Scene(stackPane, 801, 534));

        changeScene(Main.LOGIN,(aClass)->new LoginScene(authService), -1, 1);

        primaryStage.show();
    }

    private void initDAOS() {
        connectionsFactory = new ConnectionsFactory();
        userDAO = new JDBCUser(connectionsFactory);
        attributesDAO = new JDBCAttributes(connectionsFactory);
        armorDAO = new JDBCArmor(connectionsFactory);
    }

    private void initRepository() {
        authService = new AuthService(userDAO);
        armorRepository = new ArmorRepositoryImpl(armorDAO, attributesDAO);
        armorAttributesRepository = new ArmorAttributesRepositoryImpl(attributesDAO);
    }

    public static void mainMenu() {
        changeScene(Main.MENU, (aclass) -> new MenuScene(authService, armorRepository, armorAttributesRepository), 950, 2);
    }

    public static void changeScene(String cena, Callback construtor, int timer, int type) {

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            switch (type) {
                case 1:
                    noAnimation(paneToAdd);
                    break;
                case 2:
                    fadeIn(paneToAdd, timer);
                    break;
                case 4:
                    slideLeft(paneToAdd, timer);
                    break;
                case 5:
                    slideRight(paneToAdd, timer);
                    break;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void noAnimation(Parent paneToAdd) {
        if (stackPane.getChildren().stream().count() > 0) {
            stackPane.getChildren().remove(0);
        }
        stackPane.getChildren().add(paneToAdd);
    }


    private static void fadeIn(Parent paneToAdd, int timer) {
        var paneToRemove = stackPane.getChildren().get(0);

        stackPane.getChildren().add(paneToAdd);

        var fadeInTransition = new FadeTransition(Duration.millis(timer));

        fadeInTransition.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });
        fadeInTransition.setNode(paneToAdd);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        fadeInTransition.play();
    }

    private static void slideRight(Parent paneToAdd, int timer) {
        var paneToRemove = stackPane.getChildren().get(0);

        paneToAdd.translateXProperty().set(stackPane.getWidth());
        stackPane.getChildren().add(paneToAdd);

        KeyFrame start = new KeyFrame(Duration.ZERO,
                new KeyValue(paneToAdd.translateXProperty(), stackPane.getWidth()),
                new KeyValue(paneToRemove.translateXProperty(), 0));
        KeyFrame end = new KeyFrame(Duration.millis(timer),
                new KeyValue(paneToAdd.translateXProperty(), 0),
                new KeyValue(paneToRemove.translateXProperty(), -stackPane.getWidth()));

        Timeline slide = new Timeline(start, end);

        slide.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });

        slide.play();
    }

    private static void slideLeft(Parent paneToAdd, int timer) {
        var paneToRemove = stackPane.getChildren().get(0);

        paneToAdd.translateXProperty().set(stackPane.getWidth() * -1);
        stackPane.getChildren().add(paneToAdd);

        KeyFrame start = new KeyFrame(Duration.ZERO,
                new KeyValue(paneToAdd.translateXProperty(), -stackPane.getWidth()),
                new KeyValue(paneToRemove.translateXProperty(), 0));
        KeyFrame end = new KeyFrame(Duration.millis(timer),
                new KeyValue(paneToAdd.translateXProperty(), 0),
                new KeyValue(paneToRemove.translateXProperty(), stackPane.getWidth()));

        Timeline slide = new Timeline(start, end);

        slide.setOnFinished(evt -> {
            stackPane.getChildren().remove(paneToRemove);
        });

        slide.play();
    }
}
