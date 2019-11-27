package model;

/*Weather is a class made for representing 1 type of weather that corresponds to a range of temperatures.
This type of weather has a name and an image representing it.
The image isn't stocked in the object but the variable path is the relative path to the image*/

public class Weather {
    private String name;
    private float minTemperature;
    private float maxTemperature;
    private String pathImage;

    public Weather(String name, float minTemperature, float maxTemperature, String pathImage)
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

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
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

    public boolean isTemperatureInInterval(double temperature) {
        if(getMinTemperature()>= temperature && getMaxTemperature()<=temperature)
            return true;
        return false;
    }
}
