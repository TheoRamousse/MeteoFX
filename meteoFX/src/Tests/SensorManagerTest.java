package Tests;

import model.Sensor;
import model.SensorInitializer;
import model.SensorManager;

import java.io.IOException;

public class SensorManagerTest {
    public static void testSM() throws InterruptedException, IOException {
        SensorManager sm = new SensorManager(new SensorInitializer("salut"));
        /*Sensor s = sm.findSensorById(1);
        while(true)
        {
            System.out.println(s.getCurrentTemperature());
            Thread.sleep(500);
        }*/
    }
}
