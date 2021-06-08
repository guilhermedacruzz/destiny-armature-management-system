package info.oo.control;

import info.oo.Main;
import info.oo.model.Armor;
import info.oo.model.ArmorSet;
import info.oo.model.repository.ArmorSetRepositoryImpl;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CalculateArmorScene extends BasicScene implements Initializable {

    @FXML
    private TableView<Armor> tvExotic;

    @FXML
    private TableColumn<Armor, String> tcName;

    @FXML
    private TableColumn<Armor, String> tcAttributes;

    @FXML
    private TableView<ArmorSet> tvResult;

    @FXML
    private TableColumn<ArmorSet, String> tcResult;

    @FXML
    private CheckBox cbPowerfulFriends;

    @FXML
    private CheckBox cbRadiantLight;

    @FXML
    private CheckBox cbStasis;

    private ArmorRepository armorRepository;
    private AuthService authService;
    private ArmorSetRepositoryImpl armorSetRepositoryImpl;

    private ObservableList<ArmorSet> armorSetObservableList;

    public CalculateArmorScene(ArmorRepository armorRepository, AuthService authService) {
        this.armorRepository = armorRepository;
        this.authService = authService;

        armorSetRepositoryImpl =  new ArmorSetRepositoryImpl(authService, armorRepository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvResult.setOnMouseClicked((evt)-> {
            open();
        });

        tvExotic.setOnMouseClicked((evt)->{
            calcule();
        });

        initTableColumn();

        try {
            int id = authService.getLogged().getId();
            String guardianClass = authService.getLogged().getGuardianClass();

            tvExotic.setItems(armorRepository.selectByRarity(id, guardianClass, "Exótico"));
        } catch (SQLException e) {
            sampleAlert("[ERRO]", e.getMessage());
        }
    }

    private void initTableColumn() {
        tcName.setCellValueFactory(armorStringCellDataFeatures -> new SimpleStringProperty(armorStringCellDataFeatures.getValue().getName()));

        tcAttributes.setCellValueFactory(armorStringCellDataFeatures -> new SimpleStringProperty(armorStringCellDataFeatures.getValue().getArmorAttribute().getAttributes()));

        tcResult.setCellValueFactory(armorSetStringCellDataFeatures -> new SimpleStringProperty(armorSetStringCellDataFeatures.getValue().generateAttributes().getAttributes()));

    }


    private void calcule() {
        boolean powerfulFriends = cbPowerfulFriends.isSelected();
        boolean radiantLight = cbRadiantLight.isSelected();
        boolean stasis = cbStasis.isSelected();

        Armor exotic = tvExotic.getSelectionModel().getSelectedItem();

        if(exotic == null)
            return;

        try {
            armorSetObservableList = armorSetRepositoryImpl.calculate(exotic, powerfulFriends, radiantLight, stasis);
            tvResult.setItems(armorSetObservableList);
        } catch (SQLException e) {
            sampleAlert("[ERRO]", e.getMessage());
        }
    }


    @FXML
    private void comeBack() {
        Main.mainMenu();
    }

    void open() {
        ArmorSet armorSet = tvResult.getSelectionModel().getSelectedItem();

        if(armorSet != null) {
            String path = System.getProperty("user.dir") + "\\resources\\results\\" + authService.getLogged().getUsername() + ".txt";
            path = path.replaceAll(" ", "_");


            try(BufferedWriter bw = new BufferedWriter(
                    new FileWriter(
                            path))){

                for(ArmorSet armorSet1: armorSetObservableList) {
                    bw.write(armorSet1.generateAttributes().getAttributes());
                    bw.newLine();

                    for(Armor armor: armorSet1.getArmors()) {
                        bw.write(armor.getName() + " - " + armor.getType());
                        bw.newLine();
                    }
                }

            }catch (IOException e){
                sampleAlert("[ERRO]", e.getMessage());
            }

            File u = new File(path);
            Desktop d = Desktop.getDesktop();

            try {
                d.open(u);
            } catch (IOException e) {
                sampleAlert("[ERRO]", e.getMessage());
            }

        }
    }
}
