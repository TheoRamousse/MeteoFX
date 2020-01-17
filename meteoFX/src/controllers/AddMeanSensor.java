package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import java.util.Map;
import java.util.TreeMap;
/**
 * This class has the responsibility to manage the information displayed and the interactions made with the view when
 * adding a MeanSensor.
 */
public class AddMeanSensor {
    @FXML
    private TextField nameInput;

    @FXML
    private TextField coeffField;

    @FXML
    private ComboBox<ComponentSensor> listSensors;


    private RootSensor rs;
    private TreeItem<ComponentSensor> root;

    private TreeMap<ComponentSensor, Double> children = new TreeMap<>(new NameComparator());

    /**
     * This constructor will take two parameters
     * @param rs in order to access to all the sensors of the view
     * @param root in order to manipulate the main item of the TreeView
     */
    public AddMeanSensor(RootSensor rs, TreeItem root){
        this.rs = rs;
        this.root = root;
    }

    @FXML
    public void initialize() {

        listSensors.itemsProperty().bind(
                rs.componentSensorListProperty()
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

    /**
     * This method creates a new sensor and adds it to the RootSensor and the TreeView.
     * It is called from the add button of the view.
     */
    public void addChild(ActionEvent actionEvent){
        if (children.get(listSensors.getValue()) == null) {
            children.put(listSensors.getValue(), Double.valueOf(coeffField.getText()));
            coeffField.setText("");
        }
    }

    /**
     * Creates the meanSensor and configures it
     */
    public void createMeanSensor(ActionEvent actionEvent){
        if(!nameInput.getText().isEmpty())
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
            try {
                rs.add(newMs, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            TreeItem<ComponentSensor> newMsItem = new TreeItem<>(newMs);
            for (ComponentSensor cs: children.keySet()) {
                newMsItem.getChildren().add(new TreeItem<>(cs));
            }
            root.getChildren().add(newMsItem);
        }
        else{
            AlertBox.displayWarningAlertBox("Veuillez remplir tous les champs");
        }
    }

}
