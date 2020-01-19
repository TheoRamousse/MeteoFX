package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * WeatherManage contains a list of all the possible weathers. A weather can be added, deleted or searched with a name string.
 */

public class WeatherManager {

    private List<Weather> weatherList;
    private Persistence<Weather> persistence;

    /**
     * @param persistence is used to make a list of the persistence content
     */
    public WeatherManager(Persistence<Weather> persistence){
        this.persistence = persistence;
        weatherList = new ArrayList<>();
        try {
            weatherList = this.persistence.load();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<Weather> getWeatherList(){
        return weatherList;
    }

    public void setWeatherList(List<Weather> weathers){
        weatherList = weathers;
    }

    /**
     * Made to add a weather to the list
     * @param w is the weather meant to be added
     * @return a boolean whether it was successful or not
     */
    private boolean addWeather(Weather w)
    {
        if(findWeatherByName(w.getName()) == null)
            return false;
        weatherList.add(w);
        return true;
    }

    /**
     * Made to delete a weather to the list
     * @param name is the weather name meant to be deleted
     * @return a boolean whether it was successful or not
     */
    private boolean deleteWeather(String name)
    {
        Weather w = findWeatherByName(name);
        if(w == null)
            return false;
        weatherList.remove(w);
        return true;
    }

    /**
     * Looks for a Weather with a temperature
     * @param temperature is the temperature searched
     * @return the Weather object corresponding to that temperature.
     */
    public Weather findWeatherByTemperature(double temperature)
    {
        for (Weather w : weatherList) {
            if(w.isTemperatureInInterval(temperature))
                return w;
        }
        return null;
    }

    /**
     * Looks for a Weather with a name
     * @param name is the name searched
     * @return the Weather object corresponding to that name.
     */
    private Weather findWeatherByName(String name)
    {
        for (Weather w : weatherList) {
            if (w.getName().equals(name))
                return w;
        }
        return null;
    }
}
