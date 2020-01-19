package model;

/**
 * This algorithm is used to generate random temperatures
 */
public class AlgoRandom extends SensorAlgoChanger{

    /**
     * It's the algorithm solving the mathematical function "random decimal between the minimum and infinity" :
     * min + (\infty - min) * rand
     * @return the result of this function
     */
    @Override
    public double doTemperature()
    {
        return getRandom().nextDouble()*(Double.MAX_VALUE-getMin())+getMin();
    }

    /**
     * load() is here to let know the other classes that this class exists
     */
    public static void load(){}

    /**
     * static block used to notify the father of the existence of this class when the class is called.
     */
    static
    {
        notifyFatherIExist(AlgoRandom.class);
    }


}
