package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import model.ComponentSensor;
import model.MeanSensor;
import model.RootSensor;

import java.io.IOException;

/**
 * This class has the responsibility to manage the information displayed and the interactions made with the view
 * displayed for a MeanSensor in order to modify its children.
 */
public class ModifyChildrenMeanSensor {
    private RootSensor rootSensor;
    private TreeItem<ComponentSensor> msItem;

    @FXML
    private ComboBox<ComponentSensor> listChildrenSensors;

    @FXML
    private TextField coefChildfField;

    @FXML
    private ComboBox<ComponentSensor> listSensors;

    @FXML
    private TextField coeffField;

    public ModifyChildrenMeanSensor(RootSensor rootSensor, TreeItem msItem){
        this.rootSensor = rootSensor;
        this.msItem = msItem;
    }

    @FXML
    public void initialize()
    {
        initializeModifList();
        initializeAddList();
    }

    private void initializeModifList(){
        listChildrenSensors.itemsProperty().bind(
                ((MeanSensor)msItem.getValue()).componentSensorListProperty()
        );

        listChildrenSensors.setCellFactory(__ ->
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

        listChildrenSensors.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV)->{
            try {
                coefChildfField.setText(((MeanSensor)msItem.getValue()).getChildren().get(newV).toString());
            }
            catch (Exception e){
                AlertBox.displayWarningAlertBox(e.getMessage());
            }
        });

        listChildrenSensors.getSelectionModel().selectFirst();
    }

    private void initializeAddList(){
        coefChildfField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                    coefChildfField.setText(oldValue);
                }
            }
        });

        listSensors.itemsProperty().bind(
                rootSensor.componentSensorListProperty()
        );

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

        listSensors.getSelectionModel().selectFirst();

        coeffField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                    coeffField.setText(oldValue);
                }
            }
        });
    }

    /**
     * This method is called when the user clicks on the button to modify a child. It's meant to apply the changes.
     */
    public void validateModif(ActionEvent actionEvent) throws IOException {
        ((MeanSensor)msItem.getValue()).getChildren().replace(listChildrenSensors.getValue(), ((MeanSensor)msItem.getValue()).getChildren().get(listChildrenSensors.getValue()), Double.parseDouble(coefChildfField.getText()));
    }

    /**
     * This method is called when the user clicks on the button to add a child. It's meant to add the child selected to the MainSensor.
     */
    public void addChild(ActionEvent actionEvent) throws IOException {
        if(!((MeanSensor)msItem.getValue()).childExists(listSensors.getValue()) && msItem.getValue() != listSensors.getValue()) {
            try {
                listSensors.getValue().addObserver(((MeanSensor) msItem.getValue()));
                ((MeanSensor) msItem.getValue()).add(listSensors.getValue(), Double.parseDouble(coeffField.getText()));
            } catch (Exception e) {
                AlertBox.displayWarningAlertBox(e.getMessage());
            }
            msItem.getChildren().add(new TreeItem<ComponentSensor>(listSensors.getValue()));
        }
    }
}
