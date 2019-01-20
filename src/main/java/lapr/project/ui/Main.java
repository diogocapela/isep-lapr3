package lapr.project.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lapr.project.assessment.Facade;
import lapr.project.controller.ActionsController;
import lapr.project.data.DataHandler;
import lapr.project.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Main extends Application {

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {

        // Load database properties
        //============================================================
        try {
            Properties properties = new Properties(System.getProperties());
            InputStream input = new FileInputStream("./src/main/resources/application.properties");
            properties.load(input);
            input.close();
            System.setProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Populate Initial Data
        //============================================================
        DataHandler dataHandler = new DataHandler();
        dataHandler.scriptRunner("./src/main/resources/store/drops.sql");
        dataHandler.scriptRunner("./src/main/resources/store/creates.sql");
        dataHandler.scriptRunner("./src/main/resources/store/functions.sql");
        dataHandler.scriptRunner("./src/main/resources/store/procedures.sql");
        dataHandler.scriptRunner("./src/main/resources/store/triggers.sql");
        dataHandler.scriptRunner("./src/main/resources/store/inserts.sql");


        /*
        // Login
        //============================================================
        while(!AuthManager.getInstance().isLoggedIn()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduz email:");
            String email = scanner.next();
            System.out.println("Introduz password:");
            String password = scanner.next();
            AuthManager.getInstance().login(email, password);
        }
        */


        // CSV Data
        //============================================================
        /*
        CSVAdapter csvAdapter = new CSVAdapter();
        csvAdapter.loadUsersFromCSV("./src/main/resources/csv/users.csv", ";");
        csvAdapter.loadTouristicPointsFromCSV("./src/main/resources/csv/pois.csv", ";");
        csvAdapter.loadParksFromCSV("./src/main/resources/csv/parks.csv", ";");
        csvAdapter.loadRoutesFromCSV("./src/main/resources/csv/paths.csv", ";");
        csvAdapter.loadBikesFromCSV("./src/main/resources/csv/bicycles.csv", ";");
        */


        // Facade Test
        //============================================================
        ActionsController actionsController = new ActionsController();
        Facade facade = new Facade();
        //System.out.println(facade.addBicycles("./src/main/resources/input/bicycles.csv"));
        //System.out.println(facade.addParks("./src/main/resources/input/parks.csv"));
        //System.out.println(facade.addPOIs("./src/main/resources/input/pois.csv"));
        //System.out.println(facade.addUsers("./src/main/resources/input/users.csv"));
        //System.out.println(facade.addPaths("./src/main/resources/input/paths.csv"));
        //System.out.println(facade.getNumberOfBicyclesAtPark(17.312406, 41.887231, "./src/main/resources/testing/numberOfBikesAtPark.csv"));
        //System.out.println(facade.getFreeSlotsAtPArk(17.312406, 41.887231, "rbradburne3@upenn.edu"));
        //facade.getNearestParks(17.312406, 41.887231, "./src/main/resources/testing/nearestPark.csv");
        //System.out.println(facade.distanceTo(17.312406, 41.887231, 41.152712, -8.609297));
        //System.out.println(actionsController.g);
        //System.out.println(facade.unlockBicycle("1","Bike A"));
        //System.out.println(facade.lockBicycle("Bike D", 17.312406, 41.887231));
        //System.out.println(facade.getUserCurrentDebt("3", "./src/main/resources/testing/getUserCurrentDebt.csv"))
        //System.out.println(facade.getUserCurrentPoints("1", "./src/main/resources/testing/getUserCurrentPoints.csv"));
        //System.out.println(facade.unlockAnyElectricBicycleAtPark(17.312406, 41.887231, "7", "./src/main/resources/testing/unlockAnyBike.csv" ));
        //System.out.println(facade.unlockAnyElectricBicycleAtPark(17.312406, 41.887231, "7", "./src/main/resources/testing/unlockAnyElectricBike.csv"));
        //System.out.println(facade.calculateElectricalEnergyToTravelFromOneLocationToAnother(17.312406, 41.887231, 41.152712, -8.609297, "7"));
        //System.out.println(facade.suggestElectricalBicyclesToGoFromOneParkToAnother(41.150004, -8.676257, 41.152712, -8.609297,"7", "./src/main/resources/testing/suggestElectricBike.csv"));
        //System.out.println(facade.forHowLongWasTheBicycleUnlocked("Bike A"));
        //System.out.println(facade.shortestRouteBetweenTwoParks(41.167331, -8.688336, 41.150004, -8.676257, "./src/main/resources/testing/testRoute1.csv"));
        //System.out.println(facade.shortestRouteBetweenTwoParks(41.150004, -8.676257, 41.167331, -8.688336, "./src/main/resources/testing/testRoute2.csv"));
        //System.out.println(facade.mostEnergyEfficientRouteBetweenTwoParks(41.167331, -8.688336, 41.150004, -8.676257, "electric", "8", "./src/main/resources/testing/mostEnergyEfficient.csv"));
        //System.out.println(facade.shortestRouteBetweenTwoParksForGivenPOIs(41.167331, -8.688336, 41.150004, -8.676257,"./src/main/resources/input/pois_tst.csv", "./src/main/resources/testing/shortestRoutesPOIs.csv"));
        //System.out.println(facade.getParkChargingReportForPark(17.312406, 41.887231, "./src/main/resources/testing/parkReport.csv"));
        //System.out.println(facade.suggestRoutesBetweenTwoLocations(41.167331, -8.688336, 41.150004, -8.676257, "electric", "7", 5, true, "energy", "./src/main/resources/input/pois_tst.csv","./src/main/resources/testing/suggestRoutes.csv"));
        //System.out.println(facade.getInvoiceForMonth(10,"1","./src/main/resources/testing/testInv1.csv"));
        //System.out.println(facade.getInvoiceForMonth(8,"11","./src/main/resources/testing/testInv2.csv"));



        // Start JavaFX app
        //============================================================
        launch(args);


        // Show console menu
        //============================================================
        MenuConsola.showMenu();
    }

    /**
     * Exit from the app.
     */
    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * JavaFX start method.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Main pane for the JavaFX pages
        BorderPane mainPane = new BorderPane();
        // Pass the main pane down to the PageChanger, so it can change pages when requested
        new FXPageChanger(mainPane);
        // Add menu to the top of the main pane
        Parent menu = FXMLLoader.load(getClass().getResource("/views/Menu.fxml"));
        mainPane.setTop(menu);
        // Create the scene with our main pane and set it's width and height
        Scene scene = new Scene(mainPane, 1000, 800);
        // Set the CSS stylesheet to our scene
        scene.getStylesheets().add("/styles/Styles.css");
        // Configure the stage and show it
        stage.setTitle("LAPR3 Project");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
        // Set the home page of the app

        FXPageChanger.getStaticInstance().showPage("Dashboard.fxml");

    }
}

