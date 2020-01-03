package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SensorPersistance<ComponentSensor> implements Persistence<ComponentSensor> {
    private String fileName;
    public SensorPersistance(String fileName){
        this.fileName = fileName;
    }

    @Override
    public List<ComponentSensor> load() throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(file);

        List<ComponentSensor> returnedList = new ArrayList<>();

        while(true){
            try{
                ComponentSensorProxy cspLoaded = (ComponentSensorProxy) in.readObject();
                returnedList.add((ComponentSensor) cspLoaded.computeSensor());
            } catch (EOFException | ClassNotFoundException e){return returnedList;}
        }
    }

    @Override
    public void save(List<ComponentSensor> list) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(file);

        for (ComponentSensor cs : list) {
            out.writeObject(((model.ComponentSensor)cs).createProxy());
        }

        System.out.println("Sensors sérializés");
    }
}
