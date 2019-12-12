package model;

/* Sensor is an abstract class representing a sensor. A sensor has a temperature that it can generate.
*/

import javafx.beans.property.*;

public class Sensor extends Thread{
    private SensorAlgoChanger sensorAlgoChanger;
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty currentTemperature = new SimpleDoubleProperty();
    private IntegerProperty timeUpdate = new SimpleIntegerProperty();


    public IntegerProperty idProperty(){ return id;}

    public Sensor(int id, String name, SensorAlgoChanger sac, int timeUpdate)
    {
        this.setId(id);
        this.setSensorName(name);
        this.sensorAlgoChanger = sac;
        this.setTimeUpdate(timeUpdate);
    }

    public String getAlgoType()
    {
        int start = this.getClass().getName().indexOf(".")+1;
        return sensorAlgoChanger.getClass().getName().substring(start);
    }

    public void setSensorAlgoChanger(SensorAlgoChanger sac)
    {
        this.sensorAlgoChanger = sac;
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

    public void doTemperature() {
        setCurrentTemperature(sensorAlgoChanger.doTemperature());
    }

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
}
