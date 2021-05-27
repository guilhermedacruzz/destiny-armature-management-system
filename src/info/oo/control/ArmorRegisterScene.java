package info.oo.control;

import info.oo.Main;
import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.repository.interfaces.ArmorAttributesRepository;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ArmorRegisterScene implements Initializable {

    @FXML
    TextField tfName;


    @FXML
    private TextField tfMobility;

    @FXML
    private TextField tfResilience;

    @FXML
    private TextField tfRecovery;

    @FXML
    private TextField tfDicipline;

    @FXML
    private TextField tfIntellect;

    @FXML
    private TextField tfStrenght;

    @FXML
    private RadioButton rbHelmet;

    @FXML
    private RadioButton rbArm;

    @FXML
    private RadioButton rbChest;

    @FXML
    private RadioButton rbBoot;

    @FXML
    private RadioButton rbClassItem;

    @FXML
    private RadioButton rbExotic;

    @FXML
    private RadioButton rbLegendary;

    @FXML
    private RadioButton rbNoMasterprice;

    @FXML
    private RadioButton rbYesMasterprice;

    @FXML
    private RadioButton rbSolar;

    @FXML
    private RadioButton rbVoid;

    @FXML
    private RadioButton rbArc;

    private AuthService authService;
    private ArmorRepository armorRepository;
    private ArmorAttributesRepository armorAttributesRepository;

    public ArmorRegisterScene(AuthService authService, ArmorRepository armorRepository, ArmorAttributesRepository armorAttributesRepository) {
        this.authService = authService;
        this.armorRepository = armorRepository;
        this.armorAttributesRepository = armorAttributesRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfName.setPromptText("Nome");

        tfMobility.setPromptText("2");
        tfResilience.setPromptText("2");
        tfRecovery.setPromptText("2");
        tfDicipline.setPromptText("2");
        tfIntellect.setPromptText("2");
        tfStrenght.setPromptText("2");

    }

    @FXML
    private void register() {
        String name = tfName.getText();
        int mod = 0, res = 0, rec = 0, dici = 0, inte = 0, stren = 0;
        String type = "", element = "", rarity = "";
        boolean masterprice = false;

        try {
            mod = Integer.parseInt(tfMobility.getText());
            res = Integer.parseInt(tfResilience.getText());
            rec = Integer.parseInt(tfRecovery.getText());
            dici = Integer.parseInt(tfDicipline.getText());
            inte = Integer.parseInt(tfIntellect.getText());
            stren = Integer.parseInt(tfStrenght.getText());
        } catch (NumberFormatException e) {
            errorRegister(e.getMessage());
        }

        if(rbHelmet.isSelected())
            type = "Capacete";
        else if(rbArm.isSelected())
            type = "Manopla";
        else if(rbChest.isSelected())
            type = "Armadura de Torço";
        else if(rbBoot.isSelected())
            type = "Armadura de Perna";
        else if(rbClassItem.isSelected())
            type = "Item de Classe";
        else
            errorRegister("Selecione tipo");

        if(rbArc.isSelected())
            element = "Arco";
        else if(rbSolar.isSelected())
            element = "Solar";
        else if(rbVoid.isSelected())
            element = "Vácuo";
        else
            errorRegister("Selecione eleme");

        if(rbExotic.isSelected())
            rarity = "Exótico";
        else if(rbLegendary.isSelected())
            rarity = "Lendário";
        else
            errorRegister("Selecione Rar");

        if(rbYesMasterprice.isSelected())
            masterprice = true;
        else
            errorRegister("Selecione Obra");


        ArmorAttribute armorAttribute = new ArmorAttribute(mod, res, rec, dici, inte, stren);

        Armor armor = new Armor(name, authService.getLogged().getGuardianClass(), type, rarity, true, masterprice,
                armorAttribute, element, authService.getLogged().getId());

        try {
            armorAttributesRepository.registerAttribute(armorAttribute);
        } catch (SQLException e) {
            errorRegister(e.getMessage());
        }

        try {
            armorRepository.register(armor);
        } catch (SQLException e) {
            errorRegister(e.getMessage());
        }
    }

    private void errorRegister(String error) {
        System.out.println("Erro: " + error);
    }

    @FXML
    private void comeBack() { Main.mainMenu(); }
}
