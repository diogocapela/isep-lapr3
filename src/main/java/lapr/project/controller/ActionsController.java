package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.tree.BST;
import lapr.project.utils.Utils;

import java.util.*;

/**
 * Class responsible to respond to services requested by the user.
 */
public class ActionsController extends BaseController {

    /**
     * Empty constructor.
     */
    public ActionsController() {
        super();
    }

    /**
     * Complete constructor.
     *
     * @param bikesAPI,           BikesAPI, database interface for bikes
     * @param parksAPI,           ParksAPI, database interface for parks
     * @param ridesAPI,           RidesAPI, database interface for rides
     * @param usersAPI,           UsersAPI, database interface for users
     * @param touristicPointsAPI, database interface for touristicpoints
     */
    public ActionsController(BikesAPI bikesAPI, ParksAPI parksAPI,
                             RidesAPI ridesAPI, UsersAPI usersAPI,
                             TouristicPointsAPI touristicPointsAPI,
                             InvoicesAPI invoicesAPI,
                             ReceiptsAPI receiptsAPI,
                             RoutesAPI routesAPI) {
        super(bikesAPI, parksAPI, ridesAPI, usersAPI, touristicPointsAPI, invoicesAPI, receiptsAPI, routesAPI);
    }

    /**
     * Constructor with three parameters.
     *
     * @param databaseUrl,      String, database url
     * @param databaseUsername, String, database username
     * @param databasePassword, String, database password
     */
    public ActionsController(String databaseUrl, String databaseUsername,
                             String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    /**
     * Queries the database directly and returns a location by coordinates.
     */
    public Location getLocationByCoordinates(Double latitutide, Double longitude) {
        Location loc = null;

        loc = getParkByCoordinates(latitutide, longitude);
        if (loc == null) {
            loc = getTouristicPointByCoordinates(latitutide, longitude);
        }

        return loc;
    }

    /**
     * Method that returns a map with the number of available bikes by bikeTypes.
     *
     * @param idPark
     * @return
     */
    public Map<BikeType, List<Bike>> getAvailableBikesOfPark(int idPark) {
        Map<BikeType, List<Bike>> result = new EnumMap<>(BikeType.class);
        Park park = getParkById(idPark);
        if (park == null) {
            return null;
        }
        List<Bike> roadBikes = getAllRoadActiveBikesFromPark(idPark);
        List<Bike> mountainBikes = getAllMountainActiveBikesFromPark(idPark);
        List<Bike> eletricalBikes = getAllElectricalActiveBikesFromPark(idPark);
        result.put(BikeType.ELECTRICAL, eletricalBikes);
        result.put(BikeType.MOUNTAIN, mountainBikes);
        result.put(BikeType.ROAD, roadBikes);
        return result;
    }

    /**
     * Method that returns a map with all the free spaces by bike type.
     *
     * @param idPark, int, park id
     * @return result, Map, a map with the number of free spaces per Biketype
     */
    public Map<BikeType, Integer> getFreeSpacesOfPark(int idPark) {
        Map<BikeType, Integer> result = new TreeMap<>();
        Park park = getParkById(idPark);
        if (park == null) {
            return null;
        }
        int roadBikesCnt = getAllRoadActiveBikesFromPark(idPark).size();
        int mountainBikesCnt = getAllMountainActiveBikesFromPark(idPark).size();
        int electricBikesCnt =
            getAllElectricalActiveBikesFromPark(idPark).size();
        result.put(BikeType.ROAD, park.getMaxCapacityStandard() -
            mountainBikesCnt - roadBikesCnt);
        result.put(BikeType.MOUNTAIN, park.getMaxCapacityStandard() -
            mountainBikesCnt - roadBikesCnt);
        result.put(BikeType.ELECTRICAL, park.getMaxCapacityElectric() -
            electricBikesCnt);
        return result;
    }

    /**
     * Method that returns the amount of calories burned in a given ride.
     *
     * @param ride
     * @return
     */
    public Double calculateCaloriesBurntBetweenParks(Ride ride) {

        Double energy;
        User user = getUserById(ride.getIdUser());
        Bike bike = getBikeById(ride.getIdBike());
        BikeType bikeType = bike.getType();
        Location park1 = getParkById(ride.getIdStartPark());
        Location park2 = getParkById(ride.getIdEndPark());
        if (park2 == null) {
            return null;
        }
        Route route = getRouteByCoordinates(park1.getGeoLatitude(), park1.getGeoLongitude(), park2.getGeoLatitude(), park2.getGeoLongitude());
        Double distance = Utils.calculateDistanceBetweenTwoCoordinates(
            park1.getGeoLatitude(),
            park1.getGeoLongitude(),
            park1.getGeoAltitude(),
            park2.getGeoLatitude(),
            park2.getGeoLongitude(),
            park2.getGeoAltitude());

        Long seconds = Utils.getTimeDifferenceInSeconds(ride.getTimestampStart(), ride.getTimestampFinish());
        double time = (seconds == null ? 0.0 : seconds);
        Double rideSpeed = (time > 0.0 ? distance / time : 0.0);

        energy = Utils.calculateEnergyToTravelBetweenLocations(user, bike, park1, park2, rideSpeed, route);
        //  1 / 4,186 cal = 1 Joule -> 0.2388915432 cal
        //  1.0000008 / 2.77778e-7 Joule = 1 Kwatts.hora -> 3600000 Joules

        return Math.abs(energy * 3600000 * 0.2388915432 * (bikeType.equals(BikeType.ELECTRICAL) ? 0.5 : 1));
    }

    /**
     * Method that returns the amount of necessary energy to travel between two Locations
     *
     * @param idUser,         Integer, user id
     * @param firstLocation,  Location, start location
     * @param secondLocation, Location, end location
     * @return Double, electrical energy spent between two locations
     * (SI, Kwatt.hour)
     */
    public Double calculateElectricalEnergyToTravelBetweenLocations(
        //unicos parametros obrigatorios sao locations, resto pode ir tudo a null
        Integer idUser,
        Integer idBike,
        Location firstLocation,
        Location secondLocation,
        Double speed) {

        User user = (idUser == null ? null : getUserById(idUser));
        Bike bike = (idBike == null ? null : getBikeById(idBike));

        if (firstLocation == null || secondLocation == null) {
            return null;
        }

        Route route = getRouteByCoordinates(firstLocation.getGeoLatitude(), firstLocation.getGeoLongitude(), secondLocation.getGeoLatitude(), secondLocation.getGeoLongitude());

        return Utils.calculateEnergyToTravelBetweenLocations(user, bike, firstLocation, secondLocation, speed, route);
    }

    /**
     * Method that returns the total electrical energy necessary to travel
     * between two locations
     *
     * @param idUser, Integer, user id
     * @param path,   List, list with intermediary locations between a start point
     *                and an end point
     * @return result, Double, electrical energy spent between two locations
     * passing through zero or several locations
     * (SI, Kwatt.hour)
     */
    public Double calculatePathEnergy(Integer idUser, List<Location> path) {
        double result = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            result += calculateElectricalEnergyToTravelBetweenLocations(idUser, null, path.get(i), path.get(i + 1), null);
        }

        return result;
    }

    /**
     * Method that returns the total distance in meters necessary to travel
     *
     * @param path, List, list with intermediary locations between a start point
     *              and an end point
     * @return result, Double, distance in meters for the sequence of stops
     */
    public Double calculatePathDistance(List<Location> path) {
        double result = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            result += Utils.calculateDistanceBetweenTwoCoordinates(path.get(i).getGeoLatitude(), path.get(i).getGeoLongitude(), path.get(i).getGeoAltitude(), path.get(i + 1).getGeoLatitude(), path.get(i + 1).getGeoLongitude(), path.get(i + 1).getGeoAltitude());
        }

        return result;
    }

    /**
     * Method that returns the distance between a user and a given park
     *
     * @param userLatitude,    Double, user´s latitude reference (degrees)
     * @param userLongetitude, Double, user´s latitude reference (degrees)
     * @param userHeight,      Double, user´s height (meters)
     * @param idPark,          Integer, park id to calculate distance
     * @return distance, Double, distance between a user and a given park
     */
    public Double getDistanceFromUserToPark(Double userLatitude,
                                            Double userLongetitude,
                                            Double userHeight,
                                            Integer idPark) {
        double distance = 0.0;
        Park park = getParkById(idPark);
        if (park != null) {
            distance = Utils.calculateDistanceBetweenTwoCoordinates(
                userLatitude, userLongetitude, userHeight,
                park.getGeoLatitude(), park.getGeoLongitude(),
                park.getGeoAltitude());
        }
        return distance;
    }

    /**
     * Method that returns the most charged bike from a given park.
     *
     * @param idPark, Integer, park id
     * @return maxBatteryBike, Bike, most charged bike from the given park
     */
    public Bike getBikeWithMostChargeFromPark(Integer idPark) {
        Park park = getParkById(idPark);
        if (park == null) {
            return null;
        }

        List<Bike> allElectricBikes =
            getAllElectricalActiveBikesFromPark(idPark);
        Bike maxBatteryBike = null;
        double maxCurrentBattery = 0.0;
        for (Bike bike : allElectricBikes) {
            if (bike.getCurrentBattery() > maxCurrentBattery) {
                maxCurrentBattery = bike.getCurrentBattery();
                maxBatteryBike = bike;
            }
        }
        return maxBatteryBike;
    }

    /**
     * Method that returns a bike with 10% plus energy given a value of
     * necessary energy.
     *
     * @param idPark,     Integer, park id to get the bike
     * @param energyCost, Double, value of necessary energy for the bike
     * @return bikeWithEnoughEnergy, Bike, with 10% plus energy given a value of
     * necessary energy.
     */
    public Bike getBikeWith10PlusEnergyFromPark(Integer idPark,
                                                Double energyCost) {
        Park park = getParkById(idPark);
        if (park == null) {
            return null;
        }

        double necessaryEnergy = energyCost * 1.1;
        Bike bikeWithEnoughEnergy = null;

        List<Bike> elecBikes = getAllElectricalActiveBikesFromPark(idPark);
        for (Bike bike : elecBikes) {
            if (bike.getCurrentBattery() >= necessaryEnergy) {
                if (bikeWithEnoughEnergy == null) {
                    bikeWithEnoughEnergy = bike;
                }
                if (bike.getCurrentBattery() < bikeWithEnoughEnergy.getCurrentBattery()) {
                    bikeWithEnoughEnergy = bike;
                }
            }
        }

        return bikeWithEnoughEnergy;
    }

    /**
     * Method that returns an ordered list of the nearest parks from user geo
     * location (latitude and longitude)
     *
     * @param userLatitude,  Double, user´s latitude
     * @param userLongitude, Double, user´s longitude
     * @param userHeight,    Double, usr´s height
     * @return allParks, List, ordered list by  ascending distances from user to
     * all parks
     */
    public List<Park> getNearestParks(Double userLatitude,
                                      Double userLongitude,
                                      Double userHeight) {

        final class ParkDistance implements Comparable<ParkDistance> {
            Park park;
            private double distance;

            private ParkDistance(Park park, double distance) {
                this.park = park;
                this.distance = distance;
            }

            @Override
            public int compareTo(ParkDistance o) {
                if (distance > o.distance) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        BST<ParkDistance> tree = new BST<>();
        for (Park parkTmp : getAllParks()) {
            double dist = Utils.calculateDistanceBetweenTwoCoordinates(
                userLatitude,
                userLongitude,
                userHeight,
                parkTmp.getGeoLatitude(),
                parkTmp.getGeoLongitude(),
                parkTmp.getGeoAltitude());
            tree.insert(new ParkDistance(parkTmp, dist));
        }

        List<Park> top5 = new ArrayList<>();
        int cnt = 0;

        for (ParkDistance pdTmp : tree.inOrder()) {
            cnt++;
            top5.add(pdTmp.park);
            if (cnt == 5) {
                break;
            }
        }

        return top5;
    }

    /**
     * Checks if a park has free space for a specific bike type.
     *
     * @param idBike, int,bike id
     * @param idPark, int park id
     * @return true if the park have space for the given bike or false otherwise
     */
    public boolean doesParkHaveSpaceForThisBike(int idBike, int idPark) {
        Bike bike = getBikeById(idBike);
        Park park = getParkById(idPark);

        if (bike == null || park == null) {
            return false;
        }

        BikeType bikeType = bike.getType();
        Map<BikeType, Integer> freeSpacesMap = getFreeSpacesOfPark(idPark);
        return freeSpacesMap.get(bikeType) > 0;
    }

    /**
     * Based on a Park's id, this method will show the current charging status
     * for an electrical bike, and how long it will take to all of the bikes to
     * get to full charge.
     *
     * @param idPark, Integer, park id
     * @return result, ArrayList, with park report
     */
    public List<String> generateParkReport(Integer idPark) {
        List<String> result = new ArrayList<>();
        if (idPark != null) {
            ChargingController chargingController = new ChargingController();
            LinkedList<Bike> electricalBikesAtPark1 = new LinkedList<>();

            //current charging status for each bike in park
            List<Bike> electricalBikesAtPark = getAllElectricalActiveBikesFromPark(idPark);

            for (Bike bike : electricalBikesAtPark) {
                result.add(bike.getIdBike() + " " + bike.getCurrentBattery());
                electricalBikesAtPark1.add(bike);
            }

            //how long each bike will take to get to full charge

            double hours = chargingController.calculateHoursToCompleteChargeOfParkTotal(electricalBikesAtPark1, getParkById(idPark));
            String timeConverted = Utils.convertHoursToHoursMinutesSeconds(hours);
            result.add("Time left until all bikes in park " + idPark + " are fully charged is: " + timeConverted);
        }
        return result;
    }

    /**
     * Returns the list of bikes and how long it takes to charge to 100%
     *
     * @param idPark : park ID
     * @return Returns a list of pair containing a bike and how long to charge to 100% in seconds
     */
    public List<Pair<Bike, Double>> getParkReport(Integer idPark) {
        List<Pair<Bike, Double>> retLst = new LinkedList<>();
        if (idPark != null) {
            ChargingController chargingController = new ChargingController();
            List<Bike> electricalBikesAtPark1;
            electricalBikesAtPark1 = getAllElectricalActiveBikesFromPark(idPark);
            Park p = getParkById(idPark);
            retLst = chargingController.calculateHoursToCompleteChargeOfPark(electricalBikesAtPark1, p);
        }
        return retLst;
    }

}
