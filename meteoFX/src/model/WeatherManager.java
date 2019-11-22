package model;

import java.util.HashSet;

/*WeatherManage contains a list of all the possible weathers. A weather can be added, deleted or searched with a name string.*/

public class WeatherManager {

    private HashSet<Weather> weatherList;

    public WeatherManager(){
        weatherList = new HashSet<>();
    }

    public HashSet<Weather> getWeatherList(){
        return weatherList;
    }

    public void setWeatherList(HashSet<Weather> weathers){
        weatherList = weathers;
    }

    public boolean addWeather(Weather w)
    {
        if(findWeatherByName(w.getName()) == null)
            return false;
        weatherList.add(w);
        return true;
    }

    public boolean deleteWeather(String name)
    {
        Weather w = findWeatherByName(name);
        if(w == null)
            return false;
        weatherList.remove(w);
        return true;
    }

    private Weather findWeatherByName(String name)
    {
        for (Weather w :
                weatherList) {
            if (w.getName().equals(name))
                return w;
        }
        return null;
    }
}
