package model;

public abstract class Sensor {
    private int id;
    private String name;
    private double currentTemperature;
    private int timeUpdate;

    public Sensor(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentTemperature(double temperature){
        currentTemperature = temperature;
    }

    public void setTimeUpdate(int timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    abstract public void doTemperature();
}
