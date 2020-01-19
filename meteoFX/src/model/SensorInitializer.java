package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub of sensors (used for the tests)
 */
public class SensorInitializer implements Persistence<Sensor> {

    private String fileName;

    public SensorInitializer(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public List<Sensor> load() {
        List<Sensor> l = new ArrayList<>();
        l.add(new Sensor(1, "First Sensor", new AlgoBoundedRandom(), 5));
        l.add(new Sensor(2, "Second Sensor", new AlgoSmallFluctuation(0.2,200), 2));
        l.add(new Sensor(3, "Third Sensor", new AlgoRandom(), 1));

        return l;
    }

    @Override
    public void save(List<Sensor> list) { }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
