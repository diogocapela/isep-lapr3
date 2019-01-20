package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import lapr.project.controller.ActionsController;
import lapr.project.model.BikeType;
import lapr.project.model.Park;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class UCFreeSpacesOfPark implements Initializable {

    private ActionsController controller;

    @FXML
    private ChoiceBox<Park> parksChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new ActionsController();
        parksChoiceBox.setItems(FXCollections.observableArrayList(controller.getAllParks()));
    }

    public void handleShowFreeSpacesForSelectedPark() {
        Park park = parksChoiceBox.getValue();
        if (park != null) {
            Map<BikeType, Integer> freeSpaces = controller.getFreeSpacesOfPark(park.getIdPark());
            int mountainSpaces = freeSpaces.get(BikeType.MOUNTAIN);
            int roadSpaces = freeSpaces.get(BikeType.ROAD);
            int electricalSpaces = freeSpaces.get(BikeType.ELECTRICAL);
            String message = "Free Spaces:\n\nMountain: " + mountainSpaces + "\n\nRoad: " + roadSpaces + "\n\nElectrical: " + electricalSpaces;
            FXUtils.openAlertSuccess(message);
        } else {
            FXUtils.openAlertError("You need to first select a park first!");
        }
    }

}
