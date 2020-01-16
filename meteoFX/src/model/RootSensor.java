package model;

public class RootSensor extends CompositeSensor {

    public RootSensor() {
        super(0, "root");
    }


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
