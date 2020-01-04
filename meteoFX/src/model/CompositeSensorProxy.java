package model;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public abstract class CompositeSensorProxy extends ComponentSensorProxy{

    private TreeMap<ComponentSensorProxy, Double> children = new TreeMap<>();

    public CompositeSensorProxy(CompositeSensor cs){
        super(cs);
        this.children = treeMapConverterSerialize(cs.getChildren());
    }

    private TreeMap<ComponentSensorProxy, Double> treeMapConverterSerialize(TreeMap<ComponentSensor, Double> treeMapNotConverted){
        TreeMap<ComponentSensorProxy, Double> result = new TreeMap<>();
        for (Map.Entry<ComponentSensor, Double> currentEntry : treeMapNotConverted.entrySet()) {
            result.put((ComponentSensorProxy)currentEntry.getKey().createProxy(), currentEntry.getValue());
        }
        children = result;
        return result;
    }

    public TreeMap<ComponentSensor, Double> treeMapConverterSerializeRevert(){
        TreeMap<ComponentSensor, Double> result = new TreeMap<>();
        for (Map.Entry<ComponentSensorProxy, Double> currentEntry : children.entrySet()) {
            result.put((ComponentSensor) currentEntry.getKey().computeSensor(), currentEntry.getValue());
        }
        return result;
    }

    public TreeMap<ComponentSensorProxy, Double> getChildren() {
        return children;
    }

    public void setChildren(TreeMap<ComponentSensorProxy, Double> children) {
        this.children = children;
    }
}
