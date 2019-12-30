package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SensorPersistance implements Persistence<ComponentSensor> {
    @Override
    public List<ComponentSensor> load() throws IOException {
        FileInputStream file = new FileInputStream("sensor.txt");
        ObjectInputStream in = new ObjectInputStream(file);

        List<ComponentSensor> returnedList = new ArrayList<>();

        while(true){
            try{
                ComponentSensorProxy cspLoaded = (ComponentSensorProxy) in.readObject();
                returnedList.add(cspLoaded.computeSensor());
            } catch (EOFException | ClassNotFoundException e){return returnedList;}
        }
    }

    @Override
    public void save(List<ComponentSensor> list) throws IOException {
        FileOutputStream file = new FileOutputStream("sensor.txt");
        ObjectOutputStream out = new ObjectOutputStream(file);

        for (ComponentSensor cs : list) {
            out.writeObject(cs.createProxy());
        }

        System.out.println("Sensors sérializés");
    }
}
