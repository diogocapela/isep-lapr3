package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.BaseController;
import lapr.project.model.Ride;
import lapr.project.utils.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerRidesUI implements Initializable {

    private BaseController controller;

    @FXML
    private TableView<Ride> table;
    @FXML
    private TableColumn<Ride, String> col1;
    @FXML
    private TableColumn<Ride, String> col2;
    @FXML
    private TableColumn<Ride, String> col3;
    @FXML
    private TableColumn<Ride, String> col4;
    @FXML
    private TableColumn<Ride, String> col5;
    @FXML
    private TableColumn<Ride, String> col6;
    @FXML
    private TableColumn<Ride, String> col7;
    @FXML
    private TextField idRide;
    @FXML
    private TextField idBike;
    @FXML
    private TextField idUser;
    @FXML
    private TextField idStartPark;
    @FXML
    private TextField idEndPark;
    @FXML
    private TextField timestampStart;
    @FXML
    private TextField timestampFinish;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new BaseController();
        updateView();
    }

    private void updateView() {
        // Update table
        col1.setCellValueFactory(new PropertyValueFactory<>("idRide"));
        col2.setCellValueFactory(new PropertyValueFactory<>("idBike"));
        col3.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        Logger.log("updateView()");
        col4.setCellValueFactory(new PropertyValueFactory<>("idStartPark"));
        col5.setCellValueFactory(new PropertyValueFactory<>("idEndPark"));
        col6.setCellValueFactory(new PropertyValueFactory<>("timestampStart"));
        col7.setCellValueFactory(new PropertyValueFactory<>("timestampFinish"));
        table.setItems(FXCollections.observableArrayList(controller.getAllRides()));
        // Clear input fields
        idRide.clear();
        idBike.clear();
        idUser.clear();
        idStartPark.clear();
        idEndPark.clear();
        timestampStart.clear();
        timestampFinish.clear();
    }

    @FXML
    private void handleAddRide() {
        try {
            Ride ride = new Ride();
            ride.setIdRide(Integer.parseInt(idRide.getText()));
            ride.setIdBike(Integer.parseInt(idBike.getText()));
            ride.setIdUser(Integer.parseInt(idUser.getText()));
            ride.setIdStartPark(Integer.parseInt(idStartPark.getText()));
            ride.setIdEndPark(Integer.parseInt(idEndPark.getText()));
            ride.setTimestampStart(timestampStart.getText());
            ride.setTimestampFinish(timestampFinish.getText());
            controller.addRide(ride);
            FXUtils.openAlertSuccess("Ride added successfully!");
            updateView();
        } catch (IllegalArgumentException e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

    @FXML
    private void handleRemoveRide() {
        Ride selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.removeRideById(selected.getIdRide());
            FXUtils.openAlertSuccess("Ride removed successfully!");
            updateView();
        } else {
            FXUtils.openAlertError("You need to select a ride first!");
        }
    }

    @FXML
    private void handleDeactivateRide() {
        throw new UnsupportedOperationException();
    }

    @FXML
    private void handleActivateRide() {
        throw new UnsupportedOperationException();
    }

}
