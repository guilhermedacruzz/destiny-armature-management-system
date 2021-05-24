package info.oo.control;

import info.oo.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInScene implements Initializable {

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfSecret;

    @FXML
    private CheckBox cbPass;

    @FXML
    private Button btCreate;

    @FXML
    private Label lbWelcome;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfSobrenome;

    @FXML
    private ImageView imgLogo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfNome.setPromptText("Nome");
        tfSobrenome.setPromptText("Sobrenome");
        tfUsername.setPromptText("Username");
        pfSecret.setPromptText("Senha");
    }

    @FXML
    void hidePassword() {
        if (cbPass.isSelected()){
            pfSecret.setPromptText(pfSecret.getText());
            pfSecret.setText("");
            pfSecret.setDisable(true);

        }else {
            pfSecret.setText(pfSecret.getPromptText());
            pfSecret.setPromptText("Senha");
            pfSecret.setDisable(false);
        }
    }

    @FXML
    void create() {
        System.out.println("User: " + tfUsername.getText());
        System.out.println("Password: " + pfSecret.getText());
    }

    @FXML
    void comeBack() {
        //Main.changeSceneSlideLeft(Main.LOGIN, (aClass)-> new LoginScene());
    }
}
