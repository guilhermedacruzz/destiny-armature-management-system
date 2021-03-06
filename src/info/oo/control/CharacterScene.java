package info.oo.control;

import info.oo.Main;
import info.oo.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class CharacterScene extends BasicScene implements Initializable {

    private AuthService authService;

    public CharacterScene(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void comeBack() {
        Main.changeScene(Main.LOGIN, (aClass)-> new LoginScene(authService),650, 2);
    }

    void changeScene() {
        Main.mainMenu();
    }

    @FXML
    void selectHunter() {
        authService.getLogged().setGuardianClass("Caçador");
        changeScene();
    }

    @FXML
    void selectTitan() {
        authService.getLogged().setGuardianClass("Titã");
        changeScene();
    }

    @FXML
    void selectWarlock() {
        authService.getLogged().setGuardianClass("Arcano");
        changeScene();
    }

}
