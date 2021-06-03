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

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ArmorRegisterScene extends BasicScene implements Initializable {

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
    private RadioButton rbYesMasterprice;

    @FXML
    private RadioButton rbNoMasterprice;

    @FXML
    private RadioButton rbSolar;

    @FXML
    private RadioButton rbVoid;

    @FXML
    private RadioButton rbArc;

    private AuthService authService;
    private ArmorRepository armorRepository;
    private ArmorAttributesRepository armorAttributesRepository;
    private Armor currentArmor;

    public ArmorRegisterScene(AuthService authService, ArmorRepository armorRepository,
                              ArmorAttributesRepository armorAttributesRepository, Armor currentArmor) {
        this.authService = authService;
        this.armorRepository = armorRepository;
        this.armorAttributesRepository = armorAttributesRepository;
        this.currentArmor = currentArmor;
    }

    public ArmorRegisterScene(AuthService authService, ArmorRepository armorRepository,
                              ArmorAttributesRepository armorAttributesRepository) {
        this(authService, armorRepository, armorAttributesRepository, null);
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

        if(!(currentArmor == null)) {
            tfName.setText(currentArmor.getName());

            tfMobility.setText(String.valueOf(currentArmor.getArmorAttribute().getMobility()));
            tfResilience.setText(String.valueOf(currentArmor.getArmorAttribute().getResilience()));
            tfRecovery.setText(String.valueOf(currentArmor.getArmorAttribute().getRecovery()));
            tfDicipline.setText(String.valueOf(currentArmor.getArmorAttribute().getDicipline()));
            tfIntellect.setText(String.valueOf(currentArmor.getArmorAttribute().getIntellect()));
            tfStrenght.setText(String.valueOf(currentArmor.getArmorAttribute().getStrenght()));

            switch (currentArmor.getType()) {
                case "Capacete":
                    rbHelmet.setSelected(true);
                    break;
                case "Manopla":
                    rbArm.setSelected(true);
                    break;
                case "Armadura de Torso":
                    rbChest.setSelected(true);
                    break;
                case "Armadura de Perna":
                    rbBoot.setSelected(true);
                    break;
                case "Item de Classe":
                    rbClassItem.setSelected(true);
                    break;
            }

            switch (currentArmor.getElement()) {
                case "Arco":
                    rbArc.setSelected(true);
                    break;
                case "Solar":
                    rbSolar.setSelected(true);
                    break;
                case "Vácuo":
                    rbVoid.setSelected(true);
                    break;
            }

            switch (currentArmor.getRarity()) {
                case "Exótico":
                    rbExotic.setSelected(true);
                    break;
                case "Lendário":
                    rbLegendary.setSelected(true);
                    break;
            }

            rbYesMasterprice.setSelected(currentArmor.isStatusMasterprice());
            rbNoMasterprice.setSelected(!currentArmor.isStatusMasterprice());
        }
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
            if(currentArmor != null) {
                armor.getArmorAttribute().setId(currentArmor.getArmorAttribute().getId());
                System.out.println(armorAttributesRepository.updateAttribute(armorAttribute));
            }
            else {
                armorAttributesRepository.registerAttribute(armorAttribute);
            }
        } catch (SQLException e) {
            errorRegister("[ERRO]", e.getMessage());
            return;
        }

        try {
            if(currentArmor != null) {
                armor.setId(currentArmor.getId());
                armorRepository.update(armor);
            }
            else {
                armorRepository.insert(armor);
            }
        } catch (SQLException e) {
            errorRegister("[ERRO]", e.getMessage());
            return;
        }

        errorRegister("[OK]", "Armadura Cadastrada com Sucesso!");
        clearInfos();
    }

    private void clearInfos() {
        tfName.setText("");

        tfMobility.setText("2");
        tfResilience.setText("2");
        tfRecovery.setText("2");
        tfDicipline.setText("2");
        tfIntellect.setText("2");
        tfStrenght.setText("2");

        rbYesMasterprice.setSelected(false);
        rbNoMasterprice.setSelected(false);

        rbLegendary.setSelected(false);
        rbExotic.setSelected(false);

        rbArc.setSelected(false);
        rbSolar.setSelected(false);
        rbVoid.setSelected(false);

        rbHelmet.setSelected(false);
        rbArm.setSelected(false);
        rbChest.setSelected(false);
        rbBoot.setSelected(false);
        rbClassItem.setSelected(false);
    }

    @FXML
    private void comeBack() { Main.mainMenu(); }
}
