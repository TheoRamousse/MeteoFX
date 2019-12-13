package model;

import javafx.beans.property.*;

import java.awt.*;

public abstract class ComponentSensor extends Thread{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty currentTemperature = new SimpleDoubleProperty();

    public ComponentSensor(int id, String name){
        this.setId(id);
        this.setSensorName(name);
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

/*    public abstract void add(ComponentSensor child);

    public abstract void remove(ComponentSensor child);*/


}
