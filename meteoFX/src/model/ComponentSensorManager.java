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
            cSensorList = new SimpleListProperty<ComponentSensor>((ObservableList<ComponentSensor>) persistence.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ListProperty<ComponentSensor> componentSensorListProperty() {return cSensorList;}


}
