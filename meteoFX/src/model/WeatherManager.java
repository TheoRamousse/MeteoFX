package model;

import java.util.HashSet;

public class WeatherManager {

    HashSet<Weather> weatherList = new HashSet<Weather>();

    public boolean addWeather(Weather w)
    {
        Weather w2 = findWeatherByName(w.getName());
        if(w2 != null)
            return false;
        return true;
    }

    public boolean deleteWeather(String name)
    {
        Weather w = findWeatherByName(name);

        if(w == null)
            return false;
        return true;
    }

    private Weather findWeatherByName(String name)
    {
        return null;
    }
}
