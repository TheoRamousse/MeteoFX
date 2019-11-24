package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import model.Sensor;
import model.SensorManager;

public class AllSensors {
    @FXML
    private ListView sensorsList;
    private SensorManager sensorManager;

    public void setSensorManager(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
    }

    private void fillsensorList(){
        if (sensorManager != null) {
            for (Sensor sensor : sensorManager.getSensors()) {
                sensorsList.getItems().add(sensorDisplay(sensor));
            }
        }
    }

    private HBox sensorDisplay(Sensor sensor) {
        HBox element = new HBox();
        element.getChildren().addAll(new Label(sensor.getSensorId() + " : " + sensor.getSensorName()));
        return element;
    }

    @FXML
    public void initialize(){
        sensorManager = new SensorManager();
        sensorManager.addRandomSensors(2);
        fillsensorList();
    }
}
