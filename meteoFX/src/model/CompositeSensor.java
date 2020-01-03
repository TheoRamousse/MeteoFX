package model;

import java.util.*;

public abstract class CompositeSensor extends ComponentSensor{

    private HashMap<ComponentSensor, Double> children = new HashMap<>();

    public CompositeSensor(int id, String name) {
        super(id, name);
    }

    public CompositeSensor(int id, String name, MeanSensor observer){
        super(id, name, observer);
    }

    public void add(ComponentSensor child, double coef){
        children.put(child, coef);
    }

    public void remove(ComponentSensor child){
        children.remove(child);
    }

    public HashMap<ComponentSensor, Double> getChildren() {
        return children;
    }

    public void setChildren(HashMap<ComponentSensor, Double> children) {
        this.children = children;
    }
}
