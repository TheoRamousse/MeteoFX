package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * This class has the responsibility to manage the information displayed and the interactions made with the view when
 * adding a Sensor.
 */
public class AddDefaultSensor {

    private Constructor<?> constructorOfAlgo = null;
    private RootSensor rs;
    private TreeItem<ComponentSensor> root;

    @FXML
    private TextField nameInput;

    @FXML
    private ComboBox<String> comboBoxAlgos;

    @FXML
    private ComboBox<Integer> freqInput;

    @FXML
    private VBox algoContainer;


    /**
     * This constructor will take two parameters
     * @param rs in order to access to all the sensors of the view
     * @param root in order to manipulate the main item of the TreeView
     */
    public AddDefaultSensor(RootSensor rs, TreeItem root){
        this.rs = rs;
        this.root = root;
    }

    @FXML
    public void initialize() {
        initializeAlgoBox();
        initializeFreqInput();
    }

    /**
     * Initialize a combobox made of algorithms implemented in the software
     */
    private void initializeAlgoBox(){
        comboBoxAlgos.getItems().addAll(
                SensorAlgoChanger.getSons()
        );

        comboBoxAlgos.valueProperty().addListener(new ChangeListener<>() {

            /**
             *Constructor of algorithm selected
             */

            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                try {
                    Constructor<?>[] constructorsOfAlgoSelected = Class.forName("model." + t1).getConstructors();
                    constructorOfAlgo = constructorsOfAlgoSelected[0];
                    for (Constructor<?> c : constructorsOfAlgoSelected) {
                        if (c.getParameterTypes().length != 0) {
                            constructorOfAlgo = c;
                            try {
                                String pathOfView = t1.replaceFirst(".", ("res/fxml/" + t1.charAt(0) + "").toLowerCase()) + "View.fxml";
                                FXMLLoader fxmlLoader = new FXMLLoader(new File(pathOfView).toURI().toURL());
                                algoContainer.getChildren().add(fxmlLoader.load());
                            } catch (Exception ex) {
                                throw new Exception("Your algo needs a fxml view");
                            }

                            try {
                                Button validate = (Button) algoContainer.lookup("#paramContainer").lookup("#submit");
                                validate.setVisible(false);
                            } catch (Exception ex) {
                                throw new Exception("Your view needs a submit button");
                            }
                            return;
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
     * Initialize the combo box made of numbers from 1 to 60 (seconds)
     */
    private void initializeFreqInput(){
        for (int i = 1; i < 61; i++) {
            freqInput.getItems().add(i);
        }
    }

    /**
     * This method creates a new sensor and adds it to the RootSensor and the TreeView.
     * It is called from the add button of the view.
     */
    public void addSensor(ActionEvent actionEvent) {
        if(constructorOfAlgo != null) {
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
                SensorAlgoChanger algoWanted = (SensorAlgoChanger) constructorOfAlgo.newInstance(parametersConverted);
                Sensor newS = new Sensor(rs.maxIdChildren()+1, nameInput.getText(), algoWanted, freqInput.getValue());
                try {
                    rs.add(newS, 1);
                } catch (Exception e) {
                    AlertBox.displayWarningAlertBox("Impossible d'ajouter un MeanSensor à lui-même");
                }
                root.getChildren().add(new TreeItem<>(newS));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                AlertBox.displayWarningAlertBox("Veuillez remplir tous les champs de la création");
            }
        }
        else
        {
            AlertBox.displayWarningAlertBox("Veuillez remplir tous les champs de la création");
        }
    }


}
