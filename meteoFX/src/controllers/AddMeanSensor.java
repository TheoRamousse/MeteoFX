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


    private ComponentSensorManager sm;
    private RootSensor rs;
    private TreeItem<ComponentSensor> root;

    /**
     * List of sensors used by the new mean sensor. Each sensor is linked to a double value (the coefficient)
     */
    private TreeMap<ComponentSensor, Double> children = new TreeMap<>(new IdComparator());

    /**
     * This constructor will take two parameters
     * @param sm for having the SensorManager
     * @param rs in order to access to all the sensors of the view
     * @param root in order to manipulate the main item of the TreeView
     */
    public AddMeanSensor(ComponentSensorManager sm, RootSensor rs, TreeItem root){
        this.sm =sm;
        this.rs = rs;
        this.root = root;
    }

    @FXML
    public void initialize() {
        initializeListSensor();
        initializeCoeffField();

    }

    private void initializeCoeffField(){
        coeffField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([.]\\d{0,4})?")) {
                    coeffField.setText(oldValue);
                }
            }
        });
    }

    private void initializeListSensor(){
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
            MeanSensor newMs = new MeanSensor(rs.maxIdChildren()+1, nameInput.getText());
            for (Map.Entry<ComponentSensor, Double> entry: children.entrySet()) {
                try {
                    entry.getKey().addObserver(newMs);
                } catch (Exception e) {
                    AlertBox.displayWarningAlertBox(e.getMessage());
                }
            }
            newMs.setChildren(children);
            try {
                rs.add(newMs, 1);
            } catch (Exception e) {
                AlertBox.displayWarningAlertBox(e.getMessage());
            }
            sm.addSensor(newMs);
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
