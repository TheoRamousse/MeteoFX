package model;

import java.util.List;

public class SensorInitializer implements Persistence<Sensor> {

    private String nomDuFichier;

    public SensorInitializer(String nomDuFichier)
    {
        this.nomDuFichier = nomDuFichier;
    }

    @Override
    public List<Sensor> load() {
        return null;
    }

    @Override
    public void save(List<Sensor> list) {

    }
}
