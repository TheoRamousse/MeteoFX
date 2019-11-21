package model;

import java.util.Random;

public class RandomSensor extends Sensor{

    public RandomSensor(int id, String name, int timeUpdate)
    {
        super(id);
        super.setName(name);
        super.setTimeUpdate(timeUpdate);
    }

    public void doTemperature()
    {
        //minimum temperature : -273.15 maximum here : 4000
        double max = 100;
        double min = -273.15;
        super.setCurrentTemperature(min+(max-min)*new Random().nextDouble());
    }
}
