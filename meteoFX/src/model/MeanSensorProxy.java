package model;

/**
 * This class creates a proxy of MeanSensor to allow the save of this class
 */
public class MeanSensorProxy extends CompositeSensorProxy{

    /**
     * Constructor of this class
     * @param meanSensor MeanSensor to save
     */
    public MeanSensorProxy(MeanSensor meanSensor) {
        super(meanSensor);

    }

    /**
     * Convert this MeanSensorProxy into a MeanSensor (ComponentSensor)
     * @return MeanSensor created (Returned into a ComponentSensor)
     */
    @Override
    public ComponentSensor computeSensor() {
        MeanSensor result =  new MeanSensor(super.getSensorId(), super.getSensorName());
        result.setCurrentTemperature(super.getCurrentTemperature());
        result.setChildren(super.treeMapConverterSerializeRevert());
        return result;
    }
}
