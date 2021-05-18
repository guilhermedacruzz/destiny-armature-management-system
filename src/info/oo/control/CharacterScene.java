package info.oo.control;

import info.oo.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterScene implements Initializable {

    @FXML
    private Button btHunter;

    @FXML
    private Button btTitan;

    @FXML
    private Button btWarlock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btHunter.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/images/hunter_logo.png"))));
        btWarlock.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/images/warlock_logo.png"))));
        btTitan.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/assets/images/titan_logo.png"))));
    }

    @FXML
    void comeBack() {
        Main.changeSceneFade(Main.LOGIN, (aClass)-> new LoginScene());
    }
}
