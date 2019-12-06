package model;

import java.util.Random;

public abstract class SensorAlgoChanger {

    private final double MIN =-273.15;
    private final double MAX = 4000;
    private final static Random RANDOM = new Random();

    private String algoType;

    public abstract double doTemperature();

    public double getMin()
    {
        return MIN;
    }
    public double getMax(){
        return MAX;
    }
    public String getAlgoType()
    {
        return algoType;
    }
    public Random getRandom(){
        return RANDOM;
    }

    public void setAlgoType(String algoType)
    {
        this.algoType = algoType;
    }
}
