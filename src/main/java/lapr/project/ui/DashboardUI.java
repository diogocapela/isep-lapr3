package lapr.project.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardUI implements Initializable {

    private FXPageChanger pageChanger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageChanger = FXPageChanger.getStaticInstance();
    }

    @FXML
    private void handleOpenBikes() {
        pageChanger.showPage("ManagerBikes.fxml");
    }

    @FXML
    private void handleOpenParks() {
        pageChanger.showPage("ManagerParks.fxml");
    }

    @FXML
    private void handleOpenRides() {
        pageChanger.showPage("ManagerRides.fxml");
    }

    @FXML
    private void handleOpenUsers() {
        pageChanger.showPage("ManagerUsers.fxml");
    }

    @FXML
    public void openUCAvailableBikesOfPark() {
        pageChanger.showPage("UCAvailableBikesOfPark.fxml");
    }

    @FXML
    public void openUCFreeSpacesOfPark() {
        pageChanger.showPage("UCFreeSpacesOfPark.fxml");
    }

}
