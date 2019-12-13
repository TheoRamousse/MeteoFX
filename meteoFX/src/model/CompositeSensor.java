package model;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeSensor extends ComponentSensor{

    private List<ComponentSensor> children = new ArrayList<ComponentSensor>();

    public CompositeSensor(int id, String name) {
        super(id, name);
    }

    public void add(ComponentSensor child){
        children.add(child);
    }

    public void remove(ComponentSensor child){
        children.remove(child);
    }
}
