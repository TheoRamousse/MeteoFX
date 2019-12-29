package model;

import java.util.ArrayList;
import java.util.Random;

/***
 * WARNING : Don't forget to call notifyFatherIExist() in a static block to display this algo in the mainView comboBox
 */

public abstract class SensorAlgoChanger {
    private static ArrayList<Class> listSons = new ArrayList<>();
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
