package model;

import java.util.Map;

/**
 * This class inheriting of the CompositeSensor observes several ComponentSensors in order to make a mean value of all
 * their temperatures, with a coefficient for every sensor.
 */
public class MeanSensor extends CompositeSensor {

    public MeanSensor(int id, String name) {
        super(id, name);
    }

    /**
     * Is using the mathematical formula sum(component temperature * component coefficient) / sum(component coefficient)
     * in order to give a mean value of all of the sensors
     */
    @Override
    public void doTemperature() {
        double numerator = 0;
        double denominator = 0;
        for (Map.Entry<ComponentSensor, Double> entry : getChildren().entrySet()) {
            ComponentSensor sensor = entry.getKey();
            double coef = entry.getValue();
            numerator += sensor.getCurrentTemperature() * coef;
            denominator += coef;
        }
        if (denominator == 0)
            throw new UnsupportedOperationException();
        else
            setCurrentTemperature(numerator/denominator);
    }

    @Override
    public ComponentSensorProxy createProxy() {
        return new MeanSensorProxy(this);
    }
}
