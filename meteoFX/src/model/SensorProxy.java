package model;

import java.io.Serializable;
import java.util.List;

/**
 * This class creates a proxy of Sensor to allow the save of this class
 */
public class SensorProxy extends ComponentSensorProxy implements Serializable {
    private int timeUpdate;
    private SensorAlgoChanger sensorAlgoChanger;

    public SensorProxy(Sensor s){
        super(s);
        this.timeUpdate = s.getTimeUpdate();
        this.sensorAlgoChanger = s.getSensorAlgoChanger();
    }

    @Override
    public ComponentSensor computeSensor() {
        Sensor result = new Sensor(super.getSensorId(), super.getSensorName(), this.sensorAlgoChanger, this.timeUpdate);
        result.setCurrentTemperature(super.getCurrentTemperature());
        return result;
    }
}
