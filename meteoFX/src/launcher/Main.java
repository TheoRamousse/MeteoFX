/*
copy that into the VM options : --module-path ${PATH_TO_JFX} --add-modules javafx.controls,javafx.fxml
* */

package launcher;

import Tests.MeanSensorTest;
import Tests.SensorTest;
import controllers.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MeanSensor;
import model.SensorInitializer;
import model.SensorManager;

public class Main extends Application {

    public SensorManager sm = new SensorManager(new SensorInitializer("coucou"));
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
        MainView cv = new MainView(sm);
        loader.setController(cv);
        primaryStage.setTitle("MeteoFX");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
