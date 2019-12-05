package model;

public abstract class SensorAlgoChanger {

    private final double MIN =-273.15;
    private String algoType;

    public abstract double doTemperature();

    public double getMIN()
    {
        return MIN;
    }
    public String getAlgoType()
    {
        return algoType;
    }

    public void setAlgoType(String algoType)
    {
        this.algoType = algoType;
    }
}
