package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.ComponentSensor;
import model.WeatherManager;

/**
 * This class has the responsibility to manage the information displayed and the interactions made with the view
 * displaying an image of the temperature.
 */
public class CamView {
    private ComponentSensor currentSensor;
    private WeatherManager wm;

    @FXML
    private ImageView weatherImageView;

    @FXML
    private BorderPane weatherImageContainer;

    /**
     * @param wm is knowing all the weathers and images linked, will be useful for showing the right image.
     */
    public CamView(ComponentSensor currentSensor, WeatherManager wm)
    {
        this.currentSensor = currentSensor;
        this.wm = wm;
    }

    @FXML
    public void initialize()
    {
        initializeImageView();
    }

    private void initializeImageView()
    {
        currentSensor.currentTemperatureProperty().addListener(e -> {
            weatherImageView.resize(weatherImageContainer.getWidth(), weatherImageContainer.getHeight());
            weatherImageView.imageProperty().setValue(new Image(wm.findWeatherByTemperature(currentSensor.getCurrentTemperature()).getPathImage()));
        });
    }


}
