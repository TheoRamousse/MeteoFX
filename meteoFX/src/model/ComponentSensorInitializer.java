package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class inheriting of the persistence meant to initialize the censors to certain values.
 */
public class ComponentSensorInitializer implements Persistence<ComponentSensor>{

    @Override
    public List<ComponentSensor> load() throws IOException {
        List<ComponentSensor> l = new ArrayList<>();
        try {
            l.add(new Sensor(1, "First Sensor", new AlgoBoundedRandom(), 5));
            MeanSensor ms = new MeanSensor(2, "MeanSensor1");
            Sensor s1 = new Sensor(3, "s 1", new AlgoSmallFluctuation(2, 4), 1);
            s1.addObserver(ms);
            ms.add(s1, 1);
            Sensor s2 = new Sensor(4, "s 2", new AlgoBoundedRandom(), 2);
            s2.addObserver(ms);
            ms.add(s2, 2);
            Sensor s3 = new Sensor(5, "s 3", new AlgoSmallFluctuation(2, 4),3);
            s3.addObserver(ms);
            ms.add(s2, 3);
            l.add(ms);
            l.add(new Sensor(6, "Third Sensor", new AlgoRandom(), 1));
        }catch (Exception e) {
            e.printStackTrace();
        }


        return l;
    }

    @Override
    public void save(List<ComponentSensor> list) throws IOException {

    }
}
