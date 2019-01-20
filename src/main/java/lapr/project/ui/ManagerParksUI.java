package lapr.project.ui;

import com.sun.media.jfxmedia.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.BaseController;
import lapr.project.model.Park;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerParksUI implements Initializable {

    private BaseController controller;

    @FXML
    private TableView<Park> table;
    @FXML
    private TableColumn<Park, String> col1;
    @FXML
    private TableColumn<Park, String> col2;
    @FXML
    private TableColumn<Park, String> col3;
    @FXML
    private TableColumn<Park, String> col4;
    @FXML
    private TableColumn<Park, String> col5;
    @FXML
    private TableColumn<Park, String> col6;
    @FXML
    private TableColumn<Park, String> col7;
    @FXML
    private TextField idPark;
    @FXML
    private TextField geoLatitude;
    @FXML
    private TextField geoLongitude;
    @FXML
    private TextField geoAltitude;
    @FXML
    private TextField maxCapacityElectric;
    @FXML
    private TextField maxCapacityStandard;
    @FXML
    private TextField isActive;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new BaseController();
        updateView();
    }

    private void updateView() {
        // Update table
        col1.setCellValueFactory(new PropertyValueFactory<>("idPark"));
        col2.setCellValueFactory(new PropertyValueFactory<>("geoLatitude"));
        col3.setCellValueFactory(new PropertyValueFactory<>("geoLongitude"));
        col4.setCellValueFactory(new PropertyValueFactory<>("geoAltitude"));
        col5.setCellValueFactory(new PropertyValueFactory<>("maxCapacityElectric"));
        col6.setCellValueFactory(new PropertyValueFactory<>("maxCapacityStandard"));
        col7.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        table.setItems(FXCollections.observableArrayList(controller.getAllParks()));
        // Clear input fields
        idPark.clear();
        geoLatitude.clear();
        geoLongitude.clear();
        geoAltitude.clear();
        maxCapacityElectric.clear();
        maxCapacityStandard.clear();
        isActive.clear();
    }

    @FXML
    private void handleAddPark() {
        try {
            Park park = new Park();
            park.setIdPark(Integer.parseInt(idPark.getText()));
            park.setGeoLatitude(Double.parseDouble(geoLatitude.getText()));
            park.setGeoLongitude(Double.parseDouble(geoLongitude.getText()));
            park.setGeoAltitude(Double.parseDouble(geoAltitude.getText()));
            park.setMaxCapacityElectric(Integer.parseInt(maxCapacityElectric.getText()));
            park.setMaxCapacityStandard(Integer.parseInt(maxCapacityStandard.getText()));
            park.setActive(Boolean.parseBoolean(isActive.getText()));
            controller.addPark(park);
            FXUtils.openAlertSuccess("Park added successfully!");
            updateView();
        } catch (IllegalArgumentException e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

    @FXML
    private void handleRemovePark() {
        Park selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.removeParkById(selected.getIdPark());
            FXUtils.openAlertSuccess("Park removed successfully!");
            updateView();
        } else {
            FXUtils.openAlertError("You need to select a park first!");
        }
    }

    @FXML
    private void handleDeactivatePark() {
        Park selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.deactivatePark(selected.getIdPark());
            FXUtils.openAlertSuccess("Park deactivated successfully!");
            updateView();
        } else {
            FXUtils.openAlertError("You need to select a park first!");
        }
    }

    @FXML
    private void handleActivatePark() {
        throw new UnsupportedOperationException();
    }

}
