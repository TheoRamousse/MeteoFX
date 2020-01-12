package controllers;

import com.sun.source.tree.Tree;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import model.*;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.TreeMap;

public class AddMeanSensor {
    @FXML
    TextField nameInput;

    @FXML
    TextField coeffField;

    @FXML
    ComboBox<ComponentSensor> listSensors;


    private ComponentSensorManager sm;
    private RootSensor rs;

    private TreeMap<ComponentSensor, Double> children = new TreeMap<>(new NameComparator());

    public AddMeanSensor(ComponentSensorManager sm) {

        this.sm = sm;
    }

    public AddMeanSensor(RootSensor rs){
        this.rs = rs;
    }

    @FXML
    public void initialize() {

        listSensors.itemsProperty().bind(
                /*sm.componentSensorListProperty()*/rs.componentSensorListProperty()
        );

        listSensors.getSelectionModel().selectFirst();

        listSensors.setCellFactory(__ ->
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

        coeffField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    coeffField.setText(oldValue);
                }
            }
        });

    }

    public void addChild(ActionEvent actionEvent){
        children.put(listSensors.getValue(), Double.valueOf(coeffField.getText()));
        coeffField.setText("");
    }

    public void createMeanSensor(ActionEvent actionEvent){
        if(!nameInput.getText().isEmpty() && !coeffField.getText().isEmpty())
        {
            MeanSensor newMs = new MeanSensor(/*sm.getMaxId()+1*/rs.maxIdChildren()+1, nameInput.getText());
            for (Map.Entry<ComponentSensor, Double> entry: children.entrySet()) {
                try {
                    entry.getKey().addObserver(newMs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            newMs.setChildren(children);
            /*sm.addSensor(newMs);*/
            rs.add(newMs, 1);
        }
        else{
            AlertBox.displayWarningAlertBox("Veuillez remplir tous les champs");
        }
    }

}