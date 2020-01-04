package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import model.ComponentSensor;
import model.Sensor;
import model.WeatherManager;

public class DigitalView {
    private ComponentSensor currentSensor;

    @FXML
    private Text temperatureContainer;

    @FXML
    private Text nameContainer;

    public DigitalView(ComponentSensor currentSensor)
    {
        this.currentSensor = currentSensor;
    }

    @FXML
    public void initialize()
    {
        nameContainer.textProperty().bind(currentSensor.idProperty().asString());
        temperatureContainer.textProperty().bind(currentSensor.currentTemperatureProperty().asString());
    }
}
