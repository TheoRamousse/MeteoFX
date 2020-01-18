package model;

import java.lang.invoke.MethodHandles;
import java.util.Random;

/**
 * This algorithm is used to generate random temperatures between a min border and a max border
 */
public class AlgoBoundedRandom extends SensorAlgoChanger{

    //minimum temperature : -273.15 maximum here : 1000

    public AlgoBoundedRandom()
    {
    }

    public static void load(){}

    @Override
    public double doTemperature()
    {
        return super.getMin()+(getMax()-super.getMin())*getRandom().nextDouble();
    }

    static
    {
        notifyFatherIExist(AlgoBoundedRandom.class);
    }

}
