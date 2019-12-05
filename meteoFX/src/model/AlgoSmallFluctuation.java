package model;

import java.util.Random;

public class AlgoSmallFluctuation extends SensorAlgoChanger {

    private double previousTemperature;
    private final static Random RANDOM = new Random();
    private double delta;


    public AlgoSmallFluctuation(double coef, double firstTemperature)
    {
        delta = coef;
        super.setAlgoType("AlgoFluctuation");
        this.previousTemperature = firstTemperature;
    }

    @Override
    public double doTemperature() {
        this.previousTemperature = previousTemperature + ((2*delta*RANDOM.nextDouble())-delta);
        System.out.println(previousTemperature);
        return previousTemperature;
    }

}
