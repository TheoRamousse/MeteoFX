package Tests;

import model.*;

public class SensorTest {

    private Sensor s;

    public void testRandomSensor(){
        s = new Sensor(0,"sensor 0", new AlgoRandom(), 1);
        testSensor();
    }
    public void testSmallFluctuationSensor(){
        s = new Sensor(0, "sensor 0", new AlgoSmallFluctuation(2, -272), 1);
        testSensor();
    }

    private void testSensor (){
        s.start();
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s.getCurrentTemperature());
        }
    }
}
