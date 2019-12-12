package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class SensorAlgoChanger {
    private static ArrayList<String> listSons = new ArrayList<String>();
    private final double MIN =-273.15;
    private final double MAX = 4000;
    private final static Random RANDOM = new Random();

    public abstract double doTemperature();

    public double getMin()
    {
        return MIN;
    }
    public double getMax(){
        return MAX;
    }
    public Random getRandom(){
        return RANDOM;
    }


    public static void addSon(String className)
    {
        listSons.add(className);
    }

    public static ArrayList<String> getSons()
    {
        return listSons;
    }

    public void notifyFatherIExist() {
        int start = this.getClass().getName().indexOf(".")+1;
        addSon(this.getClass().getName().substring(start));
    }

}
