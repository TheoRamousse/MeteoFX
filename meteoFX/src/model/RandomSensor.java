package model;

public class RandomSensor extends Sensor{

    public RandomSensor(int id, String name, int updateTime)
    {
        super(id);
        super.setName(name);
        super.setUpdateTime(updateTime);
    }

    public void doTemperature()
    {

    }
}
