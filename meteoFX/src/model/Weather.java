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

    /**
     * Give the path of image showing the weather
     * @return path of image showing the weather
     */
    public String getPathImage() {
        return pathImage;
    }

    /**
     * Set path of image showing the weather
     * @param pathImage Path of image showing the weather
     */
    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    /**
     * Give maximum temperature to be in this weather
     * @return Maximum temperature to be in this weather
     */
    public double getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * Set the maximum temperature to be in this weather
     * @param maxTemperature Maximum temperature to be in this weather
     */
    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    /**
     * Give minimum temperature to be in this weather
     * @return Minimum temperature to be in this weather
     */
    public double getMinTemperature() {
        return minTemperature;
    }

    /**
     * Set the minimum temperature to be in this weather
     * @param minTemperature Minimum temperature to be in this weather
     */
    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    /**
     * Give the name of the weather
     * @return Name of the weather
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the weather
     * @param name Name of the weather
     */
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
