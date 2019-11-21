package model;

public class Weather {
    private String name;
    private float minTemperature;
    private float maxTemperature;
    private String urlImage;

    public Weather(String name, float minTemperature, float maxTemperature, String urlImage)
    {
        this.name = name;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.urlImage = urlImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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
}
