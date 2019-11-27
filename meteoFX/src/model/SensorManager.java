package model;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class SensorManager {
    private ListProperty<Sensor> sensorList = new SimpleListProperty();
    private Persistence<Sensor> persistence;

    public SensorManager(Persistence<Sensor> persistence) {
        this.persistence = persistence;
        sensorList = (ListProperty<Sensor>) persistence.load();
    }
}
