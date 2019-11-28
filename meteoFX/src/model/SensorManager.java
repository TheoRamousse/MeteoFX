package model;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.*;

public class SensorManager {
    private ListProperty<Sensor> sensorList = new SimpleListProperty<Sensor>();
    private Persistence<Sensor> persistence;

    public SensorManager(Persistence<Sensor> persistence) {
        this.persistence = persistence;
        ObservableList<Sensor> test =FXCollections.observableArrayList(persistence.load());
        sensorList =new SimpleListProperty<>(test);
        startSensors();
    }

    private void startSensors()
    {
        for (Sensor s: sensorList) {
            s.start();
        }
    }

    public ListProperty<Sensor> sensorListProperty() {return sensorList;}

    public ObservableList<Sensor> getSensorList(){
        return sensorList.get();
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

    public Sensor findSensorByName(String name)
    {
        for (Sensor s: sensorList) {
            if(s.getSensorName() == name)
                return s;
        }
        return null;
    }
}
