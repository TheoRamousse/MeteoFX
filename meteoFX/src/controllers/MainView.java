package controllers;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import launcher.Main;
import model.*;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.text.NumberFormat;
import java.util.ArrayList;

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
    private VBox algoContainer;

    @FXML
    private Label sensorNum;

    @FXML
    private TextField temperatureInput;

    @FXML
    private TextField nameInput;

    @FXML
    private ComboBox<String> comboBoxAlgos;

    public MainView(SensorManager sm) {

        this.sm = sm;
    }

    @FXML
    private ComboBox<Integer> freqInput;

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
            Property<Integer> bindedValue= newV.timeUpdateProperty().asObject();
            setDisplayVisible(true);
            if(oldV != null) {
                nameInput.textProperty().unbindBidirectional(oldV.nameProperty());
                freqInput.valueProperty().unbindBidirectional(bindedValue);
            }
            sensorNum.textProperty().bind(sm.findSensorById(sensorSelected.getSensorId()).idProperty().asString());
            nameInput.textProperty().bindBidirectional(newV.nameProperty());
            freqInput.valueProperty().bindBidirectional(newV.timeUpdateProperty().asObject());
            comboBoxAlgos.getSelectionModel().select(newV.getAlgoType());
            temperatureInput.textProperty().bind(Bindings.format("%.2f",sm.findSensorById(sensorSelected.getSensorId()).currentTemperatureProperty()));

        });

        comboBoxAlgos.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                try {
                    Constructor<?>[] algoSelected=Class.forName("model."+t1).getConstructors();
                    for(Constructor<?> c : algoSelected) {
                        if (c.getParameterTypes().length != 0) {
                            String pathOfView = t1.replaceFirst(".", ("res/fxml/" + t1.charAt(0) + "").toLowerCase()) + "View.fxml";
                            FXMLLoader fxmlLoader = new FXMLLoader(new File(pathOfView).toURI().toURL());
                            algoContainer.getChildren().add(fxmlLoader.load());

                            ArrayList<Object> listParameters = new ArrayList<>();
                            int i = 0;
                            for(Class<?> currentClass : c.getParameterTypes())
                            {
                                TextField curentTextField =(TextField) algoContainer.lookup("paramContainer").lookup("arg"+i);
                                switch(currentClass.getName()) {
                                    case "Double":
                                        Double currentNodeD = Double.valueOf(curentTextField.getText());
                                        break;
                                    case "Integer":
                                        Integer currentNodeI = Integer.valueOf(curentTextField.getText());
                                        break;
                                    default:
                                        String currentNodeS = curentTextField.getText();
                                        break;
                                }

                            }
                            i++;
                            return;
                        }
                    }
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                if(algoContainer.getChildren().toArray().length != 0)
                    algoContainer.getChildren().remove(0);
                //sensorSelected.setSensorAlgoChanger((SensorAlgoChanger) Class.forName("model."+t1).newInstance());
            }
        });

        if (menuListeView.getItems().size() != 0) {
            menuListeView.getSelectionModel().selectFirst();
            setDisplayVisible(true);
        }
    }

    private void setDisplayVisible(boolean show)
    {
        welcomePane.setVisible(!show);
        displayPane.setVisible(show);
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


