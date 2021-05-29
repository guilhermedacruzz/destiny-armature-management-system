package info.oo.control;

import info.oo.model.Armor;
import info.oo.model.repository.interfaces.ArmorRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewArmorScene implements Initializable {

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

    public ViewArmorScene(ArmorRepository armorRepository) {
        this.armorRepository = armorRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.initTableColumn(tcNameHelmet, tcAttributesHelmet);
        this.initTableColumn(tcNameArm, tcAttributeArm);
        this.initTableColumn(tcNameChest, tcAttributesChest);
        this.initTableColumn(tcNameBoot, tcAttributesBoot);
        this.initTableColumn(tcNameClassItem, tcAttributesClassItem);
        this.initTableColumn(tcNameExotic, tcAttributesExotic);


        try {
            List<Armor> armorList = armorRepository.search(1);

            tvHelmet.setItems(armorRepository.organizeByType(armorList, "Capacete"));
            tvArms.setItems(armorRepository.organizeByType(armorList, "Manopla"));
            tvChests.setItems(armorRepository.organizeByType(armorList, "Armadura de Torso"));
            tvBoots.setItems(armorRepository.organizeByType(armorList, "Armadura de Perna"));
            tvClassItem.setItems(armorRepository.organizeByType(armorList, "Item de Classe"));

            tvExotic.setItems(armorRepository.organizeByRarity(armorList, "Exótico"));
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
                return new SimpleStringProperty(armorStringCellDataFeatures.getValue().getArmorAttribute().toString());
            }
        });
    }

    @FXML
    void comeBack(ActionEvent event) {
        return;
    }
}
