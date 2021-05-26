package info.oo.control;

import info.oo.Main;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private void registerArmors() {
        Main.changeSceneFade(Main.REGISTER_ARMORS, (aclass) -> new ArmorRegisterScene(), 600);
    }
}
