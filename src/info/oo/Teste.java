package info.oo;

import info.oo.control.ArmorRegisterScene;
import info.oo.control.LoginScene;
import info.oo.control.MenuScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


    public class Teste extends Application {
        public static void main(String[] args) {
            System.out.println("Hello World!");
            Application.launch(args);
        }

        @Override
        public void start(Stage stage) throws Exception {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(Main.class.getResource("/fxml/ArmorRegisterScene.fxml"));
            loader.setControllerFactory((aClass) -> new ArmorRegisterScene());

            Parent root = loader.load();

            Scene scene = new Scene(root,801,534);

            stage.setScene(scene);
            stage.setTitle("Sei lá");
            stage.show();
        }
    }