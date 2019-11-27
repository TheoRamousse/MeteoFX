package model;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;

public class SensorManager {
    private ListProperty<Sensor> sensorList = new SimpleListProperty();
    private Persistence<Sensor> persistence;

    public SensorManager(Persistence<Sensor> persistence) {
        this.persistence = persistence;
        sensorList = (ListProperty<Sensor>) persistence.load();
        startSensors();
    }

    private void startSensors()
    {
        for (Sensor s: sensorList) {
            s.start();
        }
    }

    private boolean addSensor(Sensor s)
    {
        if(findSensorById(s.getSensorId()) == null)
            return false;
        sensorList.add(s);
        return true;
    }

    private boolean deleteSensor(int id)
    {
        Sensor s = findSensorById(id);
        if(s == null)
            return false;
        sensorList.remove(s);
        return true;
    }

    public Sensor findSensorById(int id)
    {
        for (Sensor s: sensorList) {
            if(s.getSensorId() == id)
                return s;
        }
        return null;
    }
}
