package model;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public abstract class CompositeSensorProxy extends ComponentSensorProxy{

    private HashMap<ComponentSensorProxy, Double> children = new HashMap<>();

    public CompositeSensorProxy(CompositeSensor cs){
        super(cs);
        this.children = treeMapConverterSerialize(cs.getChildren());
    }

    private HashMap<ComponentSensorProxy, Double> treeMapConverterSerialize(TreeMap<ComponentSensor, Double> treeMapNotConverted){
        HashMap<ComponentSensorProxy, Double> result = new HashMap<>();
        for (Map.Entry<ComponentSensor, Double> currentEntry : treeMapNotConverted.entrySet()) {
            result.put((ComponentSensorProxy)currentEntry.getKey().createProxy(), currentEntry.getValue());
        }
        children = result;
        return result;
    }

    public TreeMap<ComponentSensor, Double> treeMapConverterSerializeRevert(){
        TreeMap<ComponentSensor, Double> result = new TreeMap<>(new NameComparator());
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
