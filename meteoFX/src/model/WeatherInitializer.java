package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WeatherInitializer implements Persistence<Weather> {

    @Override
    public List<Weather> load() {
        List<Weather> l = new ArrayList<>();
        l.add(new Weather("Lava", 51, 4000, "/img/lava.jpg"));
        l.add(new Weather("Good Weather", 20, 50, "/img/goodWeather.jpg"));
        l.add(new Weather("Cloudy", 4, 19, "/img/cloudy.jpg"));
        l.add(new Weather("Snowy", -30, 3, "/img/snow.jpg"));
        l.add(new Weather("Frozen", -274, -31, "/img/frozen.jpg"));
        return l;
    }

    @Override
    public void save(List<Weather> list) {
        return;
    }
}
