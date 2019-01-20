package lapr.project.controller;

/**
 * @author Vítor Hugo Silva (1140825@isep.ipp.pt)
 */

import javafx.util.Pair;
import lapr.project.model.Bike;
import lapr.project.model.Park;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ChargingControllerTest {

    protected ChargingController cc, cc2;

    @BeforeEach
    void testSetup() {
        cc = new ChargingController();
        cc2 = new ChargingController("123", "123", "plasd");
        cc2 = new ChargingController(null, null, null, null, null, null, null, null);

    }

    @Test
    public void testcalculateCurrentBatteryLevelMs() {
        Bike b = new Bike();
        b.setIdBike(1);
        b.setIdPark(1);
        b.setMaxBattery(0.9); //kWh
        b.setType("Eletrical");
        b.setWeight(30.0);
        b.setBatteryType("a");
        b.setCurrentBattery(0.1);
        b.setIsActive(true);

        Double expectedkWh = 0.9;

        Double returnedkWh = cc.calculateCurrentBatteryLevel(b, 3600000, 220.0, 16.0);

        Assertions.assertEquals(expectedkWh, returnedkWh);

        returnedkWh = cc.calculateCurrentBatteryLevel(b, (3600000 / 8), 220.0, 16.0);
        expectedkWh = 0.54;
        Assertions.assertEquals(expectedkWh, returnedkWh);

    }

    @Test
    public void testsortByCharge() {
        Bike b1 = new Bike();
        b1.setIdBike(1);
        b1.setIdPark(1);
        b1.setMaxBattery(0.9); //kWh
        b1.setType("Eletrical");
        b1.setWeight(30.0);
        b1.setBatteryType("a");
        b1.setCurrentBattery(0.1);
        b1.setIsActive(true);

        Bike b2 = new Bike();
        b2.setIdBike(1);
        b2.setIdPark(1);
        b2.setMaxBattery(0.9); //kWh
        b2.setType("Eletrical");
        b2.setWeight(30.0);
        b2.setBatteryType("a");
        b2.setCurrentBattery(0.2);
        b2.setIsActive(true);

        Bike b3 = new Bike();
        b3.setIdBike(1);
        b3.setIdPark(1);
        b3.setMaxBattery(0.9); //kWh
        b3.setType("Eletrical");
        b3.setWeight(30.0);
        b3.setBatteryType("a");
        b3.setCurrentBattery(0.3);
        b3.setIsActive(true);

        Bike b4 = new Bike();
        b4.setIdBike(1);
        b4.setIdPark(1);
        b4.setMaxBattery(0.9); //kWh
        b4.setType("Eletrical");
        b4.setWeight(30.0);
        b4.setBatteryType("a");
        b4.setCurrentBattery(0.4);
        b4.setIsActive(true);

        LinkedList<Bike> unSortedList = new LinkedList<>();
        LinkedList<Bike> sortedList = new LinkedList<>();

        sortedList.add(b4);

        unSortedList.add(b1);
        cc.sortByCharge(unSortedList);
        Assertions.assertEquals(sortedList, unSortedList);
        Assertions.assertNotSame(sortedList, unSortedList);
        sortedList.add(b3);
        sortedList.add(b2);
        sortedList.add(b1);

        unSortedList.add(b2);
        unSortedList.add(b3);
        unSortedList.add(b4);

        cc.sortByCharge(unSortedList);

        Assertions.assertEquals(sortedList, unSortedList);

        unSortedList.clear();

        unSortedList.add(b2);
        unSortedList.add(b3);
        unSortedList.add(b1);
        unSortedList.add(b4);

        Assertions.assertEquals(sortedList, unSortedList);
    }

    @Test
    public void testcalculateHoursToCompleteChargeOfParkTotal() {
        Bike b1 = new Bike();
        b1.setIdBike(2);
        b1.setIdPark(1);
        b1.setMaxBattery(1.0); //kWh
        b1.setType("electrical");
        b1.setWeight(20.0);
        b1.setBatteryType("a");
        b1.setCurrentBattery(0.1);
        b1.setIsActive(true);

        Bike b2 = new Bike();
        b2.setIdBike(3);
        b2.setIdPark(1);
        b2.setMaxBattery(0.9); //kWh
        b2.setType("electrical");
        b2.setWeight(36.0);
        b2.setBatteryType("a");
        b2.setCurrentBattery(0.25);
        b2.setIsActive(true);

        Bike b3 = new Bike();
        b3.setIdBike(1);
        b3.setIdPark(1);
        b3.setMaxBattery(0.9); //kWh
        b3.setType("electrical");
        b3.setWeight(39.0);
        b3.setBatteryType("a");
        b3.setCurrentBattery(0.35);
        b3.setIsActive(true);

        Bike b4 = new Bike();
        b4.setIdBike(1);
        b4.setIdPark(1);
        b4.setMaxBattery(0.9); //kWh
        b4.setType("electrical");
        b4.setWeight(38.1);
        b4.setBatteryType("a");
        b4.setCurrentBattery(0.41);
        b4.setIsActive(true);

        Park p = new Park();
        p.setActive(Boolean.TRUE);
        p.setIdPark(2);
        p.setIntensity(16.0);
        p.setMaxCapacityElectric(3);
        p.setMaxCapacityStandard(5);
        p.setVoltage(220.0);

        LinkedList<Bike> unSortedList = new LinkedList<>();

        unSortedList.add(b2);
        unSortedList.add(b3);
        unSortedList.add(b1);
        unSortedList.add(b4);

        Double hrs = cc.calculateHoursToCompleteChargeOfParkTotal(unSortedList, p);

        Assertions.assertEquals(hrs, 0.7481060606060606, 0.00005);

        Assertions.assertTrue(cc.calculateHoursToCompleteChargeOfParkTotal(null, null) == 0.0);
        Assertions.assertTrue(cc.calculateHoursToCompleteChargeOfParkTotal(null, p) == 0.0);
        Assertions.assertTrue(cc.calculateHoursToCompleteChargeOfParkTotal(unSortedList, null) == 0.0);

        unSortedList.clear();
        Assertions.assertTrue(cc.calculateHoursToCompleteChargeOfParkTotal(unSortedList, p) == 0.0);
    }

    @Test
    public void testcalculateCurrentBatteryLevel() {
        Bike b123 = new Bike();
        b123.setIdBike(10);
        b123.setIdPark(5);
        b123.setMaxBattery(0.9); //kWh
        b123.setType("Eletrical");
        b123.setWeight(33.0);
        b123.setBatteryType("a");
        b123.setCurrentBattery(0.1);
        b123.setIsActive(true);

        Double expectedkWh = 0.9;

        //0.125 horas
        Double currentLevel = cc.calculateCurrentBatteryLevel(b123, new Date(3600000 / 8), new Date(3600000), 220.0, 16.0);
        Assertions.assertEquals(expectedkWh, currentLevel);

        currentLevel = cc.calculateCurrentBatteryLevel(b123, new Date(3600000 / 8), new Date(3600000), 220.0, 30.0);
        Assertions.assertEquals(expectedkWh, currentLevel);
    }

    @Test
    public void testcalculateHoursToCompleteChargeOfPark() {
        Bike b1 = new Bike();
        b1.setIdBike(2);
        b1.setIdPark(1);
        b1.setMaxBattery(1.0); //kWh
        b1.setType("electrical");
        b1.setWeight(20.0);
        b1.setBatteryType("a");
        b1.setCurrentBattery(0.1);
        b1.setIsActive(true);

        Bike b2 = new Bike();
        b2.setIdBike(3);
        b2.setIdPark(1);
        b2.setMaxBattery(0.9); //kWh
        b2.setType("electrical");
        b2.setWeight(36.0);
        b2.setBatteryType("a");
        b2.setCurrentBattery(0.25);
        b2.setIsActive(true);

        Bike b3 = new Bike();
        b3.setIdBike(1);
        b3.setIdPark(1);
        b3.setMaxBattery(0.9); //kWh
        b3.setType("electrical");
        b3.setWeight(39.0);
        b3.setBatteryType("a");
        b3.setCurrentBattery(0.35);
        b3.setIsActive(true);

        Bike b4 = new Bike();
        b4.setIdBike(1);
        b4.setIdPark(1);
        b4.setMaxBattery(0.9); //kWh
        b4.setType("electrical");
        b4.setWeight(38.1);
        b4.setBatteryType("a");
        b4.setCurrentBattery(0.41);
        b4.setIsActive(true);

        Park p = new Park();
        p.setActive(Boolean.TRUE);
        p.setIdPark(2);
        p.setIntensity(16.0);
        p.setMaxCapacityElectric(3);
        p.setMaxCapacityStandard(5);
        p.setVoltage(220.0);

        LinkedList<Bike> unSortedList = new LinkedList<>();

        unSortedList.add(b4);
        unSortedList.add(b3);
        unSortedList.add(b2);
        unSortedList.add(b1);
        LinkedList<Pair<Bike, Double>> expt = new LinkedList<>();
        expt.add(new Pair<>(b4, 0.5568181818181819));
        expt.add(new Pair<>(b3, 0.05113636363636359));
        expt.add(new Pair<>(b2, 0.056818181818181865));
        expt.add(new Pair<>(b1, 0.0833333333333333));

        List<Pair<Bike, Double>> ret = cc.calculateHoursToCompleteChargeOfPark(unSortedList, p);
        for (int i = 0; i < ret.size(); i++) {
            Assertions.assertEquals(expt.get(i).getKey(), ret.get(i).getKey());
            Assertions.assertEquals(expt.get(i).getValue(), ret.get(i).getValue(), 0.0000005); //Delta
        }
    }
}
