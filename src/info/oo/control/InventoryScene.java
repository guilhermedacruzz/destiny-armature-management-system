package info.oo.control;

import info.oo.Main;
import info.oo.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryScene implements Initializable {

    @FXML
    private TextField tfLumen;

    @FXML
    private TextField tfLegendaryFragments;

    @FXML
    private TextField tfAscendentFragments;

    @FXML
    private TextField tfEnhancementPrism;

    @FXML
    private TextField tfEhancementModule;

    @FXML
    private TextField tfImprovementCore;

    private AuthService authService;


    public InventoryScene(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int lumen = authService.getLogged().getInventory().getLumen();
        int legendaryFragments = authService.getLogged().getInventory().getLegendaryFragments();
        int ascendentFragments = authService.getLogged().getInventory().getAscendentFragments();
        int enhancementPrism = authService.getLogged().getInventory().getEnhancementPrism();
        int improvementCore = authService.getLogged().getInventory().getImprovementCore();
        int ehancementModule = authService.getLogged().getInventory().getEhancementModule();

        tfLumen.setText(String.valueOf(lumen));
        tfLegendaryFragments.setText(String.valueOf(legendaryFragments));
        tfAscendentFragments.setText(String.valueOf(ascendentFragments));
        tfEnhancementPrism.setText(String.valueOf(enhancementPrism));
        tfImprovementCore.setText(String.valueOf(improvementCore));
        tfEhancementModule.setText(String.valueOf(ehancementModule));
    }

    @FXML
    void comeBack() {
        Main.mainMenu();
    }

    @FXML
    void update() {
        System.out.println("Entrou");
    }
}
