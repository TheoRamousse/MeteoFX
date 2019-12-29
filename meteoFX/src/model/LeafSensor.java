package model;

public abstract class LeafSensor extends ComponentSensor {

    public LeafSensor(int id, String name) {super(id, name);}

    public LeafSensor(int id, String name, MeanSensor observer) {super(id, name, observer);}


}
