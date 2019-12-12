package model;

import java.util.Random;

public class AlgoRandom extends SensorAlgoChanger{

    public AlgoRandom()
    {
        notifyFatherIExist();
    }

    @Override
    public double doTemperature()
    {
        return getRandom().nextDouble()*(Double.MAX_VALUE-getMin())+getMin();
    }


}
