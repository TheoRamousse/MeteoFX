package controllers;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import launcher.Main;
import model.*;


import java.io.IOException;
import java.text.NumberFormat;

public class MainView {
    private SensorManager sm;
    private Sensor sensorSelected;
    @FXML
    private BorderPane displayPane;
    @FXML
    private BorderPane welcomePane;
    @FXML
    private ListView<Sensor> menuListeView;

    @FXML
    private Label sensorNum;

    @FXML
    private TextField temperatureInput;

    @FXML
    private TextField nameInput;

    @FXML
    private ComboBox comboBoxAlgos;

    public MainView(SensorManager sm) {

        this.sm = sm;
    }

    @FXML
    private ComboBox freqInput;

    @FXML
    public void initialize()
    {


        menuListeView.itemsProperty().bind(sm.sensorListProperty());
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

        comboBoxAlgos.getItems().addAll(
                SensorAlgoChanger.getSons()
        );

        for(int i=1; i<61; i++)
        {
            freqInput.getItems().add(i);
        }

        menuListeView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV)->{
            sensorSelected=newV;
            setDisplayVisible(true);
            if(oldV != null) {
                nameInput.textProperty().unbindBidirectional(oldV.nameProperty());
                freqInput.valueProperty().unbindBidirectional(oldV.timeUpdateProperty());
            }
            sensorNum.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).idProperty().asString());
            nameInput.textProperty().bindBidirectional(newV.nameProperty());
            freqInput.valueProperty().bindBidirectional(newV.timeUpdateProperty());
            comboBoxAlgos.getSelectionModel().select(newV.getAlgoType());
            temperatureInput.textProperty().bind(Bindings.format("%.2f",sm.findSensorById(sensorSelected.getSensorId()).currentTemperatureProperty()));

            //comboBoxAlgos.setValue(sensorSelected.getSensorAlgoType());
        });

//        comboBoxAlgos.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV)->{
//            sensorSelected.setSensorAlgoChanger(new newV());
//        });

        /*comboBoxAlgos.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                switch (t1) {
                    case "Random" :
                        sensorSelected.setSensorAlgoChanger(new AlgoRandom());
                        break;
                    case "Bounded Random" :
                        sensorSelected.setSensorAlgoChanger(new AlgoBoundedRandom());
                        break;
                    case "Small Fluctuation" :
                        sensorSelected.setSensorAlgoChanger(new AlgoSmallFluctuation(0.5,80));
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
        });*/

        if (menuListeView.getItems().size() != 0) {
            menuListeView.getSelectionModel().selectFirst();
            setDisplayVisible(true);
        }
    }

    public void setDisplayVisible(boolean show)
    {
        welcomePane.setVisible(!show);
        displayPane.setVisible(show);
    }

    public void hideWelcome()
    {

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


