package model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class creates a proxy of CompositeSensor to allow of this class to be saved
 */
public abstract class CompositeSensorProxy extends ComponentSensorProxy{

    /**
     * HashMap of sensors and the coefficients associated used by this class
     */
    private HashMap<ComponentSensorProxy, Double> children = new HashMap<>();

    /**
     * Constructor of the class
     * @param cs CompositeSensor to save
     */
    public CompositeSensorProxy(CompositeSensor cs){
        super(cs);
        this.children = treeMapConverterSerialize(cs.getChildren());
    }


    /**
     * Creates a HashMap of a TreeMap to serialize it
     * @param treeMapNotConverted TreeMap to convert
     * @return HashMap serializable
     */
    private HashMap<ComponentSensorProxy, Double> treeMapConverterSerialize(TreeMap<ComponentSensor, Double> treeMapNotConverted){
        HashMap<ComponentSensorProxy, Double> result = new HashMap<>();
        for (Map.Entry<ComponentSensor, Double> currentEntry : treeMapNotConverted.entrySet()) {
            result.put((ComponentSensorProxy)currentEntry.getKey().createProxy(), currentEntry.getValue());
        }
        children = result;
        return result;
    }

    /**
     * Uses the HashMap to create a TreeMap to be usable in the software
     * @return TreeMap usable in the software
     */
    public TreeMap<ComponentSensor, Double> treeMapConverterSerializeRevert(){
        TreeMap<ComponentSensor, Double> result = new TreeMap<>(new IdComparator());
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
