package model;

import java.lang.invoke.MethodHandles;

public class AlgoSmallFluctuation extends SensorAlgoChanger {

    private double currentTemperature;
    private boolean isFirstTemperature;
    private double delta;


    public AlgoSmallFluctuation(double coef, double firstTemperature)
    {
        delta = coef;
        currentTemperature = firstTemperature;
        this.isFirstTemperature = true;
    }


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
        //System.out.println(previousTemperature);
        return currentTemperature;
    }

    static
    {
        notifyFatherIExist(AlgoSmallFluctuation.class);
    }

}

