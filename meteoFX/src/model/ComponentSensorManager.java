package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class ComponentSensorManager {
    private ListProperty<ComponentSensor> cSensorList;
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

    public ListProperty<ComponentSensor> componentSensorListProperty() {return cSensorList;}


    public ComponentSensor findComponentSensorById(int sensorId) {
        for (ComponentSensor s: cSensorList) {
            if(s.getSensorId() == sensorId)
                return s;
        }
        return null;
    }

    public boolean deleteSensor(ComponentSensor s) {
        if(s == null)
            return false;
        cSensorList.remove(s);
        return true;
    }
}
