package model;

public abstract class Sensor {
    private int id;
    private String name;
    private float currentTemperature;
    private int updateTime;

    public Sensor(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public int getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(int updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract void doTemperature();
}
