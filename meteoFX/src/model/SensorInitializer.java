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
        l.add(new RandomSensor(1, "First Sensor", 5));
        l.add(new RandomSensor(2, "Second Sensor", 7));
        l.add(new RandomSensor(3, "Third Sensor", 9));
        return l;
    }

    @Override
    public void save(List<Sensor> list) { }
}
