package info.oo;

import info.oo.control.LoginScene;
import info.oo.control.SignInScene;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
    public static final String CADASTRO = "/fxml/SignInScene.fxml";
    private static StackPane stackPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stackPane = new StackPane();
        primaryStage.setScene(new Scene(stackPane, 801, 534));

        mudaCena(Main.LOGIN,(aClass)->new LoginScene());

        primaryStage.show();
    }

    public static void mudaCenaDireita(String cena, Callback construtor) {
        try{
            var paneToRemove = stackPane.getChildren().get(0);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            paneToAdd.translateXProperty().set(stackPane.getWidth());
            stackPane.getChildren().add(paneToAdd);

            var keyValue = new KeyValue(paneToAdd.translateXProperty(), 0, Interpolator.EASE_IN);
            var keyFrame = new KeyFrame(Duration.millis(900), keyValue);
            var timeline = new Timeline(keyFrame);
            timeline.setOnFinished(evt -> {
                stackPane.getChildren().remove(paneToRemove);
            });
            timeline.play();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void mudaCenaEsquerda(String cena, Callback construtor) {
        try{
            var paneToRemove = stackPane.getChildren().get(0);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent paneToAdd = loader.load();

            paneToAdd.translateXProperty().set(-1 * stackPane.getWidth());
            stackPane.getChildren().add(paneToAdd);

            var keyValue = new KeyValue(paneToAdd.translateXProperty(), 0, Interpolator.EASE_IN);
            var keyFrame = new KeyFrame(Duration.millis(900), keyValue);
            var timeline = new Timeline(keyFrame);
            timeline.setOnFinished(evt -> {
                stackPane.getChildren().remove(paneToRemove);
            });
            timeline.play();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void mudaCena(String cena, Callback construtor){

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
}
