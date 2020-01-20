package model;

import javafx.beans.property.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract type that can represent different types of sensors in the composite pattern.
 */
public abstract class ComponentSensor extends Thread implements ProxyCreator{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty currentTemperature = new SimpleDoubleProperty();
    private ArrayList<MeanSensor> listObserver = new ArrayList<>();

    /**
     * Constructor
     * @param id Identifier of the sensor
     * @param name Name of the sensor
     */
    public ComponentSensor(int id, String name){
        this.setId(id);
        this.setSensorName(name);
    }

    /**
     * Update the list of observers when loading of saved sensors
     * @param m MeanSensor which observe this sensor
     */
    public void updateListOfObservers(ComponentSensor m){
        listObserver.add((MeanSensor) m);
    }

    /**
     * Looks if the ComponentSensor is observed by a MeanSensor
     * @param m a MeanSensor that may observe this ComponentSensor
     * @return a boolean according if this ComponentSensor is observed by this MeanSensor
     */
    private boolean isObserverExists(MeanSensor m){
        return listObserver.contains(m);
    }

    /**
     * Adds a new observer
     * @param observer is the MeanSensor meant to observe this ComponentSensor.
     */
    public void addObserver(MeanSensor observer) throws Exception {
        if(!isObserverExists(observer))
            listObserver.add(observer);
        else
            throw new Exception("Sensor already observed by this MeanSensor");
    }

    /**
     * Deletes an observer
     * @param observer is the MeanSensor meant to stop observing this ComponentSensor
     */
    public void delObserver(MeanSensor observer) throws Exception {
        if(isObserverExists(observer))
            listObserver.remove(observer);
        else
            throw new Exception("Observer doesn't exists");
    }

    /**
     * This method is meant to call a method in the observer. It has to work like a trigger when the state of this has
     * changed.
     */
    public void notifyObserver(){
        for (MeanSensor m : listObserver) {
            m.doTemperature();
        }
    }

    /**
     * @return the id as a property
     */
    public IntegerProperty idProperty(){ return id;}

    /**
     * @return the id as its primitive type
     */
    public int getSensorId() {
        return id.get();
    }

    /**
     * @return the name as a property
     */
    public StringProperty nameProperty() {
        return name;
    }
    /**
     * @return the name as its primitive type
     */
    public String getSensorName(){ return name.get();}

    /**
     * @return the temperature as a property
     */
    public DoubleProperty currentTemperatureProperty() {
        return currentTemperature;
    }
    /**
     * @return the temperature as its primitive type
     */
    public double getCurrentTemperature(){
        return currentTemperature.get();
    }

    public void setId(int id){
        this.id.set(id);
    }

    public void setSensorName(String name) {
        this.name.set(name);
    }

    public void setCurrentTemperature(double temperature){
        currentTemperature.set(temperature);
    }

    /**
     * abstract method for generating the temperature
     */
    public abstract void doTemperature();

    public ArrayList<MeanSensor> getListObservers() {
        return listObserver;
    }

    public void setListObservers(ArrayList<MeanSensor> observer) {
        this.listObserver = observer;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
