package model;

/* Sensor is an abstract class representing a sensor. A sensor has a temperature that it can generate.
*/

import javafx.application.Platform;
import javafx.beans.property.*;

import java.io.Serializable;

public class Sensor extends ComponentSensor implements Serializable {
    private SensorAlgoChanger sensorAlgoChanger;
    private IntegerProperty timeUpdate = new SimpleIntegerProperty();

    public Sensor(int id, String name, SensorAlgoChanger sac, int timeUpdate)
    {
        super(id, name);
        this.sensorAlgoChanger = sac;
        this.setTimeUpdate(timeUpdate);
        start();
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

    public int getTimeUpdate() {
        return timeUpdate.get();
    }

    public void setTimeUpdate(int timeUpdate) {
        this.timeUpdate.set(timeUpdate);
    }

    public IntegerProperty timeUpdateProperty(){ return timeUpdate; }

    public void doTemperature() {
        setCurrentTemperature(sensorAlgoChanger.doTemperature());
        try {
            notifyObserver();
        } catch (Exception e) {
        }
    }

    @Override
    public void run(){
        final int SECOND=1000;
        while (true){
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    doTemperature();
                }
            });
            try {
                sleep((long)getTimeUpdate()*SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public SensorAlgoChanger getSensorAlgoChanger()
    {
        return this.sensorAlgoChanger;
    }

    @Override
    public ComponentSensorProxy createProxy() {
        return new SensorProxy(this);
    }
}

