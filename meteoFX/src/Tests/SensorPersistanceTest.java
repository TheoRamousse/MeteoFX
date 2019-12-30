package Tests;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SensorPersistanceTest {

    public static void main(String[] args) {
        List<ComponentSensor> l = new ArrayList<>();
        l.add(new Sensor(1, "First Sensor", new AlgoBoundedRandom(), 5));
        l.add(new Sensor(2, "Second Sensor", new AlgoSmallFluctuation(0.2, 200), 2));
        l.add(new Sensor(3, "Third Sensor", new AlgoRandom(), 1));
        MeanSensor ms1 = new MeanSensor(1, "ms 1");
        ms1.add(l.get(0), 3);
        ms1.add(l.get(1), 6);
        ms1.add(l.get(2), 2);
        l.add(ms1);


        SensorPersistance sp = new SensorPersistance();

        try {
            sp.save(l);
            System.out.println(((MeanSensor)l.get(3)).getChildren());
            l = sp.load();


            System.out.println(l.get(0).getSensorName());
            System.out.println(l.get(1).getSensorName());


            MeanSensor meanSensorSerialized = (MeanSensor)l.get(3);

            System.out.println(meanSensorSerialized.getChildren());
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Une erreur s'est produite dans la sauvegarde");
        }

    }
}
