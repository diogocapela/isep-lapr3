package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.graph.Graph;
import lapr.project.graph.GraphAlgorithms;
import lapr.project.model.Bike;
import lapr.project.model.Location;
import lapr.project.model.Route;
import lapr.project.model.User;
import lapr.project.tree.BST;
import lapr.project.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GraphController extends BaseController {

    //final variables (code smells)
    private static final String STRING_FORMAT = "{%.2f;%.2f} -> {%.2f;%.2f}";
    private Graph<Location, String> graph;
    private ActionsController actionsController;

    public GraphController() {
        super();
        actionsController = new ActionsController();
    }

    public GraphController(BikesAPI bikesAPI, ParksAPI parksAPI, RidesAPI ridesAPI, UsersAPI usersAPI, TouristicPointsAPI tourAPI, InvoicesAPI invoicesAPI, ReceiptsAPI receiptsAPI, RoutesAPI routesAPI) {
        super(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);
        actionsController = new ActionsController(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);
    }

    public GraphController(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
        actionsController = new ActionsController(databaseUrl, databaseUsername, databasePassword);
    }

    private static void getAllLocationSequences(List<List<Location>> result, List<Location> currentList, List<Location> leftoverList) {

        if (leftoverList.size() == 1) {
            currentList.add(leftoverList.get(0));
            result.add(new ArrayList<>(currentList));
            currentList.remove(leftoverList.get(0));
        } else {
            for (int i = 0; i < leftoverList.size(); i++) {
                Location tmpLocation = leftoverList.remove(i);
                currentList.add(tmpLocation);
                getAllLocationSequences(result, currentList, leftoverList);
                currentList.remove(tmpLocation);
                leftoverList.add(i, tmpLocation);
            }
        }
    }

    private void importAllLocationsDistance() {

        graph = new Graph<>(true);

        List<Route> allRoutes = getAllRoutes();
        Location loc1;
        Location loc2;
        for (Route routeTmp : allRoutes) {
            loc1 = actionsController.getLocationByCoordinates(routeTmp.getLatitudeA(), routeTmp.getLongitudeA());
            if (loc1 != null) {
                graph.insertVertex(loc1);
            }

            loc2 = actionsController.getLocationByCoordinates(routeTmp.getLatitudeB(), routeTmp.getLongitudeB());
            if (loc2 != null) {
                graph.insertVertex(loc2);
            }

            if (loc1 != null && loc2 != null) {
                double dist = Utils.calculateDistanceBetweenTwoCoordinates(
                    loc1.getGeoLatitude(), loc1.getGeoLongitude(),
                    loc1.getGeoAltitude(), loc2.getGeoLatitude(),
                    loc2.getGeoLongitude(), loc2.getGeoAltitude());

                // Edge forward
                graph.insertEdge(loc1, loc2, String.format(
                    STRING_FORMAT, loc1.getGeoLatitude(),
                    loc1.getGeoLongitude(), loc2.getGeoLatitude(),
                    loc2.getGeoLongitude()), dist);

                if ("bi".equalsIgnoreCase(routeTmp.getRouteDirection())) {
                    //Edge back
                    graph.insertEdge(loc2, loc1, String.format(
                        STRING_FORMAT, loc2.getGeoLatitude(),
                        loc2.getGeoLongitude(), loc1.getGeoLatitude(),
                        loc1.getGeoLongitude()), dist);
                }
            }
        }
    }

    private void importAllLocationsEnergy(Integer idUser, Integer idBike) {

        graph = new Graph<>(true);

        User user = (idUser == null ? null : getUserById(idUser));
        Bike bike = (idBike == null ? null : getBikeById(idBike));

        List<Route> allRoutes = getAllRoutes();
        Location loc1;
        Location loc2;
        for (Route routeTmp : allRoutes) {
            loc1 = actionsController.getLocationByCoordinates(routeTmp.getLatitudeA(), routeTmp.getLongitudeA());
            if (loc1 != null) {
                graph.insertVertex(loc1);
            }

            loc2 = actionsController.getLocationByCoordinates(routeTmp.getLatitudeB(), routeTmp.getLongitudeB());
            if (loc2 != null) {
                graph.insertVertex(loc2);
            }

            if (loc1 != null && loc2 != null) {
                double energy = Utils.calculateEnergyToTravelBetweenLocations(user, bike, loc1, loc2, null, routeTmp);
                // Edge forward
                graph.insertEdge(loc1, loc2, String.format(
                    STRING_FORMAT, loc1.getGeoLatitude(),
                    loc1.getGeoLongitude(), loc2.getGeoLatitude(),
                    loc2.getGeoLongitude()), energy);

                if ("bi".equalsIgnoreCase(routeTmp.getRouteDirection())) {
                    energy = Utils.calculateEnergyToTravelBetweenLocations(user, bike, loc2, loc1, null, routeTmp);
                    //Edge back
                    graph.insertEdge(loc2, loc1, String.format(
                        STRING_FORMAT, loc2.getGeoLatitude(),
                        loc2.getGeoLongitude(), loc1.getGeoLatitude(),
                        loc1.getGeoLongitude()), energy);
                }
            }
        }
    }

    public List<Location> getShortestRouteBetweenTwoParks(Integer idStartPark, Integer idFinishPark) {
        LinkedList<Location> result = new LinkedList<>();

        importAllLocationsDistance();

        Location startPark = getParkById(idStartPark);
        Location finishPark = getParkById(idFinishPark);

        GraphAlgorithms.shortestPath(this.graph, startPark, finishPark, result);

        return result;
    }

    public List<Location> getMostEnergyEfficientRouteBetweenTwoParks(Integer idParkStart, Integer idParkFinish, Integer idUser, Integer idBike) {
        LinkedList<Location> result = new LinkedList<>();

        importAllLocationsEnergy(idUser, idBike);

        Location startPark = getParkById(idParkStart);
        Location finishPark = getParkById(idParkFinish);

        GraphAlgorithms.shortestPath(this.graph, startPark, finishPark, result);

        return result;
    }

    public List<List<Location>> getShortestRouteWithInterestPoints(Integer idParkStart, Integer idParkFinish, Integer idUser, int alternativeCount, boolean ascending, String sortingCriteria, ArrayList<Location> interestPoints) {
        final class RouteCost implements Comparable<RouteCost> {
            List<Location> locList;
            private double cost;
            private boolean ascending;

            private RouteCost(List<Location> locList, double cost, boolean ascending) {
                this.locList = locList;
                this.cost = cost;
                this.ascending = ascending;
            }

            @Override
            public int compareTo(RouteCost o) {
                int val = ascending ? 1 : -1;
                if (cost > o.cost) {
                    return val;
                } else {
                    return -val;
                }
            }
        }

        BST<RouteCost> tree = new BST<>();

        if ("energy".equalsIgnoreCase(sortingCriteria)) {
            importAllLocationsEnergy(idUser, null);
        } else {
            importAllLocationsDistance();
        }

        List<List<Location>> result = new LinkedList<>();

        List<List<Location>> sequences = new LinkedList<>();
        List<Location> currSequence = new ArrayList<>();

        if (interestPoints != null && !interestPoints.isEmpty()) {
            getAllLocationSequences(sequences, currSequence, interestPoints);

            Location startPark = getParkById(idParkStart);
            Location finishPark = getParkById(idParkFinish);

            for (List<Location> tmpSequence : sequences) {
                double cost = 0.0;
                LinkedList<Location> tmpRoute = new LinkedList<>();

                cost += GraphAlgorithms.shortestPath(this.graph, startPark, tmpSequence.get(0), tmpRoute);
                LinkedList<Location> currRoute = new LinkedList<>(tmpRoute);

                for (int i = 0; i < tmpSequence.size() - 1; i++) {
                    tmpRoute.clear();
                    cost += GraphAlgorithms.shortestPath(this.graph, tmpSequence.get(i), tmpSequence.get(i + 1), tmpRoute);
                    currRoute.removeLast();
                    currRoute.addAll(tmpRoute);
                }

                tmpRoute.clear();
                cost += GraphAlgorithms.shortestPath(this.graph, tmpSequence.get(tmpSequence.size() - 1), finishPark, tmpRoute);
                currRoute.removeLast();
                currRoute.addAll(tmpRoute);

                cost = ("number_of_points".equalsIgnoreCase(sortingCriteria) ? currRoute.size() : cost);
                tree.insert(new RouteCost(currRoute, cost, ascending));
            }

            for (RouteCost rc : tree.inOrder()) {
                if (result.size() == alternativeCount) {
                    return result;
                } else {
                    result.add(rc.locList);
                }
            }

            return result;
        } else {
            return Collections.singletonList(getShortestRouteBetweenTwoParks(idParkStart, idParkFinish));
        }
    }
}
