package info.oo.control;

import info.oo.Main;
import info.oo.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

    private AuthService authService;

    public CharacterScene(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void comeBack() {
        Main.changeSceneFade(Main.LOGIN, (aClass)-> new LoginScene(authService), 600);
    }

    void changeScene() {
        Main.mainMenu();
    }

    @FXML
    void selectHunter() {
        authService.getLogged().setGuardianClass("Hunter");
        changeScene();
    }

    @FXML
    void selectTitan() {
        authService.getLogged().setGuardianClass("Titan");
        changeScene();
    }

    @FXML
    void selectWarlock() {
        authService.getLogged().setGuardianClass("Warlock");
        changeScene();
    }

}
