package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import launcher.Main;
import model.Sensor;
import model.SensorManager;
import model.WeatherInitializer;
import model.WeatherManager;


import java.io.IOException;

public class MainView {
    private SensorManager sm;
    private Sensor sensorSelected;

    @FXML
    private ListView<Sensor> menuListeView;

    @FXML
    private Label sensorNum;

    @FXML
    private TextField freqInput;

    @FXML
    private TextField temperatureInput;

    @FXML
    private TextField nameInput;

    public MainView(SensorManager sm) {

        this.sm = sm;
        sensorSelected= sm.getSensorList().get(0);
    }

    @FXML
    public void initialize()
    {
        sensorNum.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).idProperty().asString());
        menuListeView.itemsProperty().bind(sm.sensorListProperty());
        freqInput.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).timeUpdateProperty().asString());
        temperatureInput.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).currentTemperatureProperty().asString());
        nameInput.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).nameProperty());


        menuListeView.setCellFactory(__ ->
                new ListCell<Sensor>(){
                    @Override
                    protected void updateItem(Sensor item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            textProperty().bind(item.nameProperty());
                        } else {
                            textProperty().unbind();
                            setText("");
                        }
                    }

                }
        );
        menuListeView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            sensorSelected = menuListeView.getSelectionModel().getSelectedItem();
            sensorNum.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).idProperty().asString());
            menuListeView.itemsProperty().bind(sm.sensorListProperty());
            freqInput.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).timeUpdateProperty().asString());
            temperatureInput.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).currentTemperatureProperty().asString());
            nameInput.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).nameProperty());
        });


    }


    public void showCamView(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/camView.fxml"));
        CamView cv = new CamView(sensorSelected, new WeatherManager(new WeatherInitializer()));
        loader.setController(cv);
        primaryStage.setTitle("CAM : "+sensorSelected.getSensorName());
        primaryStage.setScene(new Scene(loader.load(), 1200, 800));
        primaryStage.show();
    }
}


