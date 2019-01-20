package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.BaseController;
import lapr.project.model.Bike;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerBikesUI implements Initializable {

    private BaseController controller;

    @FXML
    private TableView<Bike> table;
    @FXML
    private TableColumn<Bike, String> col1;
    @FXML
    private TableColumn<Bike, String> col2;
    @FXML
    private TableColumn<Bike, String> col3;
    @FXML
    private TableColumn<Bike, String> col4;
    @FXML
    private TableColumn<Bike, String> col5;
    @FXML
    private TableColumn<Bike, String> col6;
    @FXML
    private TextField idBike;
    @FXML
    private TextField idPark;
    @FXML
    private TextField type;
    @FXML
    private TextField currentBattery;
    @FXML
    private TextField maxBattery;
    @FXML
    private TextField isActive;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new BaseController();
        updateView();
    }

    private void updateView() {
        // Update table
        col1.setCellValueFactory(new PropertyValueFactory<>("idBike"));
        col2.setCellValueFactory(new PropertyValueFactory<>("idPark"));
        col3.setCellValueFactory(new PropertyValueFactory<>("type"));
        System.out.println("updateView()");
        col4.setCellValueFactory(new PropertyValueFactory<>("currentBattery"));
        col5.setCellValueFactory(new PropertyValueFactory<>("maxBattery"));
        col6.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        table.setItems(FXCollections.observableArrayList(controller.getAllBikes()));
        // Clear input fields
        idBike.clear();
        idPark.clear();
        type.clear();
        currentBattery.clear();
        maxBattery.clear();
        isActive.clear();
    }

    @FXML
    private void handleAddBike() {
        try {
            Bike bike = new Bike();
            bike.setIdBike(Integer.parseInt(idBike.getText()));
            bike.setIdPark(Integer.parseInt(idPark.getText()));
            bike.setType(type.getText());
            bike.setCurrentBattery(Double.parseDouble(currentBattery.getText()));
            bike.setMaxBattery(Double.parseDouble(maxBattery.getText()));
            bike.setIsActive(Boolean.parseBoolean(isActive.getText()));
            controller.addBike(bike);
            FXUtils.openAlertSuccess("Bike added successfully!");
            updateView();
        } catch (IllegalArgumentException e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

    @FXML
    private void handleRemoveBike() {
        Bike selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.removeBikeById(selected.getIdBike());
            FXUtils.openAlertSuccess("Bike removed successfully!");
            updateView();
        } else {
            FXUtils.openAlertError("You need to select a bike first!");
        }
    }

    @FXML
    private void handleDeactivateBike() {
        Bike selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.deactivateBike(selected.getIdBike());
            FXUtils.openAlertSuccess("Bike deactivated successfully!");
            updateView();
        } else {
            FXUtils.openAlertError("You need to select a bike first!");
        }
    }

    @FXML
    private void handleActivateBike() {
        throw new UnsupportedOperationException();
    }

}
