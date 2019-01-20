package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Utils;

import java.util.LinkedList;
import java.util.List;

public class InvoicesController extends BaseController {

    public InvoicesController() {
        super();
    }

    public InvoicesController(BikesAPI bikesAPI, ParksAPI parksAPI, RidesAPI ridesAPI, UsersAPI usersAPI, TouristicPointsAPI touristicPointsAPI, InvoicesAPI invoicesAPI, ReceiptsAPI receiptsAPI, RoutesAPI routesAPI) {
        super(bikesAPI, parksAPI, ridesAPI, usersAPI, touristicPointsAPI, invoicesAPI, receiptsAPI, routesAPI);
    }

    public InvoicesController(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    public double generateInvoiceForUser(int month, String userName, List<String> output) {
        List<String> rideStrings = new LinkedList<>();
        output.add(userName);

        User user = getUserByUsername(userName);
        if (user == null) {
            output.add("Previous Points: 0");
            output.add("Earned Points: 0");
            output.add("Discounted Points: 0");
            output.add("Actual Points: 0");
            output.add("Charged Value: 0.0");
            return 0.0;
        }

        List<Ride> rides = getAllUserRidesWithoutInvoice(user.getIdUser());
        String balanceString = "%s;%s;%s;%.6f;%.6f;%.6f;%.6f;%d;%.2f";
        if (rides.isEmpty()) {
            output.add("Previous Points: " + user.getCreditPoints());
            output.add("Earned Points: 0");
            output.add("Discounted Points: 0");
            output.add("Actual Points: " + user.getCreditPoints());
            output.add("Charged Value: 0.0");
            return 0.0;
        }

        int previousPoints = user.getCreditPoints();
        int earnedPoints = 0;
        int discountedPoints = 0;
        double result = 0.0;
        for (Ride rideTmp : rides) {
            String[] split = rideTmp.getTimestampStart().split("-");
            if (Integer.parseInt(split[1]) == month) {
                result += rideTmp.getCost();
                Park endPark = getParkById(rideTmp.getIdEndPark());
                Park startPark = getParkById(rideTmp.getIdStartPark());
                if (endPark.getGeoAltitude() - startPark.getGeoAltitude() > 50) {
                    earnedPoints += 15;
                } else if (endPark.getGeoAltitude() - startPark.getGeoAltitude() > 25) {
                    earnedPoints += 5;
                }
                Bike bike = getBikeById(rideTmp.getIdBike());
                rideStrings.add(String.format(balanceString, bike.getDescription(), rideTmp.getTimestampStart(), rideTmp.getTimestampFinish(), startPark.getGeoLatitude(), startPark.getGeoLongitude(), endPark.getGeoLatitude(), endPark.getGeoLongitude(), Utils.getTimeDifferenceInMinutes(rideTmp.getTimestampStart(), rideTmp.getTimestampFinish()) * 60, rideTmp.getCost()));
            }
        }

        Invoice invoice = new Invoice();
        invoice.setTotalCost(result);

        int userPoints = previousPoints + earnedPoints;
        while (result > 1 && userPoints >= 10) {
            result -= 1;
            userPoints -= 10;
            discountedPoints += 10;
        }

        invoice.setPointsUsed(discountedPoints);
        invoice.setMoneyPaid(result);
        invoice.setIdUser(user.getIdUser());

        int idInvoice = addInvoice(invoice);

        user.setCreditPoints(userPoints);
        updateUser(user);

        for (Ride rideTmp : rides) {
            rideTmp.setIdInvoice(idInvoice);
            updateRide(rideTmp);
        }

        output.add("Previous Points: " + previousPoints);
        output.add("Earned Points: " + earnedPoints);
        output.add("Discounted Points: " + discountedPoints);
        output.add("Actual Points: " + user.getCreditPoints());
        output.add("Charged Value: " + result);
        output.addAll(rideStrings);

        return result;
    }

    public double getUserDebt(String userName, List<String> output) {
        String balanceString = "%s;%s;%s;%.6f;%.6f;%.6f;%.6f;%d;%.2f";
        List<String> rideStrings = new LinkedList<>();
        if (userName == null) {
            return 0.0;
        }

        output.add(userName);

        double result = 0.0;

        User user = getUserByUsername(userName);
        if (user == null) {
            return result;
        }
        List<Ride> rides = getAllRides();
        for (Ride rideTmp : rides) {
            if (rideTmp.getIdUser().equals(user.getIdUser())) {
                boolean inDebt = false;
                if (rideTmp.getIdInvoice() == null) {
                    inDebt = true;
                } else {
                    Invoice inv = getInvoiceById(rideTmp.getIdInvoice());
                    if (inv == null || !("paid".equalsIgnoreCase(inv.getStatus()))) {
                        inDebt = true;
                    }
                }

                if (inDebt && rideTmp.getIdEndPark()!=0) {
                    result += rideTmp.getCost();
                    Bike bike = getBikeById(rideTmp.getIdBike());
                    Park startPark = getParkById(rideTmp.getIdStartPark());
                    Park endPark = getParkById(rideTmp.getIdEndPark());
                    String line = String.format(balanceString, bike.getDescription(), rideTmp.getTimestampStart(), rideTmp.getTimestampFinish(), startPark.getGeoLatitude(), startPark.getGeoLongitude(), endPark.getGeoLatitude(), endPark.getGeoLongitude(), Utils.getTimeDifferenceInSeconds(rideTmp.getTimestampStart(), rideTmp.getTimestampFinish()), rideTmp.getCost());
                    if (rideStrings.isEmpty()) {
                        rideStrings.add(line);
                    } else {
                        int i = 0;
                        for (String printString : rideStrings) {
                            String unlockDate = printString.split(";")[1];
                            if (Utils.getTimeDifference(unlockDate, rideTmp.getTimestampStart()) > 0) {
                                rideStrings.add(i, line);
                                break;
                            }
                            i++;
                            if (i == rideStrings.size()) {
                                rideStrings.add(line);
                                break;
                            }
                        }
                    }
                }
            }
        }

        output.addAll(rideStrings);

        return result;
    }


}
