package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.Bike;
import lapr.project.model.Park;
import lapr.project.model.Ride;
import lapr.project.model.User;
import lapr.project.utils.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RequestBikeController extends BaseController {

    private ActionsController actionsController = new ActionsController();

    public RequestBikeController() {
        super();
    }

    public RequestBikeController(BikesAPI bikesAPI, ParksAPI parksAPI, RidesAPI ridesAPI, UsersAPI usersAPI, TouristicPointsAPI tourAPI, InvoicesAPI invoicesAPI, ReceiptsAPI receiptsAPI, RoutesAPI routesAPI) {
        super(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);
    }

    public RequestBikeController(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    public Ride unlockBike(int idUser, int idBike, int idPark) {
        User user = getUserById(idUser);
        Bike bike = getBikeById(idBike);
        Park park = getParkById(idPark);
        Logger.log("Validating inputs...");

        if (user == null) {
            Logger.log("ERROR: User is null!");
            return null;
        }

        if (park == null) {
            Logger.log("ERROR: Park is null!");
            return null;
        }

        if (bike == null) {
            Logger.log("ERROR: Bike is null!");
            return null;
        }

        if (bike.getIdPark() != idPark) {
            return null;
        }

        bike.setIdPark(null);
        updateBike(bike);

        String now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
        Ride ride = new Ride();
        ride.setIdBike(idBike);
        ride.setIdUser(idUser);
        ride.setIdStartPark(idPark);
        ride.setTimestampStart(now);
        Integer idRide = addRide(ride);

        return ride;
    }

    public boolean requestBike(Integer idUser, Integer idBike, Integer idPark) {
        User currentUser = getUserById(idUser);
        Park parkStart = getParkById(idPark);
        Bike bike = getBikeById(idBike);

        // 0. Validate inputs
        //---------------------------------------------------------------------------
        Logger.log("Validating inputs...");

        if (currentUser == null) {
            Logger.log("ERROR: User is null!");
            return false;
        }

        if (parkStart == null) {
            Logger.log("ERROR: Start Park is null!");
            return false;
        }

        if (bike == null) {
            Logger.log("ERROR: Bike is null!");
            return false;
        }

        if (getUnfinishedRideOfUser(idUser) != null) {
            Logger.log("Nao pode requerir uma bike enquanto nao entregou a que requeriu anteriormente");
            return false;
        }

        Ride rideUnlocked = unlockBike(idUser, idBike, idPark);

        if (rideUnlocked == null) {
            Logger.log("Nao foi possivel desbloquear a bike");
            return false;
        }

        return true;
    }

}
