package model;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class creates a proxy of CompositeSensor to allow the save of this class
 */
public abstract class CompositeSensorProxy extends ComponentSensorProxy{

    /**
     * TreeMap of sensors and the coefficients associated used by this class
     */
    private TreeMap<ComponentSensorProxy, Double> children;

    /**
     * Constructor of the class
     * @param cs CompositeSensor to save
     */
    public CompositeSensorProxy(CompositeSensor cs){
        super(cs);
        this.children = treeMapConverterSerialize(cs.getChildren());
    }

    /**
     * Convert the elements of the TreeMap to save them too (change ComponentSensor into ComponentSensorProxy)
     * @param treeMapNotConverted TreeMap to save
     * @return TreeMap converted
     */
    private TreeMap<ComponentSensorProxy, Double> treeMapConverterSerialize(TreeMap<ComponentSensor, Double> treeMapNotConverted){
        TreeMap<ComponentSensorProxy, Double> result = new TreeMap<>();
        for (Map.Entry<ComponentSensor, Double> currentEntry : treeMapNotConverted.entrySet()) {
            result.put((ComponentSensorProxy)currentEntry.getKey().createProxy(), currentEntry.getValue());
        }
        children = result;
        return result;
    }

    /**
     * This is the reverse of treeMapConverterSerialize. Used to convert a TreeMap of proxy into a simple TreeMap
     * @return TreeMap converted
     */
    TreeMap<ComponentSensor, Double> treeMapConverterSerializeRevert(){
        TreeMap<ComponentSensor, Double> result = new TreeMap<>();
        for (Map.Entry<ComponentSensorProxy, Double> currentEntry : children.entrySet()) {
            result.put((ComponentSensor) currentEntry.getKey().computeSensor(), currentEntry.getValue());
        }
        return result;
    }

    /**
     * Return the list of children
     * @return TreeMap of sensors and coefficients associated
     */
    public TreeMap<ComponentSensorProxy, Double> getChildren() {
        return children;
    }

    /**
     * Set the TreeMap of children
     * @param children Children of the component
     */
    public void setChildren(TreeMap<ComponentSensorProxy, Double> children) {
        this.children = children;
    }
}
