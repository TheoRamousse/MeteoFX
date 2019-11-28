package model;

/* Sensor is an abstract class representing a sensor. A sensor has a temperature that it can generate.
*/

import javafx.beans.property.*;

public abstract class Sensor extends Thread{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty currentTemperature = new SimpleDoubleProperty();
    private IntegerProperty timeUpdate = new SimpleIntegerProperty();
    private final double min=-273.15;
    private final double max=100;
    public IntegerProperty idProperty(){ return id;}

    public Sensor(int id)
    {
        this.id.set(id);
    }

    public int getSensorId() {
        return id.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getSensorName(){ return name.get();}

    public double getCurrentTemperature(){
        return currentTemperature.get();
    }

    public int getTimeUpdate() {
        return timeUpdate.get();
    }

    public void setId(int id){
        this.id.set(id);
    }

    public void setSensorName(String name) {
        this.name.set(name);
    }

    public DoubleProperty currentTemperatureProperty() {
        return currentTemperature;
    }

    public void setCurrentTemperature(double temperature){
        currentTemperature.set(temperature);
    }

    public void setTimeUpdate(int timeUpdate) {
        this.timeUpdate.set(timeUpdate);
    }

    public IntegerProperty timeUpdateProperty(){ return timeUpdate; }

    abstract public void doTemperature();

    @Override
    public void run(){
        while (true){
            doTemperature();
            try {
                sleep((long)getTimeUpdate()*1000);
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
