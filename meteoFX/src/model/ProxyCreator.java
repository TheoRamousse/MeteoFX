package model;

/**
 * This interface describes the methods implemented by ProxyCreator sub-classes. These classes will be able to be persisted
 */
public interface ProxyCreator {
    public ComponentSensorProxy createProxy();
}
