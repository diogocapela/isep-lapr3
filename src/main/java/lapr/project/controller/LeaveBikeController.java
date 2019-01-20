package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Utils;

import java.util.Calendar;
import java.util.Date;

public class LeaveBikeController extends BaseController {

    public LeaveBikeController() {
        super();
    }

    public LeaveBikeController(BikesAPI bikesAPI, ParksAPI parksAPI, RidesAPI ridesAPI, UsersAPI usersAPI, TouristicPointsAPI tourAPI, InvoicesAPI invoicesAPI, ReceiptsAPI receiptsAPI, RoutesAPI routesAPI) {
        super(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);
    }

    public LeaveBikeController(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    /**
     * Leave bike (lock bike) method.
     */
    public boolean leaveBike(Integer idUser, Integer idBike, Integer idPark) {
        User currentUser = getUserById(idUser);
        Bike currentBike = getBikeById(idBike);
        Park currentPark = getParkById(idPark);

        // 0. Validate inputs
        //---------------------------------------------------------------------------
        if (currentUser == null) {
            return false;
        }

        if (currentBike == null) {
            return false;
        }

        if (currentPark == null) {
            return false;
        }

        if (currentBike.getIdPark() != 0) {
            return false;
        }

        // 1. Get the unfinished ride of the user
        //---------------------------------------------------------------------------
        Ride currentRide = getUnfinishedRideOfUser(idUser);

        if (currentRide == null) {
            return false;
        }

        // 2. Get the total duration of the ride
        //---------------------------------------------------------------------------
        Date timeStart = Utils.convertStringToDate(currentRide.getTimestampStart());
        Date timeFinish = Calendar.getInstance().getTime();

        double durationInMinutes = (double) Utils.getTimeDifferenceInMinutes(timeStart, timeFinish);

        // 3. Calculate the cost of the ride
        //---------------------------------------------------------------------------
        Double cost;
        cost = durationInMinutes > 60.0 ? (durationInMinutes - 60.0) * 3 / 60.0 : 0.0;

        // 4. Set the new values to the ride
        //---------------------------------------------------------------------------
        currentRide.setTimestampFinish(Utils.convertDateToString(timeFinish));
        currentRide.setCost(cost);
        currentRide.setIdEndPark(idPark);

        // 5. Update the ride at the database
        //---------------------------------------------------------------------------
        updateRide(currentRide);

        // 5. Update the park to the bike at the database
        //---------------------------------------------------------------------------
        currentBike.setIdPark(idPark);
        updateBike(currentBike);

        // 6. Give points to the user based of the altitude difference
        //---------------------------------------------------------------------------
        Location locationStart = getParkById(currentRide.getIdStartPark());
        if (locationStart != null) {
            double altitudeDifference = currentPark.getGeoAltitude() - locationStart.getGeoAltitude();
            int pointsToCredit = 0;

            if (altitudeDifference > 50) {
                pointsToCredit = 15;
            } else if (altitudeDifference > 25) {
                pointsToCredit = 5;
            }

            currentUser.addCreditPoints(pointsToCredit);
            updateUser(currentUser);

            // 7. Send an email to the user
            //---------------------------------------------------------------------------
            //new DataUtils sendEmailNotification currentUser  "Your bike was locked for "  durationInMinutes  " minutes."  "INFORMATION ABOUT YOUR BIKE"

            // 8. Return of burnt calories projection
            //---------------------------------------------------------------------------
            //  Calories projection

            // 9. End of the operation
            //---------------------------------------------------------------------------
            return true;
        }
        return false;
    }


}
