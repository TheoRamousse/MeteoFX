package model;

import java.util.Comparator;

public class
NameComparator implements Comparator<ComponentSensor> {
    @Override
    public int compare(ComponentSensor cs1, ComponentSensor cs2) {
        if (cs1.getSensorId() > cs2.getSensorId())
            return 1;
        else if (cs1.getSensorId() < cs2.getSensorId())
            return -1;
        else
            return 0;
    }
}
