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

public class MenuScene implements Initializable {

    @FXML
    private Button btRegister;

    @FXML
    private Button btView;

    @FXML
    private Button btCalc;

    @FXML
    private Button btInventory;

    @FXML
    private Button btExit;

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
    private void registerArmors() {
        Main.changeSceneFade(Main.REGISTER_ARMORS, (aclass) -> new ArmorRegisterScene(authService, armorRepository, armorAttributesRepository), 600);
    }
}
