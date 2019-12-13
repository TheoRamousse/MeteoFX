package model;

/* Sensor is an abstract class representing a sensor. A sensor has a temperature that it can generate.
*/

import javafx.beans.property.*;

public class Sensor extends LeafSensor{
    private SensorAlgoChanger sensorAlgoChanger;
    private IntegerProperty timeUpdate = new SimpleIntegerProperty();

    public Sensor(int id, String name, SensorAlgoChanger sac, int timeUpdate)
    {
        super(id, name);
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

    public int getTimeUpdate() {
        return timeUpdate.get();
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
