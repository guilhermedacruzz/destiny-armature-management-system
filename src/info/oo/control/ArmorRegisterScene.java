package info.oo.control;

import info.oo.Main;
import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.repository.interfaces.ArmorAttributesRepository;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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

        tfMobility.setText("2");
        tfResilience.setText("2");
        tfRecovery.setText("2");
        tfDicipline.setText("2");
        tfIntellect.setText("2");
        tfStrenght.setText("2");

    }

    private String getName() {
        return tfName.getText();
    }

    private int[] getAttributes() {
        int[] attributes = new int[6];

        try {
            attributes[0] = Integer.parseInt(tfMobility.getText());
            attributes[1] = Integer.parseInt(tfResilience.getText());
            attributes[2] = Integer.parseInt(tfRecovery.getText());
            attributes[3] = Integer.parseInt(tfDicipline.getText());
            attributes[4] = Integer.parseInt(tfIntellect.getText());
            attributes[5] = Integer.parseInt(tfStrenght.getText());
        } catch (NumberFormatException e) {
            attributes = null;
        }

        return attributes;
    }

    private String getType() {
        String type = "";

        if(rbHelmet.isSelected())
            type = "Capacete";
        else if(rbArm.isSelected())
            type = "Manopla";
        else if(rbChest.isSelected())
            type = "Armadura de Torso";
        else if(rbBoot.isSelected())
            type = "Armadura de Perna";
        else if(rbClassItem.isSelected())
            type = "Item de Classe";

        return type;
    }

    private String getElement() {
        String element = "";

        if(rbArc.isSelected())
            element = "Arco";
        else if(rbSolar.isSelected())
            element = "Solar";
        else if(rbVoid.isSelected())
            element = "Vácuo";

        return element;
    }

    private String getRarity() {
        String rarity = "";

        if(rbExotic.isSelected())
            rarity = "Exótico";
        else if(rbLegendary.isSelected())
            rarity = "Lendário";

        return rarity;
    }

    private boolean getMasterprice() {
        boolean isMasterprice = false;
        if(rbYesMasterprice.isSelected())
            isMasterprice = true;

        return isMasterprice;
    }

    @FXML
    private void register() {

        String name = getName();
        if(name.equals("")) {
            errorRegister("ERRO", "Nome Inválido.");
            return;
        }

        int[] attributes = getAttributes();
        if(attributes == null) {
            errorRegister("ERRO", "Verifiquei o valor dos Atributos.");
            return;
        }

        String type = getType();
        if(type.equals("")) {
            errorRegister("ERRO", "Escolha um Tipo de Armadura.");
            return;
        }

        String rarity = getRarity();
        if(rarity.equals("")) {
            errorRegister("ERRO", "Escolha uma raridade.");
            return;
        }

        String element = getElement();
        if(element.equals("")) {
            errorRegister("ERRO", "Escolha um elemento.");
            return;
        }

        boolean isMasterprice = getMasterprice();

        ArmorAttribute armorAttribute = new ArmorAttribute(attributes[0], attributes[1], attributes[2],
                                                           attributes[3], attributes[4], attributes[5]);

        Armor armor = new Armor(name, authService.getLogged().getGuardianClass(), type, rarity, true, isMasterprice,
                armorAttribute, element, authService.getLogged().getId());

        try {
            armorAttributesRepository.registerAttribute(armorAttribute);
        } catch (SQLException e) {
            errorRegister("[ERRO]", e.getMessage());
        }

        try {
            armorRepository.register(armor);
        } catch (SQLException e) {
            errorRegister("[ERRO]", e.getMessage());
        }
    }

    private void errorRegister(String title, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR, error);
        DialogPane dialogPane = alert.getDialogPane();
        alert.setHeaderText(title);
        dialogPane.getStylesheets().add(
                getClass().getResource("/css/myAlerts.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    @FXML
    private void comeBack() { Main.mainMenu(); }
}
