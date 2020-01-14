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
import java.util.Map;
import java.util.Set;

public class MainView {
    private ComponentSensorManager sm;
    private RootSensor rootSensor;
    private TreeItem<ComponentSensor> rootItem;
    /**
     * This attribute manage has the list of sensors and manage them
     */
    private ComponentSensor sensorSelected;
    private TreeItem selectedItem;
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
    private TreeView<ComponentSensor> menuTreeView;


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
        rootSensor = new RootSensor();
        for (ComponentSensor cs: sm.getSensorList()) {
            rootSensor.add(cs, 0);
        }
        rootItem = new TreeItem<>(rootSensor);
        rootItem.setExpanded(true);
        rootItem = itemAdd(rootSensor, rootItem);
        menuTreeView.setRoot(rootItem);
        menuTreeView.setShowRoot(false);

        menuTreeView.setCellFactory(__ ->
                new TreeCell<ComponentSensor>(){
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
                });

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



        menuTreeView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV)->{
            try {
                ComponentSensor oldSensorSelected;
                sensorSelected = newV.getValue();
                selectedItem = newV;
                setDisplayVisible(true);
                if (oldV != null) {
                    oldSensorSelected = oldV.getValue();
                    nameInput.textProperty().unbindBidirectional(oldSensorSelected.nameProperty());
                    if(!oldSensorSelected.getClass().getSimpleName().equals("MeanSensor")) {
                        freqInput.valueProperty().unbindBidirectional(((Sensor)oldSensorSelected).timeUpdateProperty());
                    }
                }
                //sensorNum.textProperty().bind(sm.findComponentSensorById(sensorSelected.getSensorId()).idProperty().asString());
                nameInput.textProperty().bindBidirectional(sensorSelected.nameProperty());
                if(!sensorSelected.getClass().getSimpleName().equals("MeanSensor")) {
                    hBoxFreq.setVisible(true);
                    hBoxAlgo.setVisible(true);
                    freqInput.valueProperty().bindBidirectional(((Sensor)sensorSelected).timeUpdateProperty());
                    comboBoxAlgos.getSelectionModel().select(((Sensor)sensorSelected).getAlgoType());
                }
                if (sensorSelected.getClass().getSimpleName().equals("MeanSensor")) {
                    hBoxFreq.setVisible(false);
                    hBoxAlgo.setVisible(false);
                }
                temperatureInput.textProperty().bind(Bindings.format("%.2f", sensorSelected.currentTemperatureProperty()));
            }
            catch(Exception e){
                e.printStackTrace();
            }
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
                        constructorOfAlgo = c;
                        if (c.getParameterTypes().length != 0) {
                            //constructorOfAlgo = c;
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
                                                double currentNodeD = Double.parseDouble(curentTextField.getText());
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
                        try {
                            if(!sensorSelected.getClass().getSimpleName().equals("MeanSensor"))
                                ((Sensor)sensorSelected).setSensorAlgoChanger((SensorAlgoChanger) constructorOfAlgo.newInstance());
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            System.out.println("aie");
                            e.printStackTrace();
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

    if (rootSensor.getChildren().size() != 0)
        menuTreeView.getSelectionModel().selectFirst();

/*
        if (menuListeView.getItems().size() != 0) {
            menuListeView.getSelectionModel().selectFirst();
            setDisplayVisible(true);
        }
*/
        /**
         * Display the first sensor in the detail if the list of sensor isn't empty
         */
    }

    private TreeItem<ComponentSensor> itemAdd(CompositeSensor compositeSensor, TreeItem<ComponentSensor> treeItem) {
        for (ComponentSensor sens: compositeSensor.getChildren().keySet()) {
            TreeItem<ComponentSensor> treeItemChild = new TreeItem<>(sens);
            if (sens.getClass().getSimpleName().equals("MeanSensor") || sens.getClass().getSimpleName().equals("RootSensor")){
                treeItemChild = itemAdd((CompositeSensor) sens, treeItemChild);
            }
            treeItem.getChildren().add(treeItemChild);
        }
        return treeItem;
    }


    private void setDisplayVisible(boolean show)
    {
        welcomePane.setVisible(!show);
        displayPane.setVisible(show);
    }

    /**
     * If the user doesn't have sensors, a welcome page is shown instead of the detail
     */


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
        ((CompositeSensor)selectedItem.getParent().getValue()).remove(sensorSelected);
        selectedItem.getParent().getChildren().remove(selectedItem);
        if (rootItem.getChildren().size() != 0) {
            menuTreeView.getSelectionModel().selectFirst();
        }
        else{
            sensorSelected=null;
            setDisplayVisible(false);
        }
    }

    public void showAddView(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addSensorView.fxml"));
        AddSensorView cv = new AddSensorView(/*sm*/rootSensor, rootItem);
        loader.setController(cv);
        primaryStage.setTitle("Ajouter un sensor");
        Scene mainScene = new Scene(loader.load(), 600, 400);
        mainScene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


}


