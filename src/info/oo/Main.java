package info.oo;

import info.oo.control.LoginScene;
import info.oo.control.SignInScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/fxml/SignInScene.fxml"));
        loader.setControllerFactory((aClass) -> new SignInScene());

        Parent root = loader.load();

        Scene scene = new Scene(root,801,534);

        stage.setScene(scene);
        stage.setTitle("Sei lá");
        stage.show();
    }
}
