package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.*;

/**
 * This class inherits of the ComponentSensor and is in charge of being composed of ComponentSensors in the composite
 * pattern.
 */
public abstract class CompositeSensor extends ComponentSensor{

    private TreeMap<ComponentSensor, Double> children = new TreeMap<>(new IdComparator());

    public CompositeSensor(int id, String name) {
        super(id, name);
    }

    /**
     * This method is meant to add a component child to the composite.
     * @param child is a ComponentSensor meant to be a child
     * @param coef is the coefficient to know how important is the component compared to the others
     */
    public void add(ComponentSensor child, double coef) throws Exception {
        if (child == this)
            throw new Exception("Cannot add itself to itself");
        else
            children.put(child, coef);
    }

    /**
     * This method notify observed elements that this sensor observe them
     */
    public void notifyChildren(){
        for (Map.Entry<ComponentSensor, Double> entry: children.entrySet()) {
            entry.getKey().updateListOfObservers(this);
        }
    }

    /**
     * This method is meant to remove a child of the list.
     * @param child is the sensor to be removed
     */
    public void remove(ComponentSensor child){
        children.remove(child);
    }

    public TreeMap<ComponentSensor, Double> getChildren() {
        return children;
    }

    /**
     * @param sensor looks if it is a child
     * @return a boolean whether the sensor is in the list or not
     */
    public boolean childExists (ComponentSensor sensor){
        return children.get(sensor) != null;
    }

    public List<ComponentSensor> getListChildren() {
        Set<ComponentSensor> keySet = children.keySet();
        return new ArrayList<ComponentSensor>(keySet);
    }

    /**
     * @return the biggest id among all children
     */
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
