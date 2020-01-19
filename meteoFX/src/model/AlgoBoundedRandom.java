package model;

/**
 * This algorithm is used to generate random temperatures between the minimum and maximum temperature set. The minimum
 * temperature cannot go below -273.15 °C while the maximum temperature is set here to 4000 °C (cf. SensorAlgoChanger).
 */
public class AlgoBoundedRandom extends SensorAlgoChanger{

    public AlgoBoundedRandom()
    {
    }

    /**
     * load() is here to let know the other classes that this class exists
     */
    public static void load(){}

    /**
     * It's the algorithm solving the mathematical function "random decimal between a minimum and maximum" :
     * min + (max - min) * rand
     * @return the result of this function
     */
    @Override
    public double doTemperature()
    {
        return super.getMin()+(getMax()-super.getMin())*getRandom().nextDouble();
    }

    /**
     * static block used to notify the father of the existence of this class when the class is called.
     */
    static
    {
        notifyFatherIExist(AlgoBoundedRandom.class);
    }

}
