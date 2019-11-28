package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import launcher.Main;
import model.Sensor;
import model.SensorManager;


import java.io.IOException;

public class MainView {
    private SensorManager sm;

    @FXML
    private ListView<Sensor> menuListeView;

    public MainView(SensorManager sm) {
        this.sm = sm;
    }

    @FXML
    public void initialize(SensorManager sm)
    {
        menuListeView.itemsProperty().bind(sm.getSensorList());


        menuListeView.setCellFactory(__ ->
                new ListCell<Sensor>(){
                    @Override
                    protected void updateItem(Sensor item, boolean empty) {
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

    public void showCamView(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/camView.fxml"));
        Stage newWindow = new Stage();
        newWindow.setTitle("Hello World");
        newWindow.setScene(new Scene(root, 700, 500));
        newWindow.show();
    }
}


