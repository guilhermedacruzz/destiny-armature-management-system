package info.oo.control;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class BasicScene {
    protected void sampleAlert(String title, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR, error);
        DialogPane dialogPane = alert.getDialogPane();
        alert.setHeaderText(title);
        dialogPane.getStylesheets().add(
                getClass().getResource("/css/components/myAlerts.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        alert.showAndWait();
    }
}
