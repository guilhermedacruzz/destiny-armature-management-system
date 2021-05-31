package info.oo.control;

import info.oo.Main;
import info.oo.model.repository.interfaces.ArmorAttributesRepository;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuScene extends BasicScene implements Initializable {

    private AuthService authService;
    private ArmorRepository armorRepository;
    private ArmorAttributesRepository armorAttributesRepository;


    public MenuScene(AuthService authService, ArmorRepository armorRepository, ArmorAttributesRepository armorAttributesRepository) {
        this.authService = authService;
        this.armorRepository = armorRepository;
        this.armorAttributesRepository = armorAttributesRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void viewArmors() {
        Main.changeScene(Main.VIEWS_ARMORS, (aclass) -> new ViewArmorScene(armorRepository, authService), 600, 2);
    }

    @FXML
    private void registerArmors() {
        Main.changeScene(Main.REGISTER_ARMORS, (aclass) -> new ArmorRegisterScene(authService, armorRepository, armorAttributesRepository),600, 2);
    }

    @FXML
    private void calculateArmors() {
        Main.changeScene(Main.CALCULE_ARMORS, (aclass) -> new CalculateArmorScene(armorRepository, authService), 600, 2);
    }

    @FXML
    private void exit() {
        Main.changeScene(Main.LOGIN,(aClass)->new LoginScene(authService), 600, 2);
    }
}
