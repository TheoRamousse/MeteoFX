package model;

import java.util.Random;

public class AlgoRandom implements SensorAlgoChanger{
    public double doTemperature()
    {
        return new Random().nextInt()+ new Random().nextDouble();
    }
}
