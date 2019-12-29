package Tests;

import model.AlgoBoundedRandom;
import model.AlgoSmallFluctuation;
import model.MeanSensor;
import model.Sensor;

public class MeanSensorTest {
    private MeanSensor ms1 = new MeanSensor(1, "ms 1");
    private Sensor s1 = new Sensor(1, "s 1", ms1, new AlgoSmallFluctuation(2, 4), 1);
    private Sensor s2 = new Sensor(2, "s 2", ms1, new AlgoBoundedRandom(), 2);
    private Sensor s3 = new Sensor(3, "s 3", ms1, new AlgoSmallFluctuation(2, 4), 3);

    public void testMeanSensor() {
        ms1.add(s1, 3);
        ms1.add(s2, 6);
        ms1.add(s3, 2);
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("s1 " + s1.getCurrentTemperature());
            System.out.println("s2 " + s2.getCurrentTemperature());
            System.out.println("s3 " + s3.getCurrentTemperature());
            System.out.println("ms1 " + ms1.getCurrentTemperature());
        }
    }
}
