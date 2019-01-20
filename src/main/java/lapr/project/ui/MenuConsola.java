package lapr.project.ui;

import javafx.util.Pair;
import lapr.project.controller.*;
import lapr.project.data.CSVAdapter;
import lapr.project.data.DataUtils;
import lapr.project.model.*;
import lapr.project.utils.AuthManager;
import lapr.project.utils.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuConsola {

    // Final Variables (Code smells)
    private static final String MENU_SEPARATOR =
        "|---------------------------------------------------------------------------|";
    private static final String INTO_DATA_BASE = " into database...";
    private static final String COMPLETED = "Completed!";
    private static final String LOADING_USERS_FROM = "Loading users from ";
    private static final String INSERT_PARK_ID = "Insert park ID:";
    private static final String QUAL_DESEJA_LEVAR_OU_ZERO =
        "Qual deseja levar? se nao deseja levar nenhuma prima 0";
    private static final String BIKE_DESBLOQUEADA = "bike desbloqueada";
    // Controllers
    private static BaseController baseController = new BaseController();
    private static ActionsController actionsController = new ActionsController();
    private static RequestBikeController requestBikeController = new RequestBikeController();
    private static LeaveBikeController leaveBikeController = new LeaveBikeController();
    private static ChargingController chargingController = new ChargingController();
    private static GraphController graphController = new GraphController();
    private static InvoicesController invoicesController = new InvoicesController();
    // CSV Adapter
    private static CSVAdapter csvAdapter = new CSVAdapter();
    // Scanner
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Mostra o menu e espera pela escolha do user.
     */
    public static void showMenu() {
        Logger.log(MENU_SEPARATOR);
        Logger.log("|                B I K E   S H A R I N G   I S   C A R I N G                |");
        Logger.log(MENU_SEPARATOR);
        Logger.log("| Logged-in User: " + AuthManager.getInstance().getLoggedInUser());
        Logger.log("| isAdmin: " + AuthManager.getInstance().isAdmin());
        Logger.log(MENU_SEPARATOR);
        Logger.log("| 1: List All Users");
        Logger.log("| 2: List All Parks");
        Logger.log("| 3: List All Touristic Points");
        Logger.log("| 4: List All Routes");
        Logger.log("| 5: List All Bikes");
        Logger.log("| 6: List All Rides");
        Logger.log("| 7: List All Unfinished Rides");
        Logger.log("| 8: List All Receipts");
        Logger.log("| 9: List All Invoices");
        Logger.log(MENU_SEPARATOR);
        Logger.log("| 20: Login");
        Logger.log("| 21: Register User");
        Logger.log(MENU_SEPARATOR);
        Logger.log("| 40: Request Bike (Unlock Bike)");
        Logger.log("| 41: Leave Bike (Lock Bike)");
        Logger.log("| 42: List the Nearest Parks");
        Logger.log("| 43: Check How Far is Another Park");
        Logger.log("| 44: Calculate the Amount of Electrical Energy Required to Travel "
            + "|     from one Park to Another");
        Logger.log("| 45: Check Free Spaces of a Park");
        Logger.log("| 46: Calculate the Amount of Calories Burnt Between 2 Parks");
        Logger.log("| 47: Get Available Bikes of a Specific Park");
        Logger.log("| 48: Generate Park Report");
        Logger.log("| 49: Suggest Electrical Bikes With an Higher Battery Charge Level on a "
            + "|     Specific Park");
        Logger.log("| 50: Suggest Bikes with Enough Range Plus 10% to Get to a Specific "
            + "|     Destination Park");
        Logger.log("| 51: Get the Most Energetically Efficient Route Between two Parks");
        Logger.log("| 52: Get the Shortest Route Between two Parks");
        Logger.log("| 53: Get the Shortest Route Between Two Parks that Goes by at Least a "
            + "|     Certain Number of Interest Points, which can be Specified "
            + "|     by the User");
        Logger.log("| 54: Charge Bike for a Specific Time (in hours)");
        Logger.log("| 55: Charge the Battery Pack of a Bike (weight of that bike may change");
        Logger.log(MENU_SEPARATOR);
        Logger.log("| 70: Issue All Invoices");
        Logger.log("| 71: Make User Pay All Invoices and Get a Receipt (Points can be Used)");
        Logger.log(MENU_SEPARATOR);
        Logger.log("| 72: Send test e-mail");
        Logger.log(MENU_SEPARATOR);
        Logger.log("| 94: Load Users from CSV File");
        Logger.log("| 95: Load Parks from CSV File");
        Logger.log("| 96: Load Touristic Points from CSV File");
        Logger.log("| 97: Load Routes from CSV File");
        Logger.log("| 98: Load Bikes from CSV File");
        Logger.log("| 99: Load all CSV files");
        Logger.log(MENU_SEPARATOR);
        Logger.log("| 0: Sair");
        Logger.log(MENU_SEPARATOR);
        Logger.log("| Escolha uma opção para continuar...");
        Logger.log(MENU_SEPARATOR);

        int userChoice = scanner.nextInt();
        Logger.log("Loading...");

        switch (userChoice) {
            case 1:
                // 1: List All Users
                menuListAllUsers();
                pausaShowMenu();
                break;
            case 2:
                // 2: List All Parks
                menuListAllParks();
                pausaShowMenu();
                break;
            case 3:
                // 3: List All Touristic Points
                menuListAllTouristicPoints();
                pausaShowMenu();
                break;
            case 4:
                // 4: List All Routes
                menuListAllRoutes();
                pausaShowMenu();
                break;
            case 5:
                // 5: List All Bikes
                menuListAllBikes();
                pausaShowMenu();
                break;
            case 6:
                // 6: List All Rides
                menuListAllRides();
                pausaShowMenu();
                break;
            case 7:
                // 7: List All Unfinished Rides
                menuListAllUnfinishedRides();
                pausaShowMenu();
                break;
            case 8:
                // 8: List All Receipts
                menuListAllReceipts();
                pausaShowMenu();
                break;
            case 9:
                // 9: List All Invoices
                menuListAllInvoices();
                pausaShowMenu();
                break;
            case 20:
                // 20: Login
                menuLogin();
                pausaShowMenu();
                break;
            case 21:
                // 21: Register User
                menuRegisterUser();
                pausaShowMenu();
                break;
            case 40:
                // 40: Request Bike (Unlock Bike)
                menuRequestBike();
                pausaShowMenu();
                break;
            case 41:
                // 41: Leave Bike (Lock Bike)
                menuLeaveBike();
                pausaShowMenu();
                break;
            case 42:
                // 42: List the Nearest Parks
                menuListNearestParks();
                pausaShowMenu();
                break;
            case 43:
                // 43: Check How Far is Another Park
                menuCheckHowFarIsAnotherPark();
                pausaShowMenu();
                break;
            case 44:
                // 44: Calculate the Amount of Electrical Energy Required to Travel from one Park to Another

                pausaShowMenu();
                break;
            case 45:
                // 45: Check Free Spaces of a Park
                menuCheckFreeSpacesOfAPark();
                pausaShowMenu();
                break;
            case 46:
                // 46: Calculate the Amount of Calories Burnt Between 2 Parks
                Logger.log("FALTA IMPLEMENTAR!");
                pausaShowMenu();
                break;
            case 47:
                // 47: Get Available Bikes of a Specific Park
                menuGetAvaiableBikesOfASpecificPark();
                pausaShowMenu();
                break;
            case 48:
                // 48: Generate Park Report
                menuGenerateParkReport();
                pausaShowMenu();
                break;
            case 49:
                // 49: Suggest Electrical Bikes With an Higher Battery Charge Level on a Specific Park

            case 50:
                // 50: Suggest Bikes with Enough Range Plus 10% to Get to a Specific Destination Park


            case 51:
                // 51: Get the Most Energetically Efficient Route Between two Parks


            case 52:
                // 52: Get the Shortest Route Between two Parks


            case 53:
                // 53: Get the Shortest Route Between Two Parks that Goes by at Least a Certain Number of Interest Points, which can be Specified by the User


            case 54:
                // 54: Charge Bike for a Specific Time (in hours)


            case 55:
                // 55: Charge the Battery Pack of a Bike (weight of that bike may change

            case 70:
                // 70: Issue All Invoices

            case 71:
                // 71: Make User Pay All Invoices and Get a Receipt (Points can be Used)
                break;
            case 72:
                //72: send test e-mail
                menuSendTestEmail();
                pausaShowMenu();
                break;
            case 94:
                // 94: Load Users from CSV File
                menuloadUsersFromCSV();
                pausaShowMenu();
                break;
            case 95:
                // 95: Load Parks from CSV File
                menuloadParksFromCSV();
                pausaShowMenu();
                break;
            case 96:
                // 96: Load Touristic Points from CSV File
                menuloadTouristicPointsFromCSV();
                pausaShowMenu();
                break;
            case 97:
                // 97: Load Routes from CSV File
                menuloadRoutesFromCSV();
                pausaShowMenu();
                break;
            case 98:
                // 98: Load Bikes from CSV File
                menuloadBikesFromCSV();
                pausaShowMenu();
                break;
            case 99:
                // 99: Load All CSV Files
                menuloadAllCSVFiles();
                pausaShowMenu();
                break;
            case 0:
                break;
            default:
                showMenu();
        }

    }

    /**
     * Pausa o menu até existir user input.
     */
    private static void pausarMenu() {
        Scanner scanner = new Scanner(System.in);
        Logger.log("Para continuar digite QUALQUER TECLA...");
        scanner.nextLine();
    }

    /**
     * This method calls pausarMenu e showMenu in the same call. (Code smells)
     */
    private static void pausaShowMenu() {
        pausarMenu();
        showMenu();
    }

    // Menu Options
    //---------------------------------------------------------------------------

    private static void menuListAllBikes() {
        for (Bike bike : baseController.getAllBikes())
            Logger.log(bike);
    }

    private static void menuListAllParks() {
        for (Park park : baseController.getAllParks())
            Logger.log(park);
    }

    private static void menuListAllRides() {
        for (Ride ride : baseController.getAllRides())
            Logger.log(ride);
    }

    private static void menuListAllUsers() {
        for (User user : baseController.getAllUsers())
            Logger.log(user);
    }

    private static void menuListAllUnfinishedRides() {
        for (Ride ride : baseController.getAllUnfinishedRides())
            Logger.log(ride);
    }

    private static void menuListAllTouristicPoints() {
        for (TouristicPoint touristicPoint : baseController.getAllTouristicPoints())
            Logger.log(touristicPoint);
    }

    private static void menuListAllInvoices() {
        for (Invoice invoice : baseController.getAllInvoices())
            Logger.log(invoice);
    }

    private static void menuListAllReceipts() {
        for (Receipt receipt : baseController.getAllReceipts())
            Logger.log(receipt);
    }

    private static void menuListAllRoutes() {
        for (Route route : baseController.getAllRoutes())
            Logger.log(route);
    }

    private static void menuRegisterUser() {
        try {
            User user = new User();
            Logger.log("New email:");
            user.setEmail(scanner.next());
            Logger.log("Set your password:");
            user.setPassword(scanner.next());
            Logger.log("Set your height:");
            user.setHeight(scanner.nextDouble());
            Logger.log("Set your weight:");
            user.setWeight(scanner.nextDouble());
            Logger.log("Is admin? (1 true /0 false):");
            user.setIsAdmin(Boolean.parseBoolean(scanner.next()));
            Logger.log("Cartão de crédito:");
            user.setCreditCardNumber(scanner.next());
            Integer n = baseController.addUser(user);
            if (n != null) {
                Logger.log("New ID: " + n);
            } else {
                Logger.log("Error adding user!");
            }
        } catch (Exception ex) {
            Logger.log(ex);
            Logger.log(ex.getMessage());
        }
    }

    private static void menuLogin() {
        Logger.log("Email:");
        String emailLogin = scanner.next();
        Logger.log("Password:");
        String passLogin = scanner.next();
        boolean succLogin = AuthManager.getInstance().login(emailLogin, passLogin);
        if (succLogin) {
            Logger.log("Success!");
        } else {
            Logger.log("Wrong password!");
        }
    }

    private static void menuRequestBike() {
        //ACEITANDO INPUT DO USER
        //---------------------------------------
        Logger.log("Insira o seu idUser");
        Integer idUser10 = scanner.nextInt();

        Logger.log("Em que parque se encontra?");
        int idParkStart10 = scanner.nextInt();

        Logger.log("Insira o park destino, se nao desejar inserir o destino insira 0");
        Integer idParkFinish10 = scanner.nextInt();
        if (idParkFinish10 == 0) {
            idParkFinish10 = null;
        }

        Logger.log("Em que tipo de bike pretende viajar? Electrical, Mountain ou Road?");
        String bikeType10 = scanner.nextLine();

        Logger.log("Insira o vento, se nao desejar toma-lo em conta insira 0");


        //FLUXO DE EXECUCAO PARA BIKE ELETRICA
        //--------------------------------------------------------------------
        if ("electrical".equalsIgnoreCase(bikeType10)) {
            //FLUXO DE EXECUCAO PARA BIKE ELETRICA SEM BIKE DESTINO
            //-------------------------------------------------------------------
            if (idParkFinish10 == null) {
                Logger.log("Calculando a bike com mais bateria");
                Bike bikeElec10 = actionsController.getBikeWithMostChargeFromPark(idParkStart10);
                Logger.log(bikeElec10);
                Logger.log("Deseja levar esta bike? Caso queira digite y, qualquer outra tecla para nao");
                String choice10 = scanner.nextLine();

                if ("y".equalsIgnoreCase(choice10)) {
                    if (requestBikeController.requestBike(idUser10, bikeElec10.getIdBike(), idParkStart10)) {
                        Logger.log(BIKE_DESBLOQUEADA);
                    }
                } else {
                    List<Bike> elecBikes10 = actionsController.getAllElectricalActiveBikesFromPark(idParkStart10);
                    Logger.log("As outras bikes disponiveis sao:");
                    for (Bike elecBike10 : elecBikes10) {
                        Logger.log(elecBike10);
                    }
                    Logger.log(QUAL_DESEJA_LEVAR_OU_ZERO);
                    Integer idBikeElec10 = scanner.nextInt();
                    if (idBikeElec10 == 0) {
                        return;
                    } else {
                        if (requestBikeController.requestBike(idUser10, idBikeElec10, idParkStart10)) {
                            Logger.log(BIKE_DESBLOQUEADA);
                        }
                    }
                }
            }

            // fazer : permitir escolha de tipo de ROTA
            // fazer : se queres ter a escolha da bike, também tens de ter a rota
            // fazer : se tentares apagar esta alteração, faço rebase de todas as alterações feitas por cima e desenmerdas-te TU

            //FLUXO DE EXECUCAO PARA BIKE ELECTRICA COM PARQUE DESTINO
            //-------------------------------------------------------------------------------------------------
            else {
                Logger.log("Calculando a bike com energia suficiente");

                List<Location> locList = Arrays.asList(baseController.getParkById(idParkStart10), baseController.getParkById(idParkFinish10));
                Bike bikeElec10 = actionsController.getBikeWith10PlusEnergyFromPark(idParkStart10, actionsController.calculatePathEnergy(idUser10, locList));
                Logger.log(bikeElec10);
                Logger.log("Deseja levar esta bike? Caso queira digite y, qualquer outra tecla para nao");
                String choice10 = scanner.nextLine();

                if ("y".equalsIgnoreCase(choice10)) {
                    if (requestBikeController.requestBike(idUser10, bikeElec10.getIdBike(), idParkStart10)) {
                        Logger.log(BIKE_DESBLOQUEADA);
                    }
                } else {
                    List<Bike> elecBikes10 = actionsController.getAllElectricalActiveBikesFromPark(idParkStart10);
                    Logger.log("As outras bikes disponiveis sao:");
                    for (Bike elecBike10 : elecBikes10) {
                        Logger.log(elecBike10);
                    }
                    Logger.log(QUAL_DESEJA_LEVAR_OU_ZERO);
                    Integer idBikeElec10 = scanner.nextInt();
                    if (idBikeElec10 == 0) {
                        return;
                    } else {
                        if (requestBikeController.requestBike(idUser10, idBikeElec10, idParkStart10)) {
                            Logger.log(BIKE_DESBLOQUEADA);
                        }
                    }
                }
            }
        }
        //FLUXO DE EXECUCAO PARA BIKE MOUNTAIN OU ROAD
        //----------------------------------------------------------------------------
        else if ("road".equalsIgnoreCase(bikeType10) || "mountain".equalsIgnoreCase(bikeType10)) {
            //FLUXO DE EXECUCAO PARA MOUNTAIN OU ROAD SEM PARK FINAL
            //-------------------------------------------------------------------------
            if (idParkFinish10 == null) {
                List<Bike> mountainBikes10 = actionsController.getAllMountainActiveBikesFromPark(idParkStart10);
                List<Bike> roadBikes10 = actionsController.getAllRoadActiveBikesFromPark(idParkStart10);
                Logger.log("As bikes disponiveis sao:");
                for (Bike elecBike10 : mountainBikes10) {
                    Logger.log(elecBike10);
                }
                for (Bike elecBike10 : roadBikes10) {
                    Logger.log(elecBike10);
                }
                Logger.log(QUAL_DESEJA_LEVAR_OU_ZERO);
                Integer idBikeElec10 = scanner.nextInt();
                if (idBikeElec10 == 0) {
                    return;
                } else {
                    if (requestBikeController.requestBike(idUser10, idBikeElec10, idParkStart10)) {
                        Logger.log(BIKE_DESBLOQUEADA);
                    }
                }
            }
            //FLUXO DE EXECUACAO PARA MOUNTAIN OU ROAD COM PARK FINAL
            //-------------------------------------------------------------------------------
            else {

                List<Bike> mountainBikes10 = actionsController.getAllMountainActiveBikesFromPark(idParkStart10);
                List<Bike> roadBikes10 = actionsController.getAllRoadActiveBikesFromPark(idParkStart10);
                Logger.log("As bikes disponiveis sao:");
                for (Bike elecBike10 : mountainBikes10) {
                    Logger.log(elecBike10);
                }
                for (Bike elecBike10 : roadBikes10) {
                    Logger.log(elecBike10);
                }
                Logger.log(QUAL_DESEJA_LEVAR_OU_ZERO);
                Integer idBikeElec10 = scanner.nextInt();
                if (idBikeElec10 == null) {
                    return;
                } else {
                    if (requestBikeController.requestBike(idUser10, idBikeElec10, idParkStart10)) {
                        Logger.log(BIKE_DESBLOQUEADA);
                    }
                }
            }
        } else {
            //caso biketype esteja mal
            //---------------------------------------------------------
            Logger.log("Tipo de bike nao reconhecida");
        }
    }

    private static void menuLeaveBike() {
        Logger.log("User ID:");
        Integer userId11 = scanner.nextInt();
        Logger.log("Bike ID:");
        Integer bikeId11 = scanner.nextInt();
        Logger.log("Park ID:");
        Integer parkId11 = scanner.nextInt();
        leaveBikeController.leaveBike(userId11, bikeId11, parkId11);
    }

    private static void menuListNearestParks() {
        Logger.log("Insert latitude:");
        Double latitude = scanner.nextDouble();
        Logger.log("Insert longitude:");
        Double longitude = scanner.nextDouble();
        Logger.log("Insert altitude:");
        Double height = scanner.nextDouble();
        List<Park> parkList21 = actionsController.getNearestParks(latitude, longitude, height);
        for (Park park_21 : parkList21) {
            Logger.log(park_21);
        }
    }

    private static void menuCheckHowFarIsAnotherPark() {
        Logger.log("Insert Geo-Latitude (in degrees): ");
        Double userLat = Double.parseDouble(scanner.next());
        Logger.log("Insert Geo-Longitude (in degrees): ");
        Double userLong = Double.parseDouble(scanner.next());
        Logger.log("Insert altitude (in meters): ");
        Double userHeight = Double.parseDouble(scanner.next());
        Logger.log(INSERT_PARK_ID);
        Integer idPark = Integer.parseInt(scanner.next());
        Logger.log("Distance is: ");
        Logger.log(actionsController.getDistanceFromUserToPark(userLat, userLong, userHeight, idPark));
    }

    private static void menuCheckFreeSpacesOfAPark() {
        Logger.log(INSERT_PARK_ID);
        String parkID = scanner.next();
        scanner.nextLine();
        Map<BikeType, Integer> freeSpaces = actionsController.getFreeSpacesOfPark(Integer.parseInt(parkID));
        for (Map.Entry<BikeType, Integer> entry : freeSpaces.entrySet()) {
            Logger.log(entry.getKey() + " - " + entry.getValue() + " free spaces");
        }
    }

    private static void menuGetAvaiableBikesOfASpecificPark() {
        Logger.log(INSERT_PARK_ID);
        String parkID28 = scanner.next();
        Map<BikeType, List<Bike>> availableBikes = actionsController.getAvailableBikesOfPark(Integer.parseInt(parkID28));
        for (Map.Entry<BikeType, List<Bike>> entry : availableBikes.entrySet()) {
            Logger.log(entry.getKey() + " - " + entry.getValue().size() + " available bikes");
        }
    }

    private static void menuGenerateParkReport() {
        Logger.log(INSERT_PARK_ID);
        Integer parkID29 = scanner.nextInt();
        List<Pair<Bike, Double>> lstReport = actionsController.getParkReport(parkID29);

        for (Pair<Bike, Double> par : lstReport) {
            Logger.log("Bike:" + par.getKey());
            Logger.log("Time in s:" + (par.getValue() * 3600) + "\n");
        }
    }


    private static void menuloadUsersFromCSV() {
        String filePath = "./src/main/resources/csv/users.csv";
        Logger.log(LOADING_USERS_FROM + filePath + INTO_DATA_BASE);
        csvAdapter.loadUsersFromCSV(filePath, ";");
        Logger.log(COMPLETED);
    }

    private static void menuloadParksFromCSV() {
        String filePath = "./src/main/resources/csv/parks.csv";
        Logger.log(LOADING_USERS_FROM + filePath + INTO_DATA_BASE);
        csvAdapter.loadParksFromCSV(filePath, ";");
        Logger.log(COMPLETED);
    }

    private static void menuloadTouristicPointsFromCSV() {
        String filePath = "./src/main/resources/csv/pois.csv";
        Logger.log(LOADING_USERS_FROM + filePath + INTO_DATA_BASE);
        csvAdapter.loadTouristicPointsFromCSV(filePath, ";");
        Logger.log(COMPLETED);
    }

    private static void menuloadRoutesFromCSV() {
        String filePath = "./src/main/resources/csv/paths.csv";
        Logger.log(LOADING_USERS_FROM + filePath + INTO_DATA_BASE);
        csvAdapter.loadRoutesFromCSV(filePath, ";");
        Logger.log(COMPLETED);
    }

    private static void menuloadBikesFromCSV() {
        String filePath = "./src/main/resources/csv/bicycles.csv";
        Logger.log(LOADING_USERS_FROM + filePath + INTO_DATA_BASE);
        csvAdapter.loadBikesFromCSV(filePath, ";");
        Logger.log(COMPLETED);
    }

    private static void menuloadAllCSVFiles() {
        menuloadUsersFromCSV();
        menuloadParksFromCSV();
        menuloadTouristicPointsFromCSV();
        menuloadRoutesFromCSV();
        menuloadBikesFromCSV();
    }

    private static void menuSendTestEmail(){
        User testUser = new User();
        testUser.setPassword("testing pass");
        testUser.setCreditCardNumber("123456");
        testUser.setUsername("LAPR TEST USER");
        Scanner mail = new Scanner(System.in);
        Logger.log("Insira e-mail de teste:");
        testUser.setEmail(mail.nextLine());
        DataUtils du = new DataUtils();
        Logger.log("Insira o assunto:");
        String subj = mail.nextLine();
        Logger.log("Insira o corpo do e-mail:");
        String body= mail.nextLine();
        if(du.sendEmailNotification(testUser,body,subj)){
            Logger.log("Enviado!");
        }
        else{
            Logger.log("Não enviado, talvez não tenha ligação à Internet.");
        }
    }

}
