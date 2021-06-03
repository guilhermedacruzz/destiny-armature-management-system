package info.oo.control;

import info.oo.Main;
import info.oo.model.Armor;
import info.oo.model.ArmorSet;
import info.oo.model.repository.ArmorSetRepositoryImpl;
import info.oo.model.repository.interfaces.ArmorRepository;
import info.oo.services.AuthService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
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

    public CalculateArmorScene(ArmorRepository armorRepository, AuthService authService) {
        this.armorRepository = armorRepository;
        this.authService = authService;

        armorSetRepositoryImpl =  new ArmorSetRepositoryImpl(authService, armorRepository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvExotic.setOnMouseClicked((evt)->{
            calcule();
        });

        initTableColumn();

        try {
            int id = authService.getLogged().getId();
            String guardianClass = authService.getLogged().getGuardianClass();

            tvExotic.setItems(armorRepository.selectByRarity(id, guardianClass, "Exótico"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void initTableColumn() {
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

        tcResult.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ArmorSet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ArmorSet, String> armorSetStringCellDataFeatures) {
                return new SimpleStringProperty(armorSetStringCellDataFeatures.getValue().generateAttributes().getAttributes());
            }
        });

    }


    private void calcule() {
        boolean powerfulFriends = cbPowerfulFriends.isSelected();
        boolean radiantLight = cbRadiantLight.isSelected();
        boolean stasis = cbStasis.isSelected();

        Armor exotic = tvExotic.getSelectionModel().getSelectedItem();

        try {
            tvResult.setItems(armorSetRepositoryImpl.calculate(exotic, powerfulFriends, radiantLight, stasis));
        } catch (SQLException e) {
            errorRegister("[ERRO]", e.getMessage());
        }
    }


    @FXML
    private void comeBack() {
        Main.mainMenu();
    }
}
