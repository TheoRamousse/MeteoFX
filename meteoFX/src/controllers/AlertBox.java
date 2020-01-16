package controllers;

import javafx.scene.control.Alert;

public class AlertBox {
    private static Alert alert;
    private static final String WARNING = "Alerte";
    private static final String INFORMATION = "information";

    public static void displayWarningAlertBox(String message){
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void displayInformationAlertBox(String message){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
