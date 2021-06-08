package info.oo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Teste extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/fxml/ViewArmorSetScene.fxml"));
        loader.setControllerFactory((aClass) -> new ViewArmorSetScene());

        Parent root = loader.load();

        Scene scene = new Scene(root,801,534);

        stage.setScene(scene);
        stage.setTitle("Cadastro Animais de Estimação");
        stage.show();
    }
}
