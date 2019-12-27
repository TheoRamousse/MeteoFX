package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class AlgoSmallFluctuationView {

    @FXML
    private TextField textFieldDefaultTemp;

    @FXML
    private TextField textFieldCoef;

    @FXML
    public void initialize()
    {
        textFieldDefaultTemp.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    textFieldDefaultTemp.setText(oldValue);
                }
            }
        });

        textFieldCoef.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    textFieldCoef.setText(oldValue);
                }
            }
        });
    }
}

