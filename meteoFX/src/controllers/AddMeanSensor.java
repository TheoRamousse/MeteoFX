package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import model.ComponentSensorManager;

import java.lang.reflect.Constructor;

public class AddMeanSensor {

    @FXML
    ComboBox<String> sensorContainer;

    private ComponentSensorManager sm;

    public AddMeanSensor(ComponentSensorManager sm) {

        this.sm = sm;
    }

    @FXML
    public void initialize() {

    }
}
