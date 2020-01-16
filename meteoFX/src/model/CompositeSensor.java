package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public abstract class CompositeSensor extends ComponentSensor{

    private TreeMap<ComponentSensor, Double> children = new TreeMap<>(new NameComparator());

    public CompositeSensor(int id, String name) {
        super(id, name);
    }


    public void add(ComponentSensor child, double coef) throws Exception {
        if (child == this)
            throw new Exception("Cannot add itself to itself");
        else
            children.put(child, coef);
    }

    public void remove(ComponentSensor child){
        children.remove(child);
    }

    public TreeMap<ComponentSensor, Double> getChildren() {
        return children;
    }

    public boolean childExists (ComponentSensor sensor){
        return children.get(sensor) != null;
    }

    public List<ComponentSensor> getListChildren() {
        Set<ComponentSensor> keySet = children.keySet();
        return new ArrayList<ComponentSensor>(keySet);
    }

    public int maxIdChildren(){
        int max = 0;
        Set<ComponentSensor> keySet = children.keySet();
        for (ComponentSensor s: keySet) {
            if (s.getClass().getSimpleName().equals("RootSensor") || s.getClass().getSimpleName().equals("MeanSensor")){
                int tmp = ((CompositeSensor)s).maxIdChildren();
                if (tmp >=  max)
                    max = tmp;
            }
            if (s.getSensorId() >= max)
                max = s.getSensorId();
        }
        return max;
    }

    public ListProperty<ComponentSensor> componentSensorListProperty(){
        ObservableList<ComponentSensor> oList = FXCollections.observableArrayList(getListChildren());
        return new SimpleListProperty<ComponentSensor>(oList);
    }

    public void setChildren(TreeMap<ComponentSensor, Double> children) {
        this.children = children;
    }
}
