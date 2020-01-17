package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.*;
import javafx.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * This class has the responsibility to manage the information displayed and the interactions made with the view when adding any type of sensor.
 */
public class AddSensorView {

    private ComponentSensorManager sm;
    private RootSensor rs;
    private TreeItem<ComponentSensor> root;
    private Constructor<?> constructorOfAlgo = null;

    @FXML
    private TextField nameInput;

    @FXML
    private ComboBox<String> comboBoxAlgos;

    @FXML
    private ToggleButton selectButton;

    @FXML
    private ComboBox<Integer> freqInput;

    @FXML
    private VBox algoContainer;

    @FXML
    private VBox displayContainer;




    public AddSensorView(RootSensor rs, TreeItem root){
        this.rs = rs;
        this.root = root;
    }

    @FXML
    public void initialize() {
        showGoodSettings(null);
    }
    public void showGoodSettings(ActionEvent actionEvent){
        if(displayContainer.getChildren().size() != 0)
            displayContainer.getChildren().remove(0);
        if(selectButton.isSelected()){
            try {
                AddMeanSensor ams = new AddMeanSensor(/*sm*/rs, root);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addMeanSensor.fxml"));
                loader.setController(ams);
                displayContainer.getChildren().add(loader.load());
            }catch(Exception e){
                AlertBox.displayWarningAlertBox(e.getMessage());
            }


        }
        else{
            try {
                AddDefaultSensor ads = new AddDefaultSensor(/*sm*/rs, root);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addDefaultSensor.fxml"));
                loader.setController(ads);
                displayContainer.getChildren().add(loader.load());
            }catch(Exception e){
                AlertBox.displayWarningAlertBox(e.getMessage());
            }
        }
    }

    public void addSensor(ActionEvent actionEvent) {
        if(constructorOfAlgo != null) {
            ArrayList<Object> listParameters = new ArrayList<>();
            int i = 0;
            for (Class<?> currentClass : constructorOfAlgo.getParameterTypes()) {
                TextField currentTextField = (TextField) algoContainer.lookup("#paramContainer").lookup("#arg" + i);
                switch (currentClass.getName()) {
                    case "double":
                        double currentNodeD = Double.parseDouble(currentTextField.getText());
                        listParameters.add(currentNodeD);
                        break;
                    case "int":
                        Integer currentNodeI = Integer.valueOf(currentTextField.getText());
                        listParameters.add(currentNodeI);
                        break;
                    default:
                        String currentNodeS = currentTextField.getText();
                        listParameters.add(currentNodeS);
                        break;
                }
                i++;
            }
            try {
                Object[] parametersConverted = listParameters.toArray();
                SensorAlgoChanger algoWanted = (SensorAlgoChanger) constructorOfAlgo.newInstance(parametersConverted);
                sm.addSensor(new Sensor(sm.getMaxId()+1, nameInput.getText(), algoWanted, freqInput.getValue()));
                //System.out.println("Ok");
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                AlertBox.displayWarningAlertBox(e.getMessage());
            }
        }
        else
        {
            AlertBox.displayWarningAlertBox("Une erreur innatendue est apparue !");
        }
    }

}