package lapr.project.ui;

import javafx.fxml.FXML;

public class MenuUI {

    private FXPageChanger pageChanger = FXPageChanger.getStaticInstance();

    @FXML
    public void openDashboard() {
        pageChanger.showPage("Dashboard.fxml");
    }

    @FXML
    public void exitApp() {
        Main.exit();
    }

    @FXML
    public void openManagerBikes() {
        pageChanger.showPage("ManagerBikes.fxml");
    }

    @FXML
    public void openManagerParks() {
        pageChanger.showPage("ManagerParks.fxml");
    }

    @FXML
    public void openManagerRides() {
        pageChanger.showPage("ManagerRides.fxml");
    }

    @FXML
    public void openManagerUsers() {
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
