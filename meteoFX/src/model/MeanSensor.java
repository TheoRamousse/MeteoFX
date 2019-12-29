package model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MeanSensor extends CompositeSensor {
    
    public MeanSensor(int id, String name) {
        super(id, name);
    }

    public MeanSensor(int id, String name, MeanSensor obserever) {
        super(id, name, obserever);
    }

    @Override
    public void doTemperature() {
        double numerator = 0;
        double denominator = 0;
        for (Map.Entry<ComponentSensor, Double> entry : getChildren().entrySet()) {
            ComponentSensor sensor = entry.getKey();
            Double coef = entry.getValue();
            numerator += sensor.getCurrentTemperature() * coef;
            denominator += coef;
        }
        if (denominator == 0)
            throw new UnsupportedOperationException();
        else
            setCurrentTemperature(numerator/denominator);
    }
}
