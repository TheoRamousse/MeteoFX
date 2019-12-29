package model;

import javafx.beans.property.*;

import java.awt.*;

public abstract class ComponentSensor extends Thread{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty currentTemperature = new SimpleDoubleProperty();
    private MeanSensor observer;

    public ComponentSensor(int id, String name){
        this.setId(id);
        this.setSensorName(name);
    }

    public ComponentSensor(int id, String name, MeanSensor observer){
        this.setId(id);
        this.setName(name);
        try {
            this.attachObserver(observer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachObserver(MeanSensor observer) throws Exception {
        if (this.observer == null)
            this.observer = observer;
        else
            throw new Exception("observer not null when attachObserver");
    }

    public void detachObserver(MeanSensor observer) throws Exception {
        if (this.observer != null)
            this.observer = null;
        else
            throw new Exception("observer null when detachObserver");
    }

    public void notifyObserver () throws Exception {
        if (this.observer != null)
            this.observer.doTemperature();
        else
            throw new Exception("obbserver is null can't notify");
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

    public MeanSensor getObserver() {
        return observer;
    }

    public void setObserver(MeanSensor observer) {
        this.observer = observer;
    }

/*    public abstract void add(ComponentSensor child);

    public abstract void remove(ComponentSensor child);*/


}
