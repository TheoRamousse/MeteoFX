package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import launcher.Main;
import model.*;


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

    @FXML
    private ComboBox comboBoxAlgos;

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
        comboBoxAlgos.getItems().addAll(
                "Random",
                "Bounded Random"
        );
        comboBoxAlgos.setValue(sensorSelected.getSensorAlgoType());
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
            comboBoxAlgos.setValue(sensorSelected.getSensorAlgoType());
        });

        comboBoxAlgos.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                switch (t1) {
                    case "Random" :
                        sensorSelected.setSensorAlgoChanger(new AlgoRandom());
                        break;
                    case "Bounded Random" :
                        sensorSelected.setSensorAlgoChanger(new AlgoBoundedRandom());
                        break;
                    default:
                        try {
                            throw new Exception("No existant item selected");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        });


    }


    public void showCamView(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/camView.fxml"));
        CamView cv = new CamView(sensorSelected, new WeatherManager(new WeatherInitializer()));
        loader.setController(cv);
        primaryStage.setTitle("CAM : "+sensorSelected.getSensorName());
        primaryStage.setScene(new Scene(loader.load(), 800, 400));
        primaryStage.show();
    }

    public void showDigitalView(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/digitalView.fxml"));
        DigitalView cv = new DigitalView(sensorSelected);
        loader.setController(cv);
        primaryStage.setTitle("Digital : "+sensorSelected.getSensorName());
        primaryStage.setScene(new Scene(loader.load(), 800, 400));
        primaryStage.show();
    }
}


