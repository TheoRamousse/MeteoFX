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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainView {
    /**
     * This attribute is used to manage sensors
     */
    private ComponentSensorManager sm;
    private RootSensor rootSensor;
    private TreeItem<ComponentSensor> rootItem;
    /**
     * This attribute manage has the list of sensors and manage them
     */
    private ComponentSensor sensorSelected;
    /**
     * Current sensor selected by the user
     */
    private TreeItem selectedItem;


    @FXML
    private BorderPane displayPane;

    @FXML
    private BorderPane welcomePane;

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
    private HBox hBoxFreq;

    @FXML
    private ComboBox<Number> freqInput;

    @FXML
    private VBox modifyChildrenContainer;


    /**
     * Constructor of MainView : Take the sensor manager in parameter
     */
    public MainView(ComponentSensorManager sm) {

        this.sm = sm;
    }

    /**
     * Constructor of MainView : Take the sensor manager in parameter
     */

    @FXML
    public void initialize() {
        initializeTreeView();

        initializeComboBoxAlgo();

        initializeFreqInput();

        /*
         * Display the first sensor in the detail if the list of sensor isn't empty
         */
        if (rootSensor.getChildren().size() != 0)
            menuTreeView.getSelectionModel().selectFirst();

    }



    private void itemAdd(CompositeSensor compositeSensor, TreeItem<ComponentSensor> treeItem) {
        for (ComponentSensor sens : compositeSensor.getChildren().keySet()) {
            TreeItem<ComponentSensor> treeItemChild = new TreeItem<>(sens);
            if (sens.getClass().getSimpleName().equals("MeanSensor") || sens.getClass().getSimpleName().equals("RootSensor")) {
                itemAdd((CompositeSensor) sens, treeItemChild);
            }
            treeItem.getChildren().add(treeItemChild);
        }
    }

    /**
     * If the user doesn't have sensors, a welcome page is shown instead of the detail
     */
    private void setDisplayVisible(boolean show) {
        welcomePane.setVisible(!show);
        displayPane.setVisible(show);
    }


    /**
     * Display the view with the camera if method is called
     */
    public void showCamView(ActionEvent actionEvent) throws IOException {
        CamView cv = new CamView(sensorSelected, new WeatherManager(new WeatherInitializer()));
        Stage mainStage = ControllerUtility.createStage(cv, "/fxml/camView.fxml", "CAM : " + sensorSelected.getSensorName(), 800, 400);
        mainStage.show();
    }

    /**
     * Display the view with digital temperature if method is called
     */
    public void showDigitalView(ActionEvent actionEvent) throws IOException {
        DigitalView cv = new DigitalView(sensorSelected);
        Stage mainStage = ControllerUtility.createStage(cv, "/fxml/digitalView.fxml", "Digital : " + sensorSelected.getSensorName(), 800, 400);
        mainStage.show();
    }

    /**
     * This method is used to delete the sensor selected in the tree view
     */
    public void deleteSensor(ActionEvent actionEvent) {
        if (selectedItem != null && sensorSelected != null) {
            ((CompositeSensor) selectedItem.getParent().getValue()).remove(sensorSelected);
            selectedItem.getParent().getChildren().remove(selectedItem);
            if (rootItem.getChildren().size() != 0) {
                menuTreeView.getSelectionModel().selectFirst();
            } else {
                sensorSelected = null;
                setDisplayVisible(false);
            }
        }
        else{
            AlertBox.displayWarningAlertBox("Veuillez sélectionner un Capteur à supprimer !");
        }
    }

    /**
     * This method show the view which is used to add a new sensor
     */
    public void showAddView(ActionEvent actionEvent) throws IOException {
        AddSensorView cv = new AddSensorView(sm, rootSensor, rootItem);
        Stage mainStage = ControllerUtility.createStage(cv, "/fxml/addSensorView.fxml", "Ajouter un sensor", 600, 400);
        mainStage.show();
    }


    /**
     * Initialize the tree view which display a tree view of sensors in the master
     */
    private void initializeTreeView() {
        rootSensor = new RootSensor();
        for (ComponentSensor cs : sm.getSensorList()) {
            try {
                rootSensor.add(cs, 0);
            } catch (Exception e) {
                AlertBox.displayWarningAlertBox(e.getMessage());
            }
        }
        rootItem = new TreeItem<>(rootSensor);
        rootItem.setExpanded(true);
        itemAdd(rootSensor, rootItem);
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


        menuTreeView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV)->{
            try {
                if (newV != null) {
                    ComponentSensor oldSensorSelected;
                    sensorSelected = newV.getValue();
                    selectedItem = newV;
                    setDisplayVisible(true);
                    if (oldV != null) {
                        oldSensorSelected = oldV.getValue();
                        nameInput.textProperty().unbindBidirectional(oldSensorSelected.nameProperty());
                        if (!oldSensorSelected.getClass().getSimpleName().equals("MeanSensor")) {
                            freqInput.valueProperty().unbindBidirectional(((Sensor) oldSensorSelected).timeUpdateProperty());
                        }
                    }
                    nameInput.textProperty().bindBidirectional(sensorSelected.nameProperty());
                    if (!sensorSelected.getClass().getSimpleName().equals("MeanSensor")) {
                        hBoxFreq.setVisible(true);
                        hBoxAlgo.setVisible(true);
                        algoContainer.setVisible(true);
                        modifyChildrenContainer.setVisible(false);
                        freqInput.valueProperty().bindBidirectional(((Sensor) sensorSelected).timeUpdateProperty());
                        comboBoxAlgos.getSelectionModel().select(((Sensor) sensorSelected).getAlgoType());
                    }
                    if (sensorSelected.getClass().getSimpleName().equals("MeanSensor")) {
                        hBoxFreq.setVisible(false);
                        hBoxAlgo.setVisible(false);
                        algoContainer.setVisible(false);
                        modifyChildrenContainer.setVisible(true);
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/modifyChildrenMeanSensor.fxml"));
                            ModifyChildrenMeanSensor modifyView = new ModifyChildrenMeanSensor(rootSensor, selectedItem);
                            fxmlLoader.setController(modifyView);
                            if (modifyChildrenContainer.getChildren().size() != 0)
                                modifyChildrenContainer.getChildren().remove(0);
                            modifyChildrenContainer.getChildren().add(fxmlLoader.load());
                        } catch (Exception e) {
                            AlertBox.displayWarningAlertBox(e.getMessage());
                        }
                    }
                    temperatureInput.textProperty().bind(Bindings.format("%.2f", sensorSelected.currentTemperatureProperty()));
                }
            } catch (Exception e) {
                AlertBox.displayWarningAlertBox(e.getMessage());
            }
        });
    }

    /**
     * This method initialize the list of algorithms available for sensors. This compnent :
     * Adapt the view of detail according to the algorithm selected
     * Take parameters entered by the user
     * Change the algorithm of the sensor
     */
    private void initializeComboBoxAlgo() {

        comboBoxAlgos.getItems().addAll(
                SensorAlgoChanger.getSons()
        );

        comboBoxAlgos.valueProperty().addListener(new ChangeListener<>() {
            private Constructor<?> constructorOfAlgo;

            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                try {
                    Constructor<?>[] constructorsOfAlgoSelected = Class.forName("model." + t1).getConstructors();
                    for (Constructor<?> c : constructorsOfAlgoSelected) {
                        constructorOfAlgo = c;
                        if (c.getParameterTypes().length != 0) {
                            try {
                                String pathOfView = t1.replaceFirst(".", ("res/fxml/" + t1.charAt(0) + "").toLowerCase()) + "View.fxml";
                                FXMLLoader fxmlLoader = new FXMLLoader(new File(pathOfView).toURI().toURL());
                                algoContainer.getChildren().add(fxmlLoader.load());
                            } catch (Exception ex) {
                                throw new Exception("Your algo needs a fxml view");
                            }

                            try {
                                Button validate = (Button) algoContainer.lookup("#paramContainer").lookup("#submit");
                                validate.setOnAction((event) -> {
                                    ArrayList<Object> listParameters = new ArrayList<>();
                                    int i = 0;
                                    for (Class<?> currentClass : constructorOfAlgo.getParameterTypes()) {
                                        TextField curentTextField = (TextField) algoContainer.lookup("#paramContainer").lookup("#arg" + i);
                                        switch (currentClass.getName()) {
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
                                        if (!sensorSelected.getClass().getSimpleName().equals("MeanSensor"))
                                            ((Sensor) sensorSelected).setSensorAlgoChanger((SensorAlgoChanger) constructorOfAlgo.newInstance(parametersConverted));
                                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                        AlertBox.displayWarningAlertBox(e.getMessage());
                                    }
                                });
                            } catch (Exception ex) {
                                throw new Exception("Your view needs a submit button");
                            }
                            return;
                        }
                        try {
                            if (!sensorSelected.getClass().getSimpleName().equals("MeanSensor"))
                                ((Sensor) sensorSelected).setSensorAlgoChanger((SensorAlgoChanger) constructorOfAlgo.newInstance());
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            AlertBox.displayWarningAlertBox(e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    AlertBox.displayWarningAlertBox(e.getMessage());
                }
                if (algoContainer.getChildren().toArray().length != 0)
                    algoContainer.getChildren().remove(0);
            }


        });
    }

    /**
     * Set values of freqInput (number of seconds to update the sensor : from 1s to 60s)
     */
    private void initializeFreqInput() {

        for (int i = 1; i < 61; i++) {
            freqInput.getItems().add(i);
        }
    }

}


