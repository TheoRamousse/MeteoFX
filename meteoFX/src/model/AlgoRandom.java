package model;

import java.util.Random;

public class AlgoRandom implements SensorAlgoChanger{
    private final String algoType = "Random";

    public double doTemperature()
    {
        return new Random().nextInt()+ new Random().nextDouble();
    }

    public String getAlgoType() {
        return algoType;
    }
}
