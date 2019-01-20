package lapr.project.controller;

import javafx.util.Pair;
import lapr.project.data.*;
import lapr.project.model.Bike;
import lapr.project.model.Park;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ChargingController extends BaseController {

    public ChargingController() {
        super();
    }

    public ChargingController(BikesAPI bikesAPI, ParksAPI parksAPI, RidesAPI ridesAPI, UsersAPI usersAPI, TouristicPointsAPI tourAPI, InvoicesAPI invoicesAPI, ReceiptsAPI receiptsAPI, RoutesAPI routesAPI) {
        super(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);
    }

    public ChargingController(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    /**
     * Returns battery level of a given bike and a given time window of charging
     * with tension and current
     *
     * @param b       : Bike
     * @param begin   : Begin of charging
     * @param end     : End of charhing
     * @param tension : Tension en VOLT
     * @param current : Current in Ampere
     * @return Returns
     */
    public Double calculateCurrentBatteryLevel(Bike b, Date begin, Date end, Double tension, Double current) {
        Double ret;
        Double kW = Math.abs(begin.getTime() - end.getTime()) / 1000.0;
        Double kWh = ((kW / (60.0 * 60.0)) * tension * current);
        ret = (kWh + (b.getCurrentBattery() * 1000.0) / 1000.0) / 1000.0; //Wh+currentLevel in Wh
        return Math.min(ret, b.getMaxBattery());
    }

    public List<Pair<Bike, Double>> calculateHoursToCompleteChargeOfPark(List<Bike> bikeList, Park park) {
        LinkedList<Pair<Bike, Double>> retList = new LinkedList<>();
        if (bikeList == null) {
            return retList;
        }
        if (bikeList.isEmpty()) {
            return retList;
        }
        if (park == null) {
            return retList;
        }

        LinkedList<Bike> lst = new LinkedList<>();
        bikeList.forEach(b -> {
            lst.add(new Bike(b));//restore
        });
        sortByCharge(lst);
        return calculateHoursToCompleteChargeOfPark(lst, retList, park);
    }

    private LinkedList<Pair<Bike, Double>> calculateHoursToCompleteChargeOfPark(LinkedList<Bike> queue, LinkedList<Pair<Bike, Double>> retList, Park p) {
        while (!queue.isEmpty()) {
            Integer currentEletricalBikes = queue.size();
            Double power = Math.min(p.getVoltage() * (p.getIntensity() / currentEletricalBikes), 3000); //Watt
            Bike b = queue.pop();
            Double remainingWh = (b.getMaxBattery() - b.getCurrentBattery()) * 1000.0; //missing Wh
            Double hoursToComplete = remainingWh / power;
            retList.add(new Pair<>(b, hoursToComplete));
            queue.forEach(bike -> {
                bike.setCurrentBattery((remainingWh / 1000.0) + bike.getCurrentBattery()); //don't forget to convert from Wh to kWh
            });
        }
        return retList;
    }

    public Double calculateHoursToCompleteChargeOfParkTotal(LinkedList<Bike> bikeList, Park park) {
        List<Pair<Bike, Double>> retList = calculateHoursToCompleteChargeOfPark(bikeList, park);
        Double sum = 0.0;
        sum = retList.stream().map(pr -> pr.getValue()).reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
    }

    /**
     * Sorted a list of bikes by charge
     *
     * @param lstBikes Linked List of bikes to sort
     */
    protected void sortByCharge(LinkedList<Bike> lstBikes) {

        Collections.sort(lstBikes, (Bike o1, Bike o2) -> {
            Double missingkWh1 = o1.getMaxBattery() - o1.getCurrentBattery();
            Double missingkWh2 = o2.getMaxBattery() - o2.getCurrentBattery();
            return missingkWh1.compareTo(missingkWh2);
        });

    }

    /**
     * Returns battery level of a given bike and a given time window of charging
     * with tension and current
     *
     * @param b        : Bike
     * @param timeInMs : time in ms
     * @param tension  : Tension en VOLT
     * @param current  : Current in Ampere
     * @return Returns
     */
    public Double calculateCurrentBatteryLevel(Bike b, long timeInMs, Double tension, Double current) {
        Double ret;
        long elapsed = Math.abs(timeInMs);
        Double hours = elapsed / 1000.0 / (60.0 * 60.0); //hour
        Double power = tension * current; //Watt
        ret = ((hours * power) + (b.getCurrentBattery() * 1000.0)); //Wh+currentLevel in Wh
        ret /= 1000.0;
        if (ret >= b.getMaxBattery()) {
            return b.getMaxBattery(); //kWh
        } else {
            return ret;//kWh
        }
    }
}
