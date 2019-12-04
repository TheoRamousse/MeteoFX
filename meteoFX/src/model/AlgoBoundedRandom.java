package model;

import java.util.Random;

public class AlgoBoundedRandom implements SensorAlgoChanger{

    //minimum temperature : -273.15 maximum here : 1000
    private final double min=-273.15;
    private final double max=1000;

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }


    public double doTemperature()
    {
        return Math.round((getMin()+(getMax()-getMin())*new Random().nextDouble())*100.0)/100.0;
    }
}
