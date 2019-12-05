package model;

import java.util.ArrayList;
import java.util.List;

public class SensorInitializer implements Persistence<Sensor> {

    private String nomDuFichier;

    public SensorInitializer(String nomDuFichier)
    {
        this.nomDuFichier = nomDuFichier;
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
}
