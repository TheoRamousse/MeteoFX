package launcher;

import Tests.SensorManagerTest;
import Tests.SensorTest;
import controllers.CamView;
import controllers.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.RandomSensor;
import model.Sensor;
import model.SensorInitializer;
import model.SensorManager;

public class Main extends Application {

    public SensorManager sm = new SensorManager(new SensorInitializer("coucou"));
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
        MainView cv = new MainView(sm);
        loader.setController(cv);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(loader.load(), 1200, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        /*try {
            SensorManagerTest.testSM();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        launch(args);
    }
}
