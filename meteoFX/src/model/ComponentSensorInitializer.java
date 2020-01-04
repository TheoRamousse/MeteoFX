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
        ms.add(new Sensor(3, "s 1", ms, new AlgoSmallFluctuation(2, 4), 1), 1);
        ms.add(new Sensor(4, "s 2", ms, new AlgoBoundedRandom(), 2), 2);
        ms.add(new Sensor(5, "s 3", ms, new AlgoSmallFluctuation(2, 4), 3), 3);
        l.add(ms);
        l.add(new Sensor(6, "Third Sensor", new AlgoRandom(), 1));

        return l;
    }

    @Override
    public void save(List<ComponentSensor> list) throws IOException {

    }
}
