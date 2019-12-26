package model;


import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.*;

public class SensorManager {
    private ListProperty<Sensor> sensorList = new SimpleListProperty<Sensor>();
    private Persistence<Sensor> persistence;

    public SensorManager(Persistence<Sensor> persistence) {
        this.persistence = persistence;
        ObservableList<Sensor> observableList =FXCollections.observableArrayList(persistence.load());
        sensorList =new SimpleListProperty<>(observableList);
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

    public boolean addSensor(Sensor s)
    {
        if(findSensorById(s.getSensorId()) != null)
            return false;
        s.start();
        sensorList.add(s);
        System.out.println(this.sensorList);
        return true;
    }

    public void deleteSensor(Sensor sensorDeleted)
    {
        sensorDeleted.stop();
        sensorList.remove(sensorDeleted);
    }

    public int getMaxId(){
        int max=0;
        for (Sensor s: sensorList) {
            if(s.getSensorId() > max)
                max = s.getSensorId();

        }
        return max;
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
