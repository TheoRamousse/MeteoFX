package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.Sensor;
import model.SensorManager;
import model.WeatherManager;


public class CamView {
    private Sensor currentSensor;
    private WeatherManager wm;

    @FXML
    private ImageView weatherImageView;

    @FXML
    private BorderPane weatherImageContainer;

    public CamView(Sensor currentSensor, WeatherManager wm)
    {
        this.currentSensor = currentSensor;
        this.wm = wm;
    }

    @FXML
    public void initialize()
    {
        currentSensor.currentTemperatureProperty().addListener(e -> {
            weatherImageView.fitHeightProperty().bind((weatherImageContainer.heightProperty()));
            weatherImageView.fitWidthProperty().bind(weatherImageContainer.widthProperty());
            weatherImageView.imageProperty().setValue(new Image(wm.findWeatherByTemperature(currentSensor.getCurrentTemperature()).getPathImage()));
        });
    }
}
