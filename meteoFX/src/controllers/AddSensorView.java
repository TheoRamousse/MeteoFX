package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.*;

import javafx.event.ActionEvent;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class AddSensorView {

    private ComponentSensorManager sm;
    private Constructor<?> constructorOfAlgo = null;

    @FXML
    private TextField nameInput;

    @FXML
    private ComboBox<String> comboBoxAlgos;

    @FXML
    private ComboBox<Integer> freqInput;

    @FXML
    private VBox algoContainer;


    public AddSensorView(ComponentSensorManager sm) {
        this.sm = sm;
    }

    @FXML
    public void initialize() {
        comboBoxAlgos.getItems().addAll(
                SensorAlgoChanger.getSons()
        );

        comboBoxAlgos.valueProperty().addListener(new ChangeListener<String>() {

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
                    e.printStackTrace();
                }
                if (algoContainer.getChildren().toArray().length != 0)
                    algoContainer.getChildren().remove(0);
            }

        });

        for (int i = 1; i < 61; i++) {
            freqInput.getItems().add(i);
        }
    }

    public void addSensor(ActionEvent actionEvent) {
        if(constructorOfAlgo != null) {
            ArrayList<Object> listParameters = new ArrayList<>();
            int i = 0;
            for (Class<?> currentClass : constructorOfAlgo.getParameterTypes()) {
                TextField curentTextField = (TextField) algoContainer.lookup("#paramContainer").lookup("#arg" + i);
                switch (currentClass.getName()) {
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
                SensorAlgoChanger algoWanted = (SensorAlgoChanger) constructorOfAlgo.newInstance(parametersConverted);
                //System.out.println(sm.addSensor(new Sensor(sm.getMaxId()+1, nameInput.getText(), algoWanted, freqInput.getValue())));
                System.out.println("Ok");
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("coucou");
        }
    }

}