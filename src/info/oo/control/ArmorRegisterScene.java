package info.oo.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
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
}
