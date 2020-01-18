package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/***
 * WARNING : If you create a new algorithm, don't forget to inform this class in the method getAllAlgorithmsAvailable. Each algorithm class needs to have these lines in the code :
 * public static void load(){}
 * static
 *     {
 *         notifyFatherIExist(NameOfYourClass.class);
 *     }
 * */

public abstract class SensorAlgoChanger implements Serializable {
    private static ArrayList<Class> listSons = new ArrayList<>();
    private final double MIN =-273.15;
    private final double MAX = 4000;
    private final static Random RANDOM = new Random();

    static{
        getAllAlgorithmsAvailable();
    }

    private static void getAllAlgorithmsAvailable(){
        AlgoBoundedRandom.load();
        AlgoSmallFluctuation.load();
        AlgoRandom.load();
    }

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


    public static void addSon(Class classAlgo)
    {
        listSons.add(classAlgo);
    }

    public static ArrayList<String> getSons(){
        ArrayList<String> listReturn = new ArrayList<>();
        for (Class c: listSons) {
            int start = c.getName().indexOf(".")+1;
            int end = c.getName().length();
            listReturn.add(c.getName().substring(start, end));
        }
        return listReturn;
    }

    public static void notifyFatherIExist(Class classAlgo)
    {
        addSon(classAlgo);
    }

}
