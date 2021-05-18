package info.oo;

import info.oo.control.LoginScene;
import info.oo.control.SignInScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class Main extends Application {

    public static final String LOGIN = "/fxml/LoginScene.fxml";
    public static final String CADASTRO = "/fxml/SignInScene.fxml";
    private static StackPane base;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        base = new StackPane();
        primaryStage.setScene(new Scene(base, 801, 534));

        mudaCena(Main.LOGIN,(aClass)->new LoginScene());

        primaryStage.show();
    }

    public static void mudaCena(String cena, Callback construtor){

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(cena));
            loader.setControllerFactory(construtor);
            Parent root = loader.load();

            if (base.getChildren().stream().count() > 0) {
                base.getChildren().remove(0);
            }
            base.getChildren().add(root);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}