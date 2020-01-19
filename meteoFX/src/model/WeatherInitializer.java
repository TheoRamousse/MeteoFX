package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A stub for the data corresponding to different weathers.
 */
public class WeatherInitializer implements Persistence<Weather> {

    @Override
    public List<Weather> load() {
        List<Weather> l = new ArrayList<>();
        l.add(new Weather("Lava", 50, Double.MAX_VALUE, "/img/lava.jpg"));
        l.add(new Weather("Good Weather", 20, 50, "/img/goodWeather.jpg"));
        l.add(new Weather("Cloudy", 4, 20, "/img/cloudy.jpeg"));
        l.add(new Weather("Snowy", -30, 4, "/img/snow.jpg"));
        l.add(new Weather("Frozen", -Double.MAX_VALUE, -30, "/img/frozen.jpg"));
        return l;
    }

    @Override
    public void save(List<Weather> list) {
    }
}
