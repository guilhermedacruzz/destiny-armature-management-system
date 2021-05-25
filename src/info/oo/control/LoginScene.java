package info.oo.control;

import info.oo.Main;
import info.oo.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class LoginScene implements Initializable {

    @FXML
    private ImageView imgLogo;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfSecret;

    @FXML
    private Button btSend;

    @FXML
    private Label lbSignUp;

    @FXML
    private Label lbWelcome;

    @FXML
    private CheckBox cbPass;

    private AuthService authService;

    public LoginScene(AuthService authService) {
        this.authService = authService;
    }

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
        String username = tfUsername.getText();
        String secret = pfSecret.getText();

        String msg = "";
        try {
            boolean status = authService.login(username, secret);

            if(status) {
                Main.changeSceneFade(Main.CHARACTER, (aClass) -> new CharacterScene(authService));
                return;
            } else {
                msg = "Usuário Inválido!";
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }

        Alert alert = new Alert(Alert.AlertType.ERROR,msg);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/css/myAlerts.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }

    @FXML
    void changeScene() {
        Main.changeSceneSlideRight(Main.REGISTER, (aClass)-> new SignInScene(authService));
    }
}
