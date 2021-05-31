package info.oo.control;

import info.oo.model.Armor;
import info.oo.model.ResultArmor;
import info.oo.model.repository.interfaces.ArmorRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CalculateArmorScene implements Initializable {

    @FXML
    private TableView<Armor> tvExotic;

    @FXML
    private TableColumn<Armor, String> tcName;

    @FXML
    private TableColumn<Armor, String> tcAttributes;

    @FXML
    private TableView<ResultArmor> tvResult;

    @FXML
    private TableColumn<ResultArmor, String> tcResult;

    private ArmorRepository armorRepository;

    private List<Armor> armorList;

    public CalculateArmorScene(ArmorRepository armorRepository) {
        this.armorRepository = armorRepository;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tvExotic.setOnMouseClicked((evt)->{
            calcule();
        });

        initTableColumn();

        try {
            armorList = armorRepository.search(1);

            tvExotic.setItems(armorRepository.organizeByRarity(armorList, "Exótico"));
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

        tcResult.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ResultArmor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ResultArmor, String> resultArmorStringCellDataFeatures) {
                return new SimpleStringProperty(resultArmorStringCellDataFeatures.getValue().getAttributesSet().toString());
            }
        });
    }


    private void calcule() {
        Armor exotic = tvExotic.getSelectionModel().getSelectedItem();
        tvResult.setItems(armorRepository.resultCalculateArmors(armorList, exotic));
    }
}
