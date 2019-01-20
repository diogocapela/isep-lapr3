package lapr.project.assessment;

import javafx.util.Pair;
import lapr.project.controller.*;
import lapr.project.data.CSVAdapter;
import lapr.project.data.OutputAdapter;
import lapr.project.model.*;
import lapr.project.utils.Logger;
import lapr.project.utils.Utils;

import java.io.*;
import java.util.*;

public class Facade implements Serviceable {

    // Controllers
    private BaseController baseController = new BaseController();
    private ActionsController actionsController = new ActionsController();
    private ChargingController chargingController = new ChargingController();
    private GraphController graphController = new GraphController();
    private InvoicesController invoicesController = new InvoicesController();
    private LeaveBikeController leaveBikeController = new LeaveBikeController();
    private RequestBikeController requestBikeController = new RequestBikeController();

    // CSV Adapters
    private CSVAdapter csvAdapter = new CSVAdapter();
    private OutputAdapter outputAdapter = new OutputAdapter();

    @Override
    public int addBicycles(String s) {
        return csvAdapter.loadBikesFromCSV(s, ";");
    }

    @Override
    public int addParks(String s) {
        return csvAdapter.loadParksFromCSV(s, ";");
    }

    @Override
    public int addPOIs(String s) {
        return csvAdapter.loadTouristicPointsFromCSV(s, ";");
    }

    @Override
    public int addUsers(String s) {
        return csvAdapter.loadUsersFromCSV(s, ";");
    }

    @Override
    public int addPaths(String s) {
        return csvAdapter.loadRoutesFromCSV(s, ";");
    }

    @Override
    public int getNumberOfBicyclesAtPark(double v, double v1, String s) {
        Park park = actionsController.getParkByCoordinates(v, v1);
        if (park == null) {
            return 0;
        }
        List<Bike> allBikes = baseController.getAllBikes();
        ArrayList<Bike> bikesAtPark = new ArrayList<>();
        for (Bike bike : allBikes) {
            if (bike.getIdPark().equals(park.getIdPark())) {
                bikesAtPark.add(bike);
            }
        }
        bikesAtPark.sort(Comparator.comparing(Bike::getDescription));
        outputAdapter.writeBikesToFile(s, bikesAtPark);
        return bikesAtPark.size();
    }

    @Override
    public int getFreeSlotsAtPArk(double v, double v1, String s) {
        Park park = actionsController.getParkByCoordinates(v, v1);
        if (park == null) {
            return 0;
        }
        User user = baseController.getUserByEmail(s);
        if (user == null) {
            return 0;
        }
        Ride ride = leaveBikeController.getUnfinishedRideOfUser(user.getIdUser());
        if (ride == null) {
            return 0;
        }
        Bike bike = baseController.getBikeById(ride.getIdBike());
        Map<BikeType, Integer> freeSpaces = actionsController.getFreeSpacesOfPark(park.getIdPark());
        return freeSpaces.get(bike.getType());
    }

    @Override
    public void getNearestParks(double v, double v1, String s) {
        BufferedWriter writer;
        try (FileWriter fw = new FileWriter(s)) {
            writer = new BufferedWriter(fw);
            for (Park loc : actionsController.getNearestParks(v, v1, 0.0)) {
                int distance = (int) Utils.calculateFlatDistanceBetweenTwoCoordinates(v, v1, loc.getGeoLatitude(), loc.getGeoLongitude());
                writer.write(String.format("%.6f,%.6f,%d\n", loc.getGeoLatitude(), loc.getGeoLongitude(), distance));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int distanceTo(double v, double v1, double v2, double v3) {
        Park parkA = actionsController.getParkByCoordinates(v, v1);
        if (parkA == null) {
            return 0;
        }
        Park parkB = actionsController.getParkByCoordinates(v2, v3);
        if (parkB == null) {
            return 0;
        }
        return actionsController.getDistanceFromUserToPark(v, v1, parkA.getGeoAltitude(), parkB.getIdPark()).intValue();
    }

    @Override
    public long unlockBicycle(String s, String s1) {
        Bike thisBike = null;
        thisBike = actionsController.getBikeByDescription(s1);
        if (thisBike == null) {
            return 10;
        }
        if (thisBike.getIdPark() == null) {
            return 20;
        }
        User user = baseController.getUserByUsername(s);
        if (user == null) {
            return 30;
        }
        Ride ride = requestBikeController.unlockBike(user.getIdUser(), thisBike.getIdBike(), thisBike.getIdPark());
        if (ride == null) {
            return 40;
        }
        Date date = Utils.convertStringToDate(ride.getTimestampStart());
        if (date == null) {
            return 50;
        }
        return date.getTime();
    }

    @Override
    public long lockBicycle(String s, double v, double v1) {
        Park p = actionsController.getParkByCoordinates(v, v1);
        Bike b = actionsController.getBikeByDescription(s);
        if (p != null && b != null) {
            List<Ride> unfinishedRides = actionsController.getAllUnfinishedRides();
            Integer idUser = null;
            for (Ride r_f : unfinishedRides) {
                if (Objects.equals(r_f.getIdBike(), b.getIdBike())) {
                    idUser = r_f.getIdUser();
                }
            }
            if (idUser != null) {
                if (leaveBikeController.leaveBike(idUser, b.getIdBike(), p.getIdPark())) {
                    return new Date().getTime();
                }
            }
        }
        return -1;
    }

    @Override
    public double getUserCurrentDebt(String s, String s1) {
        List<String> output = new LinkedList<>();

        double result = invoicesController.getUserDebt(s, output);

        outputAdapter.writeDataToFile(s1, output);

        return result;
    }

    @Override
    public double getInvoiceForMonth(int i, String s, String s1) {
        List<String> output = new LinkedList<>();

        double result = invoicesController.generateInvoiceForUser(i, s, output);

        outputAdapter.writeDataToFile(s1, output);

        return result;
    }

    @Override
    public double getUserCurrentPoints(String s, String s1) {
        User currentUser = baseController.getUserByUsername(s);
        if (currentUser == null) {
            return 0.0;
        }
        List<Ride> allRides = baseController.getAllRides();
        List<Ride> allRidesOfCurrentUser = new ArrayList<>();
        for (Ride ride : allRides) {
            if (Objects.equals(currentUser.getIdUser(), ride.getIdUser()))
                allRidesOfCurrentUser.add(ride);
        }
        List<String> lineData = new ArrayList<>();
        for (Ride ride : allRidesOfCurrentUser) {
            Bike bike = baseController.getBikeById(ride.getIdBike());
            Park parkStart = baseController.getParkById(ride.getIdStartPark());
            Park parkEnd = baseController.getParkById(ride.getIdEndPark());
            Double altitudeDifference = Math.abs(parkStart.getGeoAltitude() - parkEnd.getGeoAltitude());
            int pointsToCredit = 0;
            if (altitudeDifference > 50) {
                pointsToCredit = 15;
            } else if (altitudeDifference > 25) {
                pointsToCredit = 5;
            }
            lineData.add(bike.getDescription() + ";" + ride.getTimestampStart() + ";" + ride.getTimestampFinish() + ";" + parkStart.getGeoLatitude() + ";" + parkStart.getGeoLongitude() + ";" + parkStart.getGeoAltitude() + ";" + parkEnd.getGeoLatitude() + ";" + parkEnd.getGeoLongitude() + ";" + parkEnd.getGeoAltitude() + ";" + altitudeDifference + ";" + pointsToCredit);
        }
        outputAdapter.writeDataToFile(s1, lineData);
        return currentUser.getCreditPoints();
    }

    @Override
    public long unlockAnyBicycleAtPark(double v, double v1, String s, String s1) {
        Park parkA = actionsController.getParkByCoordinates(v, v1);
        if (parkA == null) {
            return 0;
        }
        User user = baseController.getUserByUsername(s);
        if (user == null) {
            return 0;
        }
        List<Bike> bikes = actionsController.getAllBikesFromPark(parkA.getIdPark());
        if (bikes.isEmpty()) {
            return 0;
        }
        Bike bike = bikes.get(0);
        Ride ride = requestBikeController.unlockBike(user.getIdUser(), bike.getIdBike(), bike.getIdPark());
        if (ride == null) {
            return 0;
        }
        Date date = Utils.convertStringToDate(ride.getTimestampStart());
        if (date == null) {
            return 0;
        }
        Long timeMiliseconds = date.getTime();
        List<Bike> bikesAtPark = new ArrayList<>();
        bikesAtPark.add(bike);
        outputAdapter.writeBikesToFile(s1, bikesAtPark);
        return timeMiliseconds;
    }

    @Override
    public long unlockAnyElectricBicycleAtPark(double v, double v1, String s, String s1) {
        Park parkA = actionsController.getParkByCoordinates(v, v1);
        if (parkA == null) {
            return 0;
        }
        User user = baseController.getUserByUsername(s);
        if (user == null) {
            return 0;
        }
        List<Bike> bikes = actionsController.getAllElectricalActiveBikesFromPark(parkA.getIdPark());

        if (bikes.isEmpty()) {
            return 0;
        }
        Bike bike = bikes.get(0);
        Ride ride = requestBikeController.unlockBike(user.getIdUser(), bike.getIdBike(), bike.getIdPark());
        if (ride == null) {
            return 0;
        }
        Date date = Utils.convertStringToDate(ride.getTimestampStart());
        if (date == null) {
            return 0;
        }
        Long timeMiliseconds = date.getTime();
        List<Bike> bikesAtPark = new ArrayList<>();
        bikesAtPark.add(bike);
        outputAdapter.writeBikesToFile(s1, bikesAtPark);
        return timeMiliseconds;
    }

    @Override
    public double calculateElectricalEnergyToTravelFromOneLocationToAnother(double v, double v1, double v2, double v3, String s) {
        Location locationA;
        Location locationB;
        locationA = actionsController.getLocationByCoordinates(v, v1);
        if (locationA == null) {
            return 0.0;
        }
        locationB = actionsController.getLocationByCoordinates(v2, v3);
        if (locationB == null) {
            return 0.0;
        }
        User user = actionsController.getUserByUsername(s);
        if (user == null)
            return 0;

        return actionsController.calculateElectricalEnergyToTravelBetweenLocations(user.getIdUser(), null, locationA, locationB, null) / 1000.0;
    }

    @Override
    public int suggestElectricalBicyclesToGoFromOneParkToAnother(double v, double v1, double v2, double v3, String s, String s1) {
        Park parkA = actionsController.getParkByCoordinates(v, v1);
        if (parkA == null) {
            Logger.log("park a null");
            return 0;
        }

        Park parkB = actionsController.getParkByCoordinates(v2, v3);
        if (parkB == null) {
            Logger.log("park b null");
            return 0;
        }

        User user = baseController.getUserByUsername(s);
        if (user == null) {
            Logger.log("user null");
            return 0;
        }

        double energy = actionsController.calculateElectricalEnergyToTravelBetweenLocations(user.getIdUser(), null, parkA, parkB, null) / 1000.0;
        Logger.log("energy " + energy);
        Bike bike = actionsController.getBikeWith10PlusEnergyFromPark(parkA.getIdPark(), energy);
        if (bike == null) {
            Logger.log("bike null");
            return 0;
        }
        ArrayList<Bike> bikes = new ArrayList<>();
        bikes.add(bike);
        outputAdapter.writeBikesToFile(s1, bikes);
        return 1;
    }

    @Override
    public long forHowLongWasTheBicycleUnlocked(String s) {
        if ("".equals(s))
            return 0;
        Bike bike = null;
        Long totalTime = 0L;
        List<Bike> bikes = baseController.getAllBikes();
        for (Bike b : bikes) {
            if (b.getDescription().equalsIgnoreCase(s)) {
                bike = b;
                break;
            }
        }
        if (bike == null)
            return 0;
        List<Ride> rides = baseController.getAllRides();
        rides.removeAll(baseController.getAllUnfinishedRides());
        if (rides.isEmpty())
            return 0;
        for (Ride r : rides) {
            if (r.getIdBike().equals(bike.getIdBike())) {
                Long tempTime = Utils.getTimeDifferenceInSeconds(
                    r.getTimestampStart(),
                    r.getTimestampFinish());
                if (tempTime != null)
                    totalTime += tempTime;
            }
        }

        return totalTime;
    }

    @Override
    public long getParkChargingReportForPark(double v, double v1, String s) {
        //v=park lat
        //v1=park long
        //s=outpath
        //csv format:bicycle description;actual battery capacity;time to finish charge in seconds
        List<String> fileLines = new ArrayList<>();
        Park p = actionsController.getParkByCoordinates(v, v1);
        if (p == null) {
            return -1;
        }
        List<Pair<Bike, Double>> report = actionsController.getParkReport(p.getIdPark());
        for (Pair<Bike, Double> par : report) {
            fileLines.add(par.getKey().getDescription() + ";" +
                par.getKey().getCurrentBatteryLevel() + ";" +
                String.format("%.0f", par.getValue() * 3600));
        }
        outputAdapter.writeDataToFile(s, fileLines);
        return 0;
    }

    @Override
    public long shortestRouteBetweenTwoParks(double v, double v1, double v2, double v3, String s) {
        Park parkA = actionsController.getParkByCoordinates(v, v1);
        if (parkA == null) {
            Logger.log("park A null");
            return 0;
        }

        Park parkB = actionsController.getParkByCoordinates(v2, v3);
        if (parkB == null) {
            Logger.log("park B null");
            return 0;
        }

        List<Location> result = graphController.getShortestRouteBetweenTwoParks(parkA.getIdPark(), parkB.getIdPark());

        List<String> pathList = new LinkedList<>();
        pathList.add(String.format("Path %03d", 1));
        double dist = actionsController.calculatePathDistance(result);
        pathList.add(String.format("total_distance: %.0f", dist));
        pathList.add(String.format("total_energy: %.2f", actionsController.calculatePathEnergy(null, result) / 1000));
        pathList.add(String.format("elevation: %.0f", parkA.getGeoAltitude() - parkB.getGeoAltitude()));
        for (Location loc : result) {
            pathList.add(loc.getGeoLatitude() + ";" + loc.getGeoLongitude());
        }

        outputAdapter.writeDataToFile(s, pathList);

        return Math.round(dist);
    }

    @Override
    public long mostEnergyEfficientRouteBetweenTwoParks(double v, double v1, double v2, double v3, String s, String s1, String s2) {
        Park parkA = actionsController.getParkByCoordinates(v, v1);
        if (parkA == null) {
            Logger.log("park A null");
            return 0;
        }

        Park parkB = actionsController.getParkByCoordinates(v2, v3);
        if (parkB == null) {
            Logger.log("park B null");
            return 0;
        }

        User user = baseController.getUserByUsername(s1);
        if (user == null) {
            Logger.log("user null");
            return 0;
        }

        List<Location> result = graphController.getMostEnergyEfficientRouteBetweenTwoParks(parkA.getIdPark(), parkB.getIdPark(), user.getIdUser(), null);

        List<String> pathList = new LinkedList<>();
        pathList.add(String.format("Path %03d", 1));
        double dist = actionsController.calculatePathDistance(result);
        pathList.add(String.format("total_distance: %.0f", dist));
        pathList.add(String.format("total_energy: %.2f", actionsController.calculatePathEnergy(user.getIdUser(), result) / 1000 / ("electric".equalsIgnoreCase(s) ? 2 : 1)));
        pathList.add(String.format("elevation: %.0f", parkA.getGeoAltitude() - parkB.getGeoAltitude()));
        for (Location loc : result) {
            pathList.add(loc.getGeoLatitude() + ";" + loc.getGeoLongitude());
        }

        outputAdapter.writeDataToFile(s2, pathList);

        return Math.round(dist);
    }

    @Override
    public long shortestRouteBetweenTwoParksForGivenPOIs(double v, double v1, double v2, double v3, String s, String s1) {
        Park parkA = actionsController.getParkByCoordinates(v, v1);
        if (parkA == null) {
            Logger.log("park A null");
            return 0;
        }

        Park parkB = actionsController.getParkByCoordinates(v2, v3);
        if (parkB == null) {
            Logger.log("park B null");
            return 0;
        }

        ArrayList<Location> pois = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(s))) {
            br.lines().forEach(line -> {
                if (!line.startsWith("#")) {
                    String[] lineData = line.trim().split(";");
                    if (lineData.length > 1) {
                        /* Get the data from the CSV file
                        ======================================================== */
                        TouristicPoint touristicPoint = new TouristicPoint();
                        touristicPoint.setGeoLatitude(Double.parseDouble(lineData[0]));
                        touristicPoint.setGeoLongitude(Double.parseDouble(lineData[1]));
                        touristicPoint.setGeoAltitude(Double.parseDouble(lineData[2]));
                        touristicPoint.setDescricao(lineData[3]);
                        pois.add(touristicPoint);
                    }
                }
            });
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }

        List<Location> result = graphController.getShortestRouteWithInterestPoints(parkA.getIdPark(), parkB.getIdPark(), null, 1, true, "shortest_distance", pois).get(0);

        List<String> pathList = new LinkedList<>();
        pathList.add(String.format("Path %03d", 1));
        double dist = actionsController.calculatePathDistance(result);
        pathList.add(String.format("total_distance: %.0f", dist));
        pathList.add(String.format("total_energy: %.2f", actionsController.calculatePathEnergy(null, result) / 1000));
        pathList.add(String.format("elevation: %.0f", parkA.getGeoAltitude() - parkB.getGeoAltitude()));
        for (Location loc : result) {
            pathList.add(loc.getGeoLatitude() + ";" + loc.getGeoLongitude());
        }

        outputAdapter.writeDataToFile(s1, pathList);

        return Math.round(dist);
    }

    @Override
    public int suggestRoutesBetweenTwoLocations(double v, double v1, double v2, double v3, String s, String s1, int i, boolean b, String s2, String s3, String s4) {
        Park parkA = actionsController.getParkByCoordinates(v, v1);
        if (parkA == null) {
            Logger.log("park A null");
            return 0;
        }

        Park parkB = actionsController.getParkByCoordinates(v2, v3);
        if (parkB == null) {
            Logger.log("park B null");
            return 0;
        }

        User user = baseController.getUserByUsername(s1);
        if (user == null) {
            Logger.log("user null");
            return 0;
        }

        ArrayList<Location> pois = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(s3))) {
            br.lines().forEach(line -> {
                if (!line.startsWith("#")) {
                    String[] lineData = line.trim().split(";");
                    if (lineData.length > 1) {
                        /* Get the data from the CSV file
                        ======================================================== */
                        TouristicPoint touristicPoint = new TouristicPoint();
                        touristicPoint.setGeoLatitude(Double.parseDouble(lineData[0]));
                        touristicPoint.setGeoLongitude(Double.parseDouble(lineData[1]));
                        touristicPoint.setGeoAltitude(Double.parseDouble(lineData[2]));
                        touristicPoint.setDescricao(lineData[3]);
                        pois.add(touristicPoint);
                    }
                }
            });
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }

        List<List<Location>> result = graphController.getShortestRouteWithInterestPoints(parkA.getIdPark(), parkB.getIdPark(), user.getIdUser(), i, b, s2, pois);

        List<String> pathList = new LinkedList<>();
        for (int j = 0; j < result.size(); j++) {
            pathList.add(String.format("Path %03d", j + 1));
            pathList.add(String.format("total_distance: %.0f", actionsController.calculatePathDistance(result.get(j))));
            pathList.add(String.format("total_energy: %.2f", actionsController.calculatePathEnergy(user.getIdUser(), result.get(j)) / 1000 / ("electric".equalsIgnoreCase(s) ? 2 : 1)));
            pathList.add(String.format("elevation: %.0f", parkA.getGeoAltitude() - parkB.getGeoAltitude()));
            for (Location loc : result.get(j)) {
                pathList.add(loc.getGeoLatitude() + ";" + loc.getGeoLongitude());
            }
        }


        outputAdapter.writeDataToFile(s4, pathList);

        return result.size();
    }

}
