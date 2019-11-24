package model;

import java.util.ArrayList;

public class SensorManager {
    private static final int TIME_UPDATE_RANDOM_SENSOR =2;

    private ArrayList<Sensor> sensors = new ArrayList<>();

    public ArrayList<Sensor> getSensors(){
        return sensors;
    }

    public void addRandomSensors(int nbSensors){
        for (int i=0 ; i<nbSensors ; i++){
            int lastSensorId = 0;
            if (sensors != null)
                lastSensorId = sensors.size()-1;
            sensors.add(new RandomSensor(lastSensorId+1, "Random Sensor", TIME_UPDATE_RANDOM_SENSOR));
        }
    }
}
