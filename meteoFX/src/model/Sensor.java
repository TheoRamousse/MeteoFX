package model;

/* Sensor is an abstract class representing a sensor. A sensor has a temperature that it can generate.
*/

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Sensor extends Thread{
    private int id;
    private StringProperty name = new SimpleStringProperty();
    private double currentTemperature;
    private int timeUpdate;
    private final double min=-273.15;
    private final double max=100;

    public Sensor(int id)
    {
        this.id = id;
    }

    public int getSensorId() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSensorName(){ return name.get();}

    public double getCurrentTemperature(){
        return currentTemperature;
    }

    public int getTimeUpdate() {
        return timeUpdate;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setSensorName(String name) {
        this.name.set(name);
    }

    public void setCurrentTemperature(double temperature){
        currentTemperature = temperature;
    }

    public void setTimeUpdate(int timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    abstract public void doTemperature();

    @Override
    public void run(){
        while (true){
            doTemperature();
            try {
                sleep((long)timeUpdate*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
