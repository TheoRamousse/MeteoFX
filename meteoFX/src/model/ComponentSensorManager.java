package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class ComponentSensorManager {
    private ListProperty<ComponentSensor> cSensorList = new SimpleListProperty<>();
    private Persistence<ComponentSensor> persistence;

    public ComponentSensorManager(Persistence<ComponentSensor> persistence) {
        this.persistence = persistence;
        try {
            ObservableList<ComponentSensor> oList = FXCollections.observableArrayList(persistence.load());
            cSensorList = new SimpleListProperty<ComponentSensor>(oList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ComponentSensorManager(List<ComponentSensor> cps){
        persistence = null;
        ObservableList<ComponentSensor> oList = FXCollections.observableArrayList(cps);
        cSensorList = new SimpleListProperty<ComponentSensor>(oList);
    }

    public ListProperty<ComponentSensor> componentSensorListProperty() {return cSensorList;}


    public ComponentSensor findComponentSensorById(int sensorId) {
        for (ComponentSensor cs: cSensorList) {
            if(cs.getSensorId() == sensorId)
                return cs;
        }
        return null;
    }

    public List<ComponentSensor> getSensorList(){
        return cSensorList.get();
    }


    public boolean deleteSensor(ComponentSensor cs) {
        if(cs == null)
            return false;
        cSensorList.remove(cs);
        return true;
    }

    public boolean addSensor(ComponentSensor cs)
    {
        if(findComponentSensorById(cs.getSensorId()) != null)
            return false;
        cSensorList.add(cs);
        return true;
    }

    public int getMaxId() {
        int result=0;
        for (ComponentSensor s: cSensorList) {
            if(s.getSensorId() > result)
                result=s.getSensorId();
        }
        return result;
    }

    public ComponentSensor findSensorByName(String name)
    {
        for (ComponentSensor cs: cSensorList) {
            if(cs.getSensorName().equals(name))
                return cs;
        }
        return null;
    }

    public void save() throws IOException {
        persistence.save(getSensorList());
    }
}
