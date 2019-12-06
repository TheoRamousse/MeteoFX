package model;

public class AlgoSmallFluctuation extends SensorAlgoChanger {

    private double currentTemperature;
    private Double firstTemperature;
    private double delta;


    public AlgoSmallFluctuation(double coef, double firstTemperature)
    {
        delta = coef;
        super.setAlgoType("Small Fluctuation");
        currentTemperature = firstTemperature;
        this.firstTemperature = firstTemperature;
    }

    @Override
    public double doTemperature() {
        if (firstTemperature != null){
            firstTemperature = null;
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

}
