package model;

import java.lang.invoke.MethodHandles;
import java.util.Random;

public class AlgoRandom extends SensorAlgoChanger{

    @Override
    public double doTemperature()
    {
        return getRandom().nextDouble()*(Double.MAX_VALUE-getMin())+getMin();
    }


    static
    {
        notifyFatherIExist(AlgoRandom.class);
    }


}
