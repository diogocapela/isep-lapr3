package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import lapr.project.controller.ActionsController;
import lapr.project.model.Bike;
import lapr.project.model.BikeType;
import lapr.project.model.Park;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class UCAvailableBikesOfParkUI implements Initializable {

    private ActionsController controller;

    @FXML
    private ChoiceBox<Park> parksChoiceBox;
    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new ActionsController();
        parksChoiceBox.setItems(FXCollections.observableArrayList(controller.getAllParks()));
    }

    public void handleShowAvailableBikesForSelectedPark() {
        Park park = parksChoiceBox.getValue();
        if (park != null) {
            Map<BikeType, List<Bike>> availableBikesOfParkMap = controller.getAvailableBikesOfPark(park.getIdPark());
            List<PieChart.Data> pieChartData = new ArrayList<>();
            for (Map.Entry<BikeType, List<Bike>> entry : availableBikesOfParkMap.entrySet()) {
                String bikeType = entry.getKey().toString();
                Integer numerOfBikes = entry.getValue().size();
                pieChartData.add(new PieChart.Data(bikeType + " (" + numerOfBikes + ")", numerOfBikes));
            }
            pieChart.setData(FXCollections.observableArrayList(pieChartData));
        } else {
            FXUtils.openAlertError("You need to first select a park first!");
        }
    }

}
