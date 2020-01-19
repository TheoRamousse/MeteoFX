package model;

import javafx.application.Platform;
import javafx.beans.property.*;

import java.io.Serializable;

/**
 * Sensor extends ComponentSensor, it is a leaf of the composite pattern. It is here to generate temperature values
 * according to an algorithm passed through a Strategy pattern
 */
public class Sensor extends ComponentSensor implements Serializable {
    /**
     * Algorithm used to generate temperatures
     */
    private SensorAlgoChanger sensorAlgoChanger;

    /**
     * Frequency of update
     */
    private IntegerProperty timeUpdate = new SimpleIntegerProperty();


    /**
     * Constructor of this class
     * @param id Id of the sensor
     * @param name Name of the sensor
     * @param sac Algorithm used by this sensor
     * @param timeUpdate Frequency of update (seconds)
     */
    public Sensor(int id, String name, SensorAlgoChanger sac, int timeUpdate)
    {
        super(id, name);
        this.sensorAlgoChanger = sac;
        this.setTimeUpdate(timeUpdate);
        start();
    }

    /**
     * Get the name of the algorithm used
     * @return Name of algorithm
     */
    public String getAlgoType()
    {
        int start = this.getClass().getName().indexOf(".")+1;
        return sensorAlgoChanger.getClass().getName().substring(start);
    }

    /**
     * Set the algorithm of the sensor
     * @param sac New algorithm
     */
    public void setSensorAlgoChanger(SensorAlgoChanger sac)
    {
        this.sensorAlgoChanger = sac;
    }

    /**
     * Get the frequency of update
     * @return Frequency of update (seconds)
     */
    public int getTimeUpdate() {
        return timeUpdate.get();
    }

    /**
     * Set the frequency of update
     * @param timeUpdate New frequency of update (seconds)
     */
    public void setTimeUpdate(int timeUpdate) {
        this.timeUpdate.set(timeUpdate);
    }

    /**
     * Get the frequency of update (property format)
     * @return The frequency of update (property format)
     */
    public IntegerProperty timeUpdateProperty(){ return timeUpdate; }

    /**
     * Generate a new temperature and notifies the observer of its change
     */
    public void doTemperature() {
        setCurrentTemperature(sensorAlgoChanger.doTemperature());
        try {
            notifyObserver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Start a thread that it will call doTemperature method every "timeUpdate" seconds
     */
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

    /**
     * Get the algorithm used by the sensor
     * @return Algorithm used by the sensor
     */
    public SensorAlgoChanger getSensorAlgoChanger()
    {
        return this.sensorAlgoChanger;
    }

    /**
     * Create a proxy of itself (a savable version)
     * @return Proxy of the sensor (a savable version)
     */
    @Override
    public ComponentSensorProxy createProxy() {
        return new SensorProxy(this);
    }
}
