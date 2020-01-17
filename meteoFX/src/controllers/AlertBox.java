package controllers;

import javafx.scene.control.Alert;

/**
 * This class is here to make the use of Alert easier by having some pre usable methods to display alert boxes
 */
public class AlertBox {
    private static Alert alert;
    private static final String WARNING = "Alerte";
    private static final String INFORMATION = "Information";

    /**
     * This method will show an Warning alert
     * @param message is the message that will be shown
     */
    public static void displayWarningAlertBox(String message){
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * This method will show an Warning alert
     * @param message is the message that will be shown
     */
    public static void displayInformationAlertBox(String message){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
