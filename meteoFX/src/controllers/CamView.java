package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.Sensor;
import model.WeatherManager;


public class CamView {
    private Sensor currentSensor;
    private WeatherManager wm;

    @FXML
    private BorderPane weatherImageContainer;
    @FXML
    private ImageView weatherImageView;

    public CamView(Sensor currentSensor, WeatherManager wm)
    {
        this.currentSensor = currentSensor;
        this.wm = wm;
    }

    @FXML
    public void initialize()
    {
        currentSensor.currentTemperatureProperty().addListener(e -> {
            weatherImageView.setFitWidth(weatherImageContainer.getWidth());
            weatherImageView.setFitHeight(weatherImageContainer.getHeight());
            weatherImageView.imageProperty().setValue(new Image(wm.findWeatherByTemperature(currentSensor.getCurrentTemperature()).getPathImage()));

        });
    }
}
