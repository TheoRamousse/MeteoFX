package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUtility {


    /**
     * This method is used to create and configure a stage easily thanks to parameters given in parameters
     * @param  controller Controller used by the view to work
     * @param pathFxml Path of the FXML view to show
     * @param windowTitle Title of the window
     * @param width Width of the window
     * @param height Height of the window
     * @return Stage configured and ready to be shown
     */
    public static Stage createStage(Object controller, String pathFxml, String windowTitle, int width, int height) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(ControllerUtility.class.getResource(pathFxml));
        loader.setController(controller);
        primaryStage.setTitle(windowTitle);
        Scene mainScene = new Scene(loader.load(), width, height);
        mainScene.getStylesheets().add(ControllerUtility.class.getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(mainScene);
        return primaryStage;
    }
}
