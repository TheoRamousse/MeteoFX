package model;

import java.util.Random;

public class AlgoRandom extends SensorAlgoChanger{
    private final static Random RANDOM = new Random();

    public AlgoRandom()
    {
        super.setAlgoType("Random");
    }

    @Override
    public double doTemperature()
    {
        return (super.getMIN()+(Double.MAX_VALUE)* RANDOM.nextDouble());
    }

}
