package model;

import java.util.Random;

public class AlgoBoundedRandom extends SensorAlgoChanger{

    //minimum temperature : -273.15 maximum here : 1000
    private final double max=1000;
    private final static Random RANDOM = new Random();

    public AlgoBoundedRandom()
    {
        super.setAlgoType("Bounded Random");
    }

    public double getMax() {
        return max;
    }


    @Override
    public double doTemperature()
    {
        return super.getMIN()+(getMax()-super.getMIN())*RANDOM.nextDouble();
    }

}
