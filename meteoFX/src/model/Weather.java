package model;

/**
 * Weather is a class made for representing one type of weather that corresponds to a range of temperatures.
 * This type of weather has a name and an image representing it.
 * The image isn't stocked in the object but the variable path is the relative path to the image
 */
public class Weather {
    private String name;
    private double minTemperature;
    private double maxTemperature;
    private String pathImage;

    /**
     * Constructor
     * @param name is the name of the weather
     * @param minTemperature is the minimum temperature that the sensor can take. Be careful that it is not equal with
     *                       an other maxTemperature
     * @param maxTemperature is the minimum temperature that the sensor can take. Be careful that it is not equal with
     *      *                       an other minTemperature
     * @param pathImage is the relative path to the image corresponding to that weather
     */
    public Weather(String name, double minTemperature, double maxTemperature, String pathImage)
    {
        this.name = name;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.pathImage = pathImage;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method will look if a temperature is corresponding to that weather
     * @param temperature is the temperature to looked at
     * @return a boolean whether the temperature is comprised in the range.
     */
    public boolean isTemperatureInInterval(double temperature) {
        return getMinTemperature() <= temperature && getMaxTemperature() >= temperature;
    }
}
