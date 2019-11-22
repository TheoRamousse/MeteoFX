package Tests;

import model.RandomSensor;
import model.Sensor;

public class SensorTest {
    public void testRandomCensor(){
        Sensor rs = new RandomSensor(0,"rs0", 2);
        rs.start();
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(rs.getCurrentTemperature());
        }
    }
}
