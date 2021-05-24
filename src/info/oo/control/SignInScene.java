package info.oo.control;

import info.oo.Main;
import info.oo.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
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

    private AuthService authService;

    public SignInScene(AuthService authService) {
        this.authService = authService;
    }

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
        String username = tfUsername.getText();
        String secret = pfSecret.getText();
        String name = tfNome.getText();
        String surname = tfSobrenome.getText();

        String msg = "";
        try {
            boolean status = authService.signIn(name, surname, username, secret);

            if(status) {
                msg = "Usuário cadastrado com Sucesso";
            }
            else {
                msg = "Erro ao tentar cadastrar o Usuário";
            }
        } catch (SQLException e) {
            msg = e.getMessage();
        }

        Alert alert = new Alert(Alert.AlertType.ERROR,msg);
        alert.showAndWait();

        comeBack();
    }

    @FXML
    void comeBack() {
        Main.changeSceneSlideLeft(Main.LOGIN, (aClass)-> new LoginScene(authService));
    }
}
