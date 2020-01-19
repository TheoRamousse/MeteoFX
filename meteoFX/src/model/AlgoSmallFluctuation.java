package model;


/**
 * This algorithm is used to generate random temperatures with a coefficient to reduce the difference between the
 * previous and the new temperature
 */
public class AlgoSmallFluctuation extends SensorAlgoChanger {

    private double currentTemperature;
    private boolean isFirstTemperature;
    private double delta;

    /**
     * load() is here to let know the other classes that this class exists
     */
    public static void load(){}

    /**
     *Constructor
     * @param coef Coefficient used to to reduce the difference between the previous and the new temperature
     * @param firstTemperature First temperature used by the algorithm
     */
    public AlgoSmallFluctuation(double coef, double firstTemperature)
    {
        delta = coef;
        currentTemperature = firstTemperature;
        this.isFirstTemperature = true;
    }

    /**
     * It's the algorithm solving the mathematical function "random decimal between the current value and +- the
     * coefficient" : coef - current <= rand <= coef + current
     * @return the result of this function
     */
    @Override
    public double doTemperature() {
        if (isFirstTemperature){
            isFirstTemperature = false;
            return currentTemperature;
        }
        currentTemperature = currentTemperature + (getRandom().nextDouble()*(delta + delta) - delta);
        if (currentTemperature >= getMax())
            return getMax();
        if (currentTemperature <= getMin())
            return getMin();
        return currentTemperature;
    }

    /**
     * static block used to notify the father of the existence of this class when the class is called.
     */
    static
    {
        notifyFatherIExist(AlgoSmallFluctuation.class);
    }

}

