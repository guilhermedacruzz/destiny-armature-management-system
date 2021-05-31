package info.oo;

import info.oo.control.CalculateArmorScene;
import info.oo.control.ViewArmorScene;
import info.oo.model.Armor;
import info.oo.model.ArmorAttribute;
import info.oo.model.ConnectionsFactory;
import info.oo.model.daos.JDBCArmor;
import info.oo.model.daos.JDBCAttributes;
import info.oo.model.daos.interfaces.ArmorDAO;
import info.oo.model.daos.interfaces.AttributesDAO;
import info.oo.model.repository.ArmorAttributesRepositoryImpl;
import info.oo.model.repository.ArmorRepositoryImpl;
import info.oo.model.repository.interfaces.ArmorAttributesRepository;
import info.oo.model.repository.interfaces.ArmorRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class Teste extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

}