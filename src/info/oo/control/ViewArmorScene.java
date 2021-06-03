package info.oo.control;

import info.oo.Main;
import info.oo.model.Armor;
import info.oo.model.repository.interfaces.ArmorAttributesRepository;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewArmorScene extends BasicScene implements Initializable {

    @FXML
    private TableView<Armor> tvHelmet;

    @FXML
    private TableColumn<Armor, String> tcNameHelmet;

    @FXML
    private TableColumn<Armor, String> tcAttributesHelmet;

    @FXML
    private TableView<Armor> tvArms;

    @FXML
    private TableColumn<Armor, String> tcNameArm;

    @FXML
    private TableColumn<Armor, String> tcAttributeArm;

    @FXML
    private TableView<Armor> tvChests;

    @FXML
    private TableColumn<Armor, String> tcNameChest;

    @FXML
    private TableColumn<Armor, String> tcAttributesChest;

    @FXML
    private TableView<Armor> tvBoots;

    @FXML
    private TableColumn<Armor, String> tcNameBoot;

    @FXML
    private TableColumn<Armor, String> tcAttributesBoot;

    @FXML
    private TableView<Armor> tvClassItem;

    @FXML
    private TableColumn<Armor, String> tcNameClassItem;

    @FXML
    private TableColumn<Armor, String> tcAttributesClassItem;

    @FXML
    private TableView<Armor> tvExotic;

    @FXML
    private TableColumn<Armor, String> tcNameExotic;

    @FXML
    private TableColumn<Armor, String> tcAttributesExotic;

    private ArmorRepository armorRepository;
    private ArmorAttributesRepository armorAttributesRepository;
    private AuthService authService;

    public ViewArmorScene(ArmorRepository armorRepository, ArmorAttributesRepository armorAttributesRepository, AuthService authService) {
        this.armorRepository = armorRepository;
        this.armorAttributesRepository = armorAttributesRepository;
        this.authService = authService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initTableView(tvHelmet);
        initTableView(tvArms);
        initTableView(tvChests);
        initTableView(tvBoots);
        initTableView(tvClassItem);
        initTableView(tvExotic);

        initTableColumn(tcNameHelmet, tcAttributesHelmet);
        initTableColumn(tcNameArm, tcAttributeArm);
        initTableColumn(tcNameChest, tcAttributesChest);
        initTableColumn(tcNameBoot, tcAttributesBoot);
        initTableColumn(tcNameClassItem, tcAttributesClassItem);
        initTableColumn(tcNameExotic, tcAttributesExotic);


        try {
            int id = authService.getLogged().getId();
            String guardianClass = authService.getLogged().getGuardianClass();

            tvHelmet.setItems(armorRepository.selectByType(id, guardianClass, "Capacete"));
            tvArms.setItems(armorRepository.selectByType(id, guardianClass, "Manopla"));
            tvChests.setItems(armorRepository.selectByType(id, guardianClass, "Armadura de Torso"));
            tvBoots.setItems(armorRepository.selectByType(id, guardianClass, "Armadura de Perna"));
            tvClassItem.setItems(armorRepository.selectByType(id, guardianClass, "Item de Classe"));
            tvExotic.setItems(armorRepository.selectByRarity(id, guardianClass, "Exótico"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initTableColumn(TableColumn <Armor, String> tcName, TableColumn<Armor, String> tcAttributes) {
        tcName.setCellValueFactory(armorStringCellDataFeatures -> new SimpleStringProperty(armorStringCellDataFeatures.getValue().getName()));

        tcAttributes.setCellValueFactory(armorStringCellDataFeatures -> new SimpleStringProperty(armorStringCellDataFeatures.getValue().getArmorAttribute().getAttributes()));
    }

    private void initTableView(TableView <Armor> tableView) {
        tableView.setOnMouseClicked((evt)-> edit(tableView.getSelectionModel().getSelectedItem()));
    }

    @FXML
    void edit(Armor armor) {
        if(armor != null) {
            Main.changeScene(Main.REGISTER_ARMORS, (aclass) -> new RegisterArmorScene(authService,
                    armorRepository, armorAttributesRepository, armor), 650, 2);
            System.out.println("df");
        }
        else {
            System.out.println("as");
        }
    }

    @FXML
    void comeBack() {
        Main.mainMenu();
    }
}
