package model;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.*;

import java.io.IOException;

/**
 * Manage the sensors created in the software
 */
public class SensorManager {
    private ListProperty<Sensor> sensorList = new SimpleListProperty<Sensor>();
    private Persistence<Sensor> persistence;

    public SensorManager(Persistence<Sensor> persistence){
        this.persistence = persistence;
        try {
            ObservableList<Sensor> test = FXCollections.observableArrayList(persistence.load());
            sensorList = new SimpleListProperty<>(test);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ListProperty<Sensor> sensorListProperty() {return sensorList;}

    public ObservableList<Sensor> getSensorList(){
        return sensorList.get();
    }

    public boolean addSensor(Sensor s)
    {
        if(findSensorById(s.getSensorId()) == null)
            return false;
        sensorList.add(s);
        return true;
    }

    public boolean deleteSensor(Sensor s)
    {
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

    public int getMaxId() {
        int result=0;
        for (Sensor s: sensorList) {
            if(s.getSensorId() > result)
                result=s.getSensorId();
        }
        return result;
    }

    public Sensor findSensorByName(String name)
    {
        for (Sensor s: sensorList) {
            if(s.getSensorName().equals(name))
                return s;
        }
        return null;
    }
}
