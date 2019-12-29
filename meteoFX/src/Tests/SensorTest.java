package Tests;

import model.*;

public class SensorTest {

    private Sensor s;
    private MeanSensor ms = new MeanSensor(1, "ms 1");

    public void testRandomSensor(){
        s = new Sensor(0,"sensor 0", ms, new AlgoRandom(), 1);
        testSensor();
    }
    public void testSmallFluctuationSensor(){
        s = new Sensor(0, "sensor 0", ms, new AlgoSmallFluctuation(2, -272), 1);
        testSensor();
    }

    private void testSensor (){
        ms.add(s,1);
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
