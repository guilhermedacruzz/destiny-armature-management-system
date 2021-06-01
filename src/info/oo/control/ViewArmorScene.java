package info.oo.control;

import info.oo.Main;
import info.oo.model.Armor;
import info.oo.model.repository.interfaces.ArmorAttributesRepository;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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

        tvHelmet.setOnMouseClicked((evt)->{
            edit(tvHelmet.getSelectionModel().getSelectedItem());
        });

        this.initTableColumn(tcNameHelmet, tcAttributesHelmet);
        this.initTableColumn(tcNameArm, tcAttributeArm);
        this.initTableColumn(tcNameChest, tcAttributesChest);
        this.initTableColumn(tcNameBoot, tcAttributesBoot);
        this.initTableColumn(tcNameClassItem, tcAttributesClassItem);
        this.initTableColumn(tcNameExotic, tcAttributesExotic);


        try {
            int id = authService.getLogged().getId();
            String guardianClass = authService.getLogged().getGuardianClass();

            armorRepository.search(id, guardianClass);

            tvHelmet.setItems(armorRepository.organizeByType("Capacete"));
            tvArms.setItems(armorRepository.organizeByType("Manopla"));
            tvChests.setItems(armorRepository.organizeByType("Armadura de Torso"));
            tvBoots.setItems(armorRepository.organizeByType("Armadura de Perna"));
            tvClassItem.setItems(armorRepository.organizeByType("Item de Classe"));

            tvExotic.setItems(armorRepository.organizeByRarity("Exótico"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initTableColumn(TableColumn <Armor, String> tcName, TableColumn<Armor, String> tcAttributes) {
        tcName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Armor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Armor, String> armorStringCellDataFeatures) {
                return new SimpleStringProperty(armorStringCellDataFeatures.getValue().getName());
            }
        });

        tcAttributes.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Armor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Armor, String> armorStringCellDataFeatures) {
                return new SimpleStringProperty(armorStringCellDataFeatures.getValue().getArmorAttribute().getAttributes());
            }
        });
    }

    @FXML
    void edit(Armor armor) {
        if(armor != null) {
            Main.changeScene(Main.REGISTER_ARMORS, (aclass) -> new ArmorRegisterScene(authService,
                    armorRepository, armorAttributesRepository, armor), 650, 2);
            System.out.println("df");
        }
        else {
            System.out.println("as");
        }
    }

    @FXML
    void comeBack(ActionEvent event) {
        Main.mainMenu();
    }
}
