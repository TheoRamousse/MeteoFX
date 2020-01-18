package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * This class has the responsibility to manage the information displayed and the interactions made with the view
 * displayed when a Sensor is using or means to use an AlgoSmallFluctuation class.
 */
public class AlgoSmallFluctuationView {

    @FXML
    private TextField textFieldDefaultTemp;

    @FXML
    private TextField textFieldCoef;

    @FXML
    public void initialize()
    {
        initializeTextFieldCoef();
        initializeTextFieldDefaultTemp();
    }

    /**
     * Initialization of the textfield which get the first temperature of the sensor. This method allow only numbers for the text field
     */
    private void initializeTextFieldDefaultTemp(){
        textFieldDefaultTemp.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                    textFieldDefaultTemp.setText(oldValue);
                }
            }
        });
    }
    /**
     * Initialization of the textfield which get the coefficient of the sensor. This method allow only numbers for the text field
     */
    private void initializeTextFieldCoef(){
        textFieldCoef.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                    textFieldCoef.setText(oldValue);
                }
            }
        });
    }
}

