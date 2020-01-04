package model;

import java.util.HashMap;
import java.util.Map;

public abstract class CompositeSensorProxy extends ComponentSensorProxy{

    private HashMap<ComponentSensorProxy, Double> children = new HashMap<>();

    public CompositeSensorProxy(CompositeSensor cs){
        super(cs);
        this.children = hashMapConverterSerialize(cs.getChildren());
    }

    private HashMap<ComponentSensorProxy, Double> hashMapConverterSerialize(HashMap<ComponentSensor, Double> hashMapNotConverted){
        HashMap<ComponentSensorProxy, Double> result = new HashMap<>();
        for (Map.Entry<ComponentSensor, Double> currentEntry : hashMapNotConverted.entrySet()) {
            result.put((ComponentSensorProxy)currentEntry.getKey().createProxy(), currentEntry.getValue());
        }
        children = result;
        return result;
    }

    public HashMap<ComponentSensor, Double> hashMapConverterSerializeRevert(){
        HashMap<ComponentSensor, Double> result = new HashMap<>();
        for (Map.Entry<ComponentSensorProxy, Double> currentEntry : children.entrySet()) {
            result.put((ComponentSensor) currentEntry.getKey().computeSensor(), currentEntry.getValue());
        }
        return result;
    }

    public HashMap<ComponentSensorProxy, Double> getChildren() {
        return children;
    }

    public void setChildren(HashMap<ComponentSensorProxy, Double> children) {
        this.children = children;
    }
}
