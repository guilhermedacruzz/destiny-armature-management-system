package info.oo.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginScene implements Initializable {

    @FXML
    ImageView imgLogo;

    @FXML
    TextField tfUsername;

    @FXML
    PasswordField pfSecret;

    @FXML
    Button btSend;

    @FXML
    Label lbSignUp;

    @FXML
    Label lbWelcome;

    @FXML
    CheckBox cbPass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    void login() {
        System.out.println("User: " + tfUsername.getText());
        System.out.println("Password: " + pfSecret.getText());
    }
}
