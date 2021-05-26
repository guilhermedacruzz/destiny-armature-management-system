package info.oo;

import info.oo.control.LoginScene;
import info.oo.control.MenuScene;
import info.oo.control.SignInScene;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.JDBCUser;
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
    private static StackPane stackPane;

    private ConnectionsFactory connectionsFactory;
    private JDBCUser jdbcUser;
    private AuthService authService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        connectionsFactory = new ConnectionsFactory();
        jdbcUser = new JDBCUser(connectionsFactory);
        authService = new AuthService(jdbcUser);

        stackPane = new StackPane();
        primaryStage.setScene(new Scene(stackPane, 801, 534));

        changeScene(Main.LOGIN,(aClass)->new LoginScene(authService));

        primaryStage.show();
    }

    public static void changeSceneFade(String cena, Callback construtor, int timer) {
        try{
            var paneToRemove = stackPane.getChildren().get(0);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            stackPane.getChildren().add(paneToAdd);

            var fadeInTransition = new FadeTransition(Duration.millis(timer));

            fadeInTransition.setOnFinished(evt -> {
                stackPane.getChildren().remove(paneToRemove);
            });
            fadeInTransition.setNode(paneToAdd);
            fadeInTransition.setFromValue(0);
            fadeInTransition.setToValue(1);
            fadeInTransition.play();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void changeSceneSlideRight(String cena, Callback construtor) {
        try{
            var paneToRemove = stackPane.getChildren().get(0);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            paneToAdd.translateXProperty().set(stackPane.getWidth());
            stackPane.getChildren().add(paneToAdd);

            KeyFrame start = new KeyFrame(Duration.ZERO,
                    new KeyValue(paneToAdd.translateXProperty(), stackPane.getWidth()),
                    new KeyValue(paneToRemove.translateXProperty(), 0));
            KeyFrame end = new KeyFrame(Duration.millis(850),
                    new KeyValue(paneToAdd.translateXProperty(), 0),
                    new KeyValue(paneToRemove.translateXProperty(), -stackPane.getWidth()));

            Timeline slide = new Timeline(start, end);

            slide.setOnFinished(evt -> {
                stackPane.getChildren().remove(paneToRemove);
            });

            slide.play();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void changeSceneSlideLeft(String cena, Callback construtor) {
        try{
            var paneToRemove = stackPane.getChildren().get(0);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            paneToAdd.translateXProperty().set(stackPane.getWidth() * -1);
            stackPane.getChildren().add(paneToAdd);

            KeyFrame start = new KeyFrame(Duration.ZERO,
                    new KeyValue(paneToAdd.translateXProperty(), -stackPane.getWidth()),
                    new KeyValue(paneToRemove.translateXProperty(), 0));
            KeyFrame end = new KeyFrame(Duration.millis(850),
                    new KeyValue(paneToAdd.translateXProperty(), 0),
                    new KeyValue(paneToRemove.translateXProperty(), stackPane.getWidth()));

            Timeline slide = new Timeline(start, end);

            slide.setOnFinished(evt -> {
                stackPane.getChildren().remove(paneToRemove);
            });

            slide.play();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void changeScene(String cena, Callback construtor) {

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            if (stackPane.getChildren().stream().count() > 0) {
                stackPane.getChildren().remove(0);
            }
            stackPane.getChildren().add(paneToAdd);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void mainMenu() {
        changeSceneFade(Main.MENU, (aclass) -> new MenuScene(), 1200);
    }
}
