package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Sensor;
import model.SensorManager;
import model.WeatherManager;


public class CamView {
    private Sensor currentSensor;
    private WeatherManager wm;

    @FXML
    private Image weatherImage;

    @FXML
    private ImageView weatherImageContainer;

    public CamView(Sensor currentSensor, WeatherManager wm)
    {
        this.currentSensor = currentSensor;
        this.wm = wm;
    }

    @FXML
    public void initialize()
    {
        currentSensor.currentTemperatureProperty().addListener(e -> {
            weatherImageContainer.imageProperty().setValue(new Image(wm.findWeatherByTemperature(currentSensor.getCurrentTemperature()).getPathImage()));
        });
    }
}
