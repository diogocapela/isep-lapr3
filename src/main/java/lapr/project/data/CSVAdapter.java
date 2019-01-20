package lapr.project.data;

import lapr.project.controller.BaseController;
import lapr.project.model.*;
import lapr.project.utils.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVAdapter {

    private BaseController baseController;

    public CSVAdapter() {
        baseController = new BaseController();
    }

    /**
     * Load Parks
     */
    public int loadParksFromCSV(String filePath, String delimiter) {
        final int[] numberAdded = {0};
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().forEach(line -> {
                if (!line.startsWith("#")) {
                    Logger.log(line);
                    String[] lineData = line.trim().split(delimiter);
                    if (lineData.length > 1) {
                        /* Get the data from the CSV file
                        ======================================================== */
                        Double latitude = Double.parseDouble(lineData[0]);
                        Double longitude = Double.parseDouble(lineData[1]);
                        Double elevation = Double.parseDouble(lineData[2]);
                        String parkDescription = lineData[3];
                        Integer maxNumberOfEletricBicycles = Integer.parseInt(lineData[4]);
                        Integer maxNumberOfOtherBycicleTypes = Integer.parseInt(lineData[5]);
                        Double parkInputVoltage = Double.parseDouble(lineData[6]);
                        Double parkInputCurrent = Double.parseDouble(lineData[7]);
                        /* Insert the data into the database
                        ======================================================== */
                        Park park = new Park();
                        park.setGeoLatitude(latitude);
                        park.setGeoLongitude(longitude);
                        park.setGeoAltitude(elevation);
                        park.setDescricao(parkDescription);
                        park.setMaxCapacityElectric(maxNumberOfEletricBicycles);
                        park.setMaxCapacityStandard(maxNumberOfOtherBycicleTypes);
                        park.setVoltage(parkInputVoltage);
                        park.setIntensity(parkInputCurrent);
                        baseController.addPark(park);
                        numberAdded[0]++;
                    }
                }
            });
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
        return numberAdded[0];
    }

    /**
     * Load Touristic Points
     */
    public int loadTouristicPointsFromCSV(String filePath, String delimiter) {
        final int[] numberAdded = {0};
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().forEach(line -> {
                if (!line.startsWith("#")) {
                    String[] lineData = line.trim().split(delimiter);
                    if (lineData.length > 1) {
                        /* Get the data from the CSV file
                        ======================================================== */
                        Double latitude = Double.parseDouble(lineData[0]);
                        Double longitude = Double.parseDouble(lineData[1]);
                        Double elevation = Double.parseDouble(lineData[2]);
                        String poiDescription = lineData[3];
                        /* Insert the data into the database
                        ======================================================== */
                        TouristicPoint touristicPoint = new TouristicPoint();
                        touristicPoint.setGeoLatitude(latitude);
                        touristicPoint.setGeoLongitude(longitude);
                        touristicPoint.setGeoAltitude(elevation);
                        touristicPoint.setDescricao(poiDescription);
                        baseController.addTouristicPoint(touristicPoint);
                        numberAdded[0]++;
                    }
                }
            });
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
        return numberAdded[0];
    }

    /**
     * Load Bikes
     */
    public int loadBikesFromCSV(String filePath, String delimiter) {
        final int[] numberAdded = {0};
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().forEach(line -> {
                if (!line.startsWith("#")) {
                    String[] lineData = line.trim().split(delimiter);
                    if (lineData.length > 1) {
                        /* Get the data from the CSV file
                        ======================================================== */
                        String bicycleDescription = lineData[0];
                        Double weight = Double.parseDouble(lineData[1]);
                        String type = lineData[2];
                        Double parkLatitude = Double.parseDouble(lineData[3]);
                        Double parkLongitude = Double.parseDouble(lineData[4]);
                        Double maxBatteryCapacity = Double.parseDouble(lineData[5]);
                        Double actualBatteryCapacity = Double.parseDouble(lineData[6]);
                        Double aerodynamicCoefficient = Double.parseDouble(lineData[7]);
                        /* Insert the data into the database
                        ======================================================== */
                        Bike bike = new Bike();
                        bike.setDescription(bicycleDescription);
                        bike.setWeight(weight);
                        switch (type) {
                            case "electric":
                                bike.setType("ELECTRICAL");
                                break;
                            case "mtb":
                                bike.setType("MOUNTAIN");
                                break;
                            case "road":
                                bike.setType("ROAD");
                                break;
                            default:
                                Logger.log("Invalid bike type was supplied. Added the default one: ROAD");
                                bike.setType("ROAD");
                                break;
                        }
                        // Search for a park with the same lat and lng
                        for (Park park : baseController.getAllParks()) {
                            if (parkLatitude.equals(park.getGeoLatitude()) && parkLongitude.equals(park.getGeoLongitude())) {
                                bike.setIdPark(park.getIdPark());
                                break;
                            }
                        }
                        // If there no park was found, create a new one with the lat and lng values and add it to the database
                        if (bike.getIdPark() == null) {
                            Park newPark = new Park();
                            newPark.setGeoLatitude(parkLatitude);
                            newPark.setGeoLongitude(parkLongitude);
                            Integer newParkID = baseController.addPark(newPark);
                            bike.setIdPark(newParkID);
                        }
                        bike.setMaxBattery(maxBatteryCapacity);
                        bike.setCurrentBattery(actualBatteryCapacity);
                        bike.setDragCoefficient(aerodynamicCoefficient);
                        // Finally, add the bike to the database
                        baseController.addBike(bike);
                        numberAdded[0]++;
                    }
                }
            });
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
        return numberAdded[0];
    }

    /**
     * Load Routes (Paths)
     */
    public int loadRoutesFromCSV(String filePath, String delimiter) {
        final int[] numberAdded = {0};
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().forEach(line -> {
                if (!line.startsWith("#")) {
                    String[] lineData = line.trim().split(delimiter);
                    if (lineData.length > 1) {
                        /* Get the data from the CSV file
                        ======================================================== */
                        Double latitudeA = Double.parseDouble(lineData[0]);
                        Double longitudeA = Double.parseDouble(lineData[1]);
                        Double latitudeB = Double.parseDouble(lineData[2]);
                        Double longitudeB = Double.parseDouble(lineData[3]);
                        String pathDirection = lineData[4];
                        Double aerodynamicCoefficient = Double.parseDouble(lineData[5]);
                        Double windDirection = Double.parseDouble(lineData[6]);
                        Double windSpeed = Double.parseDouble(lineData[7]);
                        /* Insert the data into the database
                        ======================================================== */
                        Route route = new Route(latitudeA, longitudeA, latitudeB, longitudeB, pathDirection, aerodynamicCoefficient, windDirection, windSpeed);
                        baseController.addRoute(route);
                        numberAdded[0]++;
                    }
                }
            });
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
        return numberAdded[0];
    }

    /**
     * Load Users
     */
    public int loadUsersFromCSV(String filePath, String delimiter) {
        final int[] numberAdded = {0};
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().forEach(line -> {
                if (!line.startsWith("#")) {
                    String[] lineData = line.trim().split(delimiter);
                    if (lineData.length > 1) {
                        User user = new User();
                        user.setUsername(lineData[0]);
                        user.setEmail(lineData[1]);
                        user.setHeight(Double.parseDouble(lineData[2]));
                        user.setWeight(Double.parseDouble(lineData[3]));
                        user.setAvgSpeed(Double.parseDouble(lineData[4]));
                        user.setCreditCardNumber(lineData[5]);
                        baseController.addUser(user);
                        numberAdded[0]++;
                    }
                }
            });
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
        return numberAdded[0];
    }

}
