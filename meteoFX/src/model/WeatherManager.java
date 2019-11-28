package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*WeatherManage contains a list of all the possible weathers. A weather can be added, deleted or searched with a name string.*/

public class WeatherManager {

    private List<Weather> weatherList;
    private Persistence<Weather> persistence;

    public WeatherManager(Persistence<Weather> persistence){
        this.persistence = persistence;
        weatherList = new ArrayList<>();
        weatherList = this.persistence.load();
    }

    public List<Weather> getWeatherList(){
        return weatherList;
    }

    public void setWeatherList(List<Weather> weathers){
        weatherList = weathers;
    }

    private boolean addWeather(Weather w)
    {
        if(findWeatherByName(w.getName()) == null)
            return false;
        weatherList.add(w);
        return true;
    }

    private boolean deleteWeather(String name)
    {
        Weather w = findWeatherByName(name);
        if(w == null)
            return false;
        weatherList.remove(w);
        return true;
    }

    public Weather findWeatherByTemperature(double temperature)
    {
        for (Weather w : weatherList) {
            if(w.isTemperatureInInterval(temperature))
                return w;
        }
        return null;
    }

    private Weather findWeatherByName(String name)
    {
        for (Weather w : weatherList) {
            if (w.getName().equals(name))
                return w;
        }
        return null;
    }
}
