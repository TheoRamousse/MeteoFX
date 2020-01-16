package model;

import javafx.beans.property.*;

import java.awt.*;
import java.util.ArrayList;

public abstract class ComponentSensor extends Thread implements ProxyCreator{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty currentTemperature = new SimpleDoubleProperty();
    private ArrayList<MeanSensor> listObserver = new ArrayList<>();

    public ComponentSensor(int id, String name){
        this.setId(id);
        this.setSensorName(name);
    }

    private boolean isObserverExists(MeanSensor m){
        return listObserver.contains(m);
    }


    public void addObserver(MeanSensor observer) throws Exception {
        if(!isObserverExists(observer))
            listObserver.add(observer);
        else
            throw new Exception("Sensor already observed by this MeanSensor");
    }

    public void delObserver(MeanSensor observer) throws Exception {
        if(isObserverExists(observer))
            listObserver.remove(observer);
        else
            throw new Exception("Observer doesn't exists");
    }

    public void notifyObserver(){
        for (MeanSensor m : listObserver) {
            m.doTemperature();
        }
    }

    public IntegerProperty idProperty(){ return id;}

    public int getSensorId() {
        return id.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSensorName(){ return name.get();}

    public DoubleProperty currentTemperatureProperty() {
        return currentTemperature;
    }

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

    /*    public abstract void add(ComponentSensor child);

    public abstract void remove(ComponentSensor child);*/


}
