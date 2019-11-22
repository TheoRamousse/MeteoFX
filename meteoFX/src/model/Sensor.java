package model;

/* Sensor is an abstract class representing a sensor. A sensor has a temperature that it can generate.
*/

public abstract class Sensor extends Thread{
    private int id;
    private String name;
    private double currentTemperature;
    private int timeUpdate;

    public Sensor(int id)
    {
        this.id = id;
    }

    public int getSensorId() {
        return id;
    }

    public String getSensorName() {
        return name;
    }

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
        this.name = name;
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
}
