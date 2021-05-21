/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.oo;

import java.io.IOException;

import info.oo.control.LoginScene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author nipun
 */
public class Teste extends Application {
    private static StackPane stackPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stackPane = new StackPane();
        primaryStage.setScene(new Scene(stackPane, 801, 534));

        changeScene("/fxml/Menu2.fxml",(aClass)->new LoginScene());

        primaryStage.show();
    }

    public static void changeScene(String cena, Callback construtor){

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