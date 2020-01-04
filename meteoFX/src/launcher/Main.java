/*
copy that into the VM options : --module-path ${PATH_TO_JFX} --add-modules javafx.controls,javafx.fxml
* */

package launcher;

import controllers.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {

    public ComponentSensorManager sm = new ComponentSensorManager(new ComponentSensorInitializer());
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
        MainView cv = new MainView(sm);
        loader.setController(cv);
        primaryStage.setTitle("MeteoFX");
        Scene mainScene = new Scene(loader.load(), 1000, 650);
        mainScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
