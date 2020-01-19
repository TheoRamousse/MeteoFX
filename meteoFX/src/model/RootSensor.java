package model;

/**
 * This class inherits of CompositeSensor. It is mainly made for stocking all the ComponentSensors in the TreeView of
 * the view
 */
public class RootSensor extends CompositeSensor {

    public RootSensor() {
        super(0, "root");
    }

    /**
     * If doTemperature is empty, kit's because RootSensor is not meant to simulate a temperature
     */
    @Override
    public void doTemperature() {
    }

    @Override
    public ComponentSensorProxy createProxy() {
        return new CompositeSensorProxy(this) {
            @Override
            public ComponentSensor computeSensor() {
                return null;
            }
        };
    }
}
