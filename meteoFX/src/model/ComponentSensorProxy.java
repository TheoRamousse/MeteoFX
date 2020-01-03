package model;

import java.io.Serializable;
import java.util.List;

public abstract class ComponentSensorProxy  implements Serializable {
    private String sensorName;
    private int sensorId;
    private double currentTemperature;

    public ComponentSensorProxy(ComponentSensor cs){
        this.sensorName = cs.getSensorName();
        this.sensorId = cs.getSensorId();
        this.currentTemperature = cs.getCurrentTemperature();
    }

    public abstract ComponentSensor computeSensor();

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}
