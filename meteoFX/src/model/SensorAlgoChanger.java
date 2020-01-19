package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/***
 * This class factorises the behaviour of algorithms created
 * WARNING : Don't forget to call notifyFatherIExist() in a static block to display this algorithm in the mainView
 * comboBox if your class inherit of this class
 */
public abstract class SensorAlgoChanger implements Serializable {
    /**
     * List of algorithms created (algorithms inherited of this class)
     */
    private static ArrayList<Class> listSons = new ArrayList<>();

    /**
     * Minimum temperature simulated
     */
    private final double MIN =-273.15;

    /**
     * Maximum temperature simulated
     */
    private final double MAX = 4000;

    /**
     * Used to generate random temperatures
     */
    private final static Random RANDOM = new Random();

    /**
     * Generate a new temperature
     * @return New temperature
     */
    static{
        getAllAlgorithmsAvailable();
    }

    /**
     * This will be called in order for algorith classes to tell they exist
     */
    private static void getAllAlgorithmsAvailable(){
        AlgoBoundedRandom.load();
        AlgoSmallFluctuation.load();
        AlgoRandom.load();
    }

    /**
     * Abstract method to be completed with the algorithm of the daughter class
     * @return a double corresponding to the generated value
     */
    public abstract double doTemperature();

    /**
     * Get the minimum temperature
     * @return Minimum temperature
     */
    public double getMin()
    {
        return MIN;
    }

    /**
     * Get the maximum temperature
     * @return Maximum temperature
     */
    public double getMax(){
        return MAX;
    }

    /**
     * Get a random number
     * @return Random number
     */
    public Random getRandom(){
        return RANDOM;
    }

    /**
     * Add a new algorithm class to the list of sons
     * @param classAlgo Class of the new algorithm
     */
    private static void addSon(Class classAlgo)
    {
        listSons.add(classAlgo);
    }

    /**
     * Return the list of algorithms classes inherited of this class
     * @return List of algorithms
     */
    public static ArrayList<String> getSons(){
        ArrayList<String> listReturn = new ArrayList<>();
        for (Class c: listSons) {
            int start = c.getName().indexOf(".")+1;
            int end = c.getName().length();
            listReturn.add(c.getName().substring(start, end));
        }
        return listReturn;
    }

    /**
     * This method is called in the static block of classes inherited of this class. Used to notify this class that a new algorithm has been implemented
     * @param classAlgo New algorithm
     */
    public static void notifyFatherIExist(Class classAlgo)
    {
        addSon(classAlgo);
    }

}
