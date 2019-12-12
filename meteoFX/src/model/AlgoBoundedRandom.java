package model;

import java.util.Random;

public class AlgoBoundedRandom extends SensorAlgoChanger{

    //minimum temperature : -273.15 maximum here : 1000

    public AlgoBoundedRandom()
    {
        notifyFatherIExist();
    }

    @Override
    public double doTemperature()
    {
        return super.getMin()+(getMax()-super.getMin())*getRandom().nextDouble();
    }

}
