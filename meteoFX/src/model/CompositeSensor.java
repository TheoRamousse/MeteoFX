package model;

import java.util.*;

public abstract class CompositeSensor extends ComponentSensor{

    private TreeMap<ComponentSensor, Double> children = new TreeMap<>(new NameComparator());

    public CompositeSensor(int id, String name) {
        super(id, name);
    }


    public void add(ComponentSensor child, double coef){
        children.put(child, coef);
    }

    public void remove(ComponentSensor child){
        children.remove(child);
    }

    public TreeMap<ComponentSensor, Double> getChildren() {
        return children;
    }

    public List<ComponentSensor> getListChildren() {
        Set<ComponentSensor> keySet = children.keySet();
        return new ArrayList<ComponentSensor>(keySet);
    }

    public void setChildren(TreeMap<ComponentSensor, Double> children) {
        this.children = children;
    }
}
