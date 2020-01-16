package model;

import java.io.Serializable;
import java.util.List;


/**
 * This class creates a proxy of ComponentSensor to allow the save of this class
 */
public abstract class ComponentSensorProxy  implements Serializable {

    /**
     * Name of the sensor
     */
    private String sensorName;

    /**
     * Id of the sensor
     */
    private int sensorId;

    /**
     * Temperature of the sensor at a given time
     */
    private double currentTemperature;


    /**
     *  Constructor of the class
     * @param cs ComponentSensor to save
     */
    public ComponentSensorProxy(ComponentSensor cs){
        this.sensorName = cs.getSensorName();
        this.sensorId = cs.getSensorId();
        this.currentTemperature = cs.getCurrentTemperature();
    }

    public abstract ComponentSensor computeSensor();

    /**
     * Return the name of the sensor
     * @return This is the name returned
     */
    public String getSensorName() {
        return sensorName;
    }

    /**
     * Change the name of the sensor
     * @param sensorName New name of the sensor
     */
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    /**
     * Return the id of the sensor
     * @return This is the id returned
     */
    public int getSensorId() {
        return sensorId;
    }

    /**
     * Change the id of the sensor
     * @param sensorId New id of the sensor
     */
    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }


    /**
     * Return the current temperature of the sensor
     * @return This is the current temperature returned
     */
    public double getCurrentTemperature() {
        return currentTemperature;
    }

     /**
     * Change the current temperature of the sensor
     * @param currentTemperature New temperature of the sensor
     */
    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}
