package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;

import java.util.List;

public class BaseController {

    private BikesAPI bikesAPI;
    private ParksAPI parksAPI;
    private RidesAPI ridesAPI;
    private UsersAPI usersAPI;
    private TouristicPointsAPI touristicPointsAPI;
    private InvoicesAPI invoicesAPI;
    private ReceiptsAPI receiptsAPI;
    private RoutesAPI routesAPI;

    public BaseController() {
        bikesAPI = new BikesAPI();
        parksAPI = new ParksAPI();
        ridesAPI = new RidesAPI();
        usersAPI = new UsersAPI();
        touristicPointsAPI = new TouristicPointsAPI();
        invoicesAPI = new InvoicesAPI();
        receiptsAPI = new ReceiptsAPI();
        routesAPI = new RoutesAPI();
    }

    public BaseController(BikesAPI bikesAPI, ParksAPI parksAPI, RidesAPI ridesAPI, UsersAPI usersAPI, TouristicPointsAPI touristicPointsAPI, InvoicesAPI invoicesAPI, ReceiptsAPI receiptsAPI, RoutesAPI routesAPI) {
        this.bikesAPI = bikesAPI;
        this.parksAPI = parksAPI;
        this.ridesAPI = ridesAPI;
        this.usersAPI = usersAPI;
        this.touristicPointsAPI = touristicPointsAPI;
        this.invoicesAPI = invoicesAPI;
        this.receiptsAPI = receiptsAPI;
        this.routesAPI = routesAPI;
    }

    public BaseController(String databaseUrl, String databaseUsername, String databasePassword) {
        bikesAPI = new BikesAPI(databaseUrl, databaseUsername, databasePassword);
        parksAPI = new ParksAPI(databaseUrl, databaseUsername, databasePassword);
        ridesAPI = new RidesAPI(databaseUrl, databaseUsername, databasePassword);
        usersAPI = new UsersAPI(databaseUrl, databaseUsername, databasePassword);
        touristicPointsAPI = new TouristicPointsAPI(databaseUrl, databaseUsername, databasePassword);
        invoicesAPI = new InvoicesAPI(databaseUrl, databaseUsername, databasePassword);
        receiptsAPI = new ReceiptsAPI(databaseUrl, databaseUsername, databasePassword);
        routesAPI = new RoutesAPI(databaseUrl, databaseUsername, databasePassword);
    }

    // Bike
    //---------------------------------------------------------------------

    public List<Bike> getAllBikes() {
        return bikesAPI.getAllBikes();
    }

    public List<Bike> getAllBikesFromPark(Integer idPark) {
        return bikesAPI.getAllActiveBikesFromPark(idPark);
    }

    public List<Bike> getAllMountainActiveBikesFromPark(Integer id) {
        return bikesAPI.getAllMountainActiveBikesFromPark(id);
    }

    public List<Bike> getAllRoadActiveBikesFromPark(Integer id) {
        return bikesAPI.getAllRoadActiveBikesFromPark(id);
    }

    public List<Bike> getAllElectricalActiveBikesFromPark(Integer id) {
        return bikesAPI.getAllElectricalActiveBikesFromPark(id);
    }

    public Bike getBikeById(int id) {
        return bikesAPI.getBikeById(id);
    }

    public Bike getBikeByDescription(String d) {
        return bikesAPI.getBikeByDescription(d);
    }

    public Integer addBike(Bike bike) {
        return bikesAPI.addBike(bike);
    }

    public Integer updateBike(Bike bike) {
        return bikesAPI.updateBike(bike);
    }

    public Integer removeBikeById(int id) {
        return bikesAPI.removeBikeById(id);
    }

    public Integer deactivateBike(int id) {
        return bikesAPI.deactivateBikeById(id);
    }

    // Park
    //---------------------------------------------------------------------

    public List<Park> getAllParks() {
        return parksAPI.getAllParks();
    }

    public Park getParkById(int id) {
        return parksAPI.getParkById(id);
    }

    public Park getParkByCoordinates(Double latitutide, Double longitude) {
        return parksAPI.getParkByCoordinates(latitutide, longitude);
    }

    public Integer addPark(Park park) {
        return parksAPI.addPark(park);
    }

    public Integer updatePark(Park park) {
        return parksAPI.updatePark(park);
    }

    public Integer removeParkById(int id) {
        return parksAPI.removeParkById(id);
    }

    public Integer deactivatePark(int id) {
        return parksAPI.deactivateParkById(id);
    }

    // Ride
    //---------------------------------------------------------------------

    public List<Ride> getAllRides() {
        return ridesAPI.getAllRides();
    }

    public List<Ride> getAllUnfinishedRides() {
        return ridesAPI.getAllUnfinishedRides();
    }

    public Ride getUnfinishedRideOfUser(Integer idUser) {
        return ridesAPI.getUnfinishedRideOfUser(idUser);
    }

    public Ride getRideById(int id) {
        return ridesAPI.getRideById(id);
    }

    public List<Ride> getRidesByInvoice(Integer id) {
        return ridesAPI.getRidesByInvoice(id);
    }

    public List<Ride> getAllUserRidesWithoutInvoice(Integer id) {
        return ridesAPI.getAllUserRidesWithoutInvoice(id);
    }

    public Integer addRide(Ride ride) {
        return ridesAPI.addRide(ride);
    }

    public Integer updateRide(Ride ride) {
        return ridesAPI.updateRide(ride);
    }

    public Integer removeRideById(int id) {
        return ridesAPI.removeRideById(id);
    }

    // User
    //---------------------------------------------------------------------

    public List<User> getAllUsers() {
        return usersAPI.getAllUsers();
    }

    public User getUserById(int id) {
        return usersAPI.getUserById(id);
    }

    public User getUserByEmail(String email) {
        return usersAPI.getUserByEmail(email);
    }

    public User getUserByUsername(String username) {
        return usersAPI.getUserByUsername(username);
    }

    public Integer addUser(User user) {
        return usersAPI.addUser(user);
    }

    public Integer updateUser(User user) {
        return usersAPI.updateUser(user);
    }

    public Integer removeUserById(int id) {
        return usersAPI.removeUserById(id);
    }

    public Integer removeUserByEmail(String email) {
        return usersAPI.removeUserByEmail(email);
    }

    // TouristicPoint
    //-------------------------------------------------------------------------

    public List<TouristicPoint> getAllTouristicPoints() {
        return touristicPointsAPI.getAllTouristicPoints();
    }

    public TouristicPoint getTouristicPointById(Integer id) {
        return touristicPointsAPI.getTouristicPointById(id);
    }

    public TouristicPoint getTouristicPointByCoordinates(Double latitude, Double longitude) {
        return touristicPointsAPI.getTouristicPointByCoordinates(latitude, longitude);
    }

    public Integer addTouristicPoint(TouristicPoint tp) {
        return touristicPointsAPI.addTouristicPoint(tp);
    }

    public Integer removeTouristicPointById(Integer id) {
        return touristicPointsAPI.removeTouristicPointById(id);
    }

    // Invoice
    //---------------------------------------------------------------------

    public List<Invoice> getAllInvoices() {
        return invoicesAPI.getAllInvoices();
    }

    public Invoice getInvoiceById(int id) {
        return invoicesAPI.getInvoiceById(id);
    }

    public List<Invoice> getAllInvoicesOfUser(Integer id) {
        return invoicesAPI.getAllInvoicesOfUser(id);
    }

    public Integer addInvoice(Invoice invoice) {
        return invoicesAPI.addInvoice(invoice);
    }

    public Integer removeInvoiceById(int id) {
        return invoicesAPI.removeInvoiceById(id);
    }

    // Receipt
    //---------------------------------------------------------------------

    public List<Receipt> getAllReceipts() {
        return receiptsAPI.getAllReceipts();
    }

    public Receipt getReceiptById(int id) {
        return receiptsAPI.getReceiptById(id);
    }

    public List<Receipt> getAllReceiptsFromUser(Integer id) {
        return receiptsAPI.getAllReceiptsFromUser(id);
    }

    public Integer addReceipt(Receipt receipt) {
        return receiptsAPI.addReceipt(receipt);
    }

    public Integer removeReceiptById(int id) {
        return receiptsAPI.removeReceiptById(id);
    }

    // Route
    //---------------------------------------------------------------------

    public List<Route> getAllRoutes() {
        return routesAPI.getAllRoutes();
    }

    public Route getRouteById(Integer id) {
        return routesAPI.getRouteById(id);
    }

    public Route getRouteByCoordinates(Double latA, Double lonA, Double latB, Double lonB) {
        return routesAPI.getRouteByCoordinates(latA, lonA, latB, lonB);
    }

    public Integer addRoute(Route route) {
        return routesAPI.addRoute(route);
    }

    public Integer updateRoute(Route route) {
        return routesAPI.updateRoute(route);
    }

    public Integer removeRouteById(int id) {
        return routesAPI.removeRouteById(id);
    }
}
