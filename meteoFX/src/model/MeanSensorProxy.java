package model;

public class MeanSensorProxy extends CompositeSensorProxy{

    public MeanSensorProxy(MeanSensor meanSensor) {
        super(meanSensor);

    }

    @Override
    public ComponentSensor computeSensor() {
        MeanSensor result =  new MeanSensor(super.getSensorId(), super.getSensorName());
        result.setCurrentTemperature(super.getCurrentTemperature());
        result.setChildren(super.treeMapConverterSerializeRevert());
        return result;
    }
}
