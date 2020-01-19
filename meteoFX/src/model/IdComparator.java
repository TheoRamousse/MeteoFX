package model;

import java.util.Comparator;

/**
 * This class is meant to give a comparator to a TreeMap in order to class them by id value.
 */
public class
IdComparator implements Comparator<ComponentSensor> {

    @Override
    public int compare(ComponentSensor cs1, ComponentSensor cs2) {
        return Integer.compare(cs1.getSensorId(), cs2.getSensorId());
    }
}
