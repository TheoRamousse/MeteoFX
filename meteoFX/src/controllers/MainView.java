package controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MainView {
    private ComponentSensorManager sm;
    /**
     * This attribute manage has the list of sensors and manage them
     */
    private ComponentSensor sensorSelected;
    /**
     * Current sensor selected by the user
     */



    @FXML
    private BorderPane displayPane;
    @FXML
    private BorderPane welcomePane;
    @FXML
    private ListView<ComponentSensor> menuListeView;


    @FXML
    private VBox algoContainer;

    @FXML
    private Label sensorNum;

    @FXML
    private TextField temperatureInput;

    @FXML
    private TextField nameInput;

    @FXML
    private HBox hBoxAlgo;

    @FXML
    private ComboBox<String> comboBoxAlgos;

    @FXML
    private HBox hBoxChildren;

    @FXML
    private HBox hBoxFreq;

    @FXML
    private ComboBox freqInput;

    public MainView(ComponentSensorManager sm) {

        this.sm = sm;
    }

    /**
     * Constructor of MainView : Take the sensor manager in parameter
     */

    @FXML
    public void initialize()
    {


        menuListeView.itemsProperty().bind(sm.componentSensorListProperty());
        menuListeView.setCellFactory(__ ->
                new ListCell<ComponentSensor>(){
                    @Override
                    protected void updateItem(ComponentSensor item, boolean empty) {
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

        /**
         * Display sensors in the master
         */

        comboBoxAlgos.getItems().addAll(
                SensorAlgoChanger.getSons()
        );
        /**
         * Add the name of all sensors known by the class SensorAlgoChanger
         */




        for (int i = 1; i < 61; i++) {
            freqInput.getItems().add(i);
        }
        /**
         * Set values of freqInput (number of seconds to update the sensor : from 1s to 60s)
         */

        menuListeView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV)->{
            try {
                sensorSelected = newV;
                setDisplayVisible(true);
                if (oldV != null) {
                    nameInput.textProperty().unbindBidirectional(oldV.nameProperty());
                    if(!sensorSelected.getClass().getSimpleName().equals("MeanSensor")) {
                        freqInput.valueProperty().unbindBidirectional(((Sensor)oldV).timeUpdateProperty());
                    }
                }
                sensorNum.textProperty().bind(sm.findComponentSensorById(sensorSelected.getSensorId()).idProperty().asString());
                nameInput.textProperty().bindBidirectional(newV.nameProperty());
                if(!sensorSelected.getClass().getSimpleName().equals("MeanSensor")) {
                    hBoxFreq.setVisible(true);
                    hBoxAlgo.setVisible(true);
                    freqInput.valueProperty().bindBidirectional(((Sensor)newV).timeUpdateProperty());
                    comboBoxAlgos.getSelectionModel().select(((Sensor)newV).getAlgoType());
                }
                if (sensorSelected.getClass().getSimpleName().equals("MeanSensor"))
                    hBoxChildren.setVisible(true);
                temperatureInput.textProperty().bind(Bindings.format("%.2f", sm.findComponentSensorById(sensorSelected.getSensorId()).currentTemperatureProperty()));
            }
            catch(Exception e){}
        });
        /**
         * Change values of the detail when a new sensor is selected in the master (menuListView)
         */

        comboBoxAlgos.valueProperty().addListener(new ChangeListener<String>() {
            private Constructor<?> constructorOfAlgo;

            /**
             *Constructor of algorithm selected
             */

            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                try {
                    Constructor<?>[] constructorsOfAlgoSelected=Class.forName("model."+t1).getConstructors();
                    for(Constructor<?> c : constructorsOfAlgoSelected) {
                        if (c.getParameterTypes().length != 0) {
                            constructorOfAlgo = c;
                            try {
                                String pathOfView = t1.replaceFirst(".", ("res/fxml/" + t1.charAt(0) + "").toLowerCase()) + "View.fxml";
                                FXMLLoader fxmlLoader = new FXMLLoader(new File(pathOfView).toURI().toURL());
                                algoContainer.getChildren().add(fxmlLoader.load());
                            }catch(Exception ex){
                                throw new Exception("Your algo needs a fxml view");
                            }

                            try {
                                Button validate = (Button) algoContainer.lookup("#paramContainer").lookup("#submit");
                                validate.setOnAction((event) -> {
                                    ArrayList<Object> listParameters = new ArrayList<>();
                                    int i = 0;
                                    for(Class<?> currentClass : constructorOfAlgo.getParameterTypes())
                                    {
                                        TextField curentTextField =(TextField) algoContainer.lookup("#paramContainer").lookup("#arg"+i);
                                        switch(currentClass.getName()) {
                                            case "double":
                                                Double currentNodeD = Double.valueOf(curentTextField.getText());
                                                listParameters.add(currentNodeD);
                                                break;
                                            case "int":
                                                Integer currentNodeI = Integer.valueOf(curentTextField.getText());
                                                listParameters.add(currentNodeI);
                                                break;
                                            default:
                                                String currentNodeS = curentTextField.getText();
                                                listParameters.add(currentNodeS);
                                                break;
                                        }
                                        i++;
                                    }
                                    try {
                                        Object[] parametersConverted = listParameters.toArray();
                                        if(!sensorSelected.getClass().getSimpleName().equals("MeanSensor"))
                                            ((Sensor)sensorSelected).setSensorAlgoChanger((SensorAlgoChanger) constructorOfAlgo.newInstance(parametersConverted));
                                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }catch(Exception ex) {
                                throw new Exception("Your view needs a submit button");
                            }
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(algoContainer.getChildren().toArray().length != 0)
                    algoContainer.getChildren().remove(0);
            }

        });
        /**
         * Adapt the view of detail according to the algorithm selected
         * Take parameters entered by the user
         * Change the algorithm of the sensor
         */

        if (menuListeView.getItems().size() != 0) {
            menuListeView.getSelectionModel().selectFirst();
            setDisplayVisible(true);
        }
        /**
         * Display the first sensor in the detail if the list of sensor isn't empty
         */
    }


    private void setDisplayVisible(boolean show)
    {
        welcomePane.setVisible(!show);
        displayPane.setVisible(show);
    }

    /**
     * If the user doesn't have sensors, a welcome page is shown instead of the detail
     */

    public void showChildren(ActionEvent actionEvent) throws IOException {
        System.out.println("blabla");
    }


    public void showCamView(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/camView.fxml"));
        CamView cv = new CamView(sensorSelected, new WeatherManager(new WeatherInitializer()));
        loader.setController(cv);
        primaryStage.setTitle("CAM : "+sensorSelected.getSensorName());
        Scene mainScene = new Scene(loader.load(), 800, 400);
        mainScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * Display the view with the camera if the button is pressed
     */

    public void showDigitalView(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/digitalView.fxml"));
        DigitalView cv = new DigitalView(sensorSelected);
        loader.setController(cv);
        primaryStage.setTitle("Digital : "+sensorSelected.getSensorName());
        Scene mainScene = new Scene(loader.load(), 800, 400);
        mainScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    /**
     * Display the view with digital temperature if the button is pressed
     */

    public void deleteSensor(ActionEvent actionEvent){
        sm.deleteSensor(sensorSelected);
        if (menuListeView.getItems().size() != 0) {
            menuListeView.getSelectionModel().selectFirst();
            setDisplayVisible(true);
        }
        else{
            setDisplayVisible(false);
            sensorSelected=null;
        }
    }

    public void showAddView(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addSensorView.fxml"));
        AddSensorView cv = new AddSensorView(sm);
        loader.setController(cv);
        primaryStage.setTitle("Ajouter un sensor");
        Scene mainScene = new Scene(loader.load(), 600, 400);
        mainScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


}


