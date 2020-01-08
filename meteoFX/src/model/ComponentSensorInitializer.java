package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComponentSensorInitializer implements Persistence<ComponentSensor>{

    @Override
    public List<ComponentSensor> load() throws IOException {
        List<ComponentSensor> l = new ArrayList<>();
        l.add(new Sensor(1, "First Sensor", new AlgoBoundedRandom(), 5));
        MeanSensor ms = new MeanSensor(2, "MeanSensor1");
        Sensor s1 = new Sensor(3, "s 1", new AlgoSmallFluctuation(2, 4), 1);
        try {
            s1.addObserver(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ms.add(s1, 1);
        Sensor s2 = new Sensor(4, "s 2", new AlgoBoundedRandom(), 2);
        try {
            s2.addObserver(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ms.add(s2, 2);
        Sensor s3 = new Sensor(5, "s 3", new AlgoSmallFluctuation(2, 4),3);
        try {
            s3.addObserver(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ms.add(s2, 3);
        l.add(ms);
        l.add(new Sensor(6, "Third Sensor", new AlgoRandom(), 1));

        return l;
    }

    @Override
    public void save(List<ComponentSensor> list) throws IOException {

    }
}
