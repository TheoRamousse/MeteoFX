package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

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
        l.add(new RandomSensor(2, "Second Sensor", 1));
        l.add(new RandomSensor(3, "Third Sensor", 9));
        return l;
    }

    @Override
    public void save(List<Sensor> list) { }
}
