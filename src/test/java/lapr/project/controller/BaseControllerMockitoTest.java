package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * ⚠️ DO NOT TOUCH THIS FILE, EVER!
 * ⚠️ IF YOUR PROJECT HAS ERROS, RUN MAVEN CLEAN, THEN MAVEN COMPIL
 * ⚠️ DO NOT TOUCH!
 */
public class BaseControllerMockitoTest {


    static private BaseController controller;

    @BeforeAll
    static void setup() {
        BikesAPI bikesAPI = mock(BikesAPI.class);
        ParksAPI parksAPI = mock(ParksAPI.class);
        RidesAPI ridesAPI = mock(RidesAPI.class);
        UsersAPI usersAPI = mock(UsersAPI.class);
        InvoicesAPI invoicesAPI = mock(InvoicesAPI.class);
        ReceiptsAPI receiptsAPI = mock(ReceiptsAPI.class);
        RoutesAPI routesAPI = mock(RoutesAPI.class);
        TouristicPointsAPI touristicPointsAPI = mock(TouristicPointsAPI.class);
        controller = new BaseController(bikesAPI, parksAPI, ridesAPI, usersAPI, touristicPointsAPI, invoicesAPI, receiptsAPI, routesAPI);
    }

    @Test
    void testAddBike() {
        assertEquals(new Integer(0), controller.addBike(new Bike()));
    }

    @Test
    void testAddPark() {
        assertEquals(new Integer(0), controller.addPark(new Park()));
    }

    @Test
    void testAddRide() {
        assertEquals(new Integer(0), controller.addRide(new Ride()));
    }

    @Test
    void testAddUser() {
        assertEquals(new Integer(0), controller.addUser(new User()));
    }

    @Test
    void testAddTouristicPoint() {
        assertEquals(new Integer(0), controller.addTouristicPoint(new TouristicPoint()));
    }

    @Test
    void testUpdateBike() {
        assertEquals(new Integer(0), controller.updateBike(new Bike()));
    }

    @Test
    void testUpdatePark() {
        assertEquals(new Integer(0), controller.updatePark(new Park()));
    }

    @Test
    void testUpdateRide() {
        assertEquals(new Integer(0), controller.updateRide(new Ride()));
    }

    @Test
    void testUpdateUser() {
        assertEquals(new Integer(0), controller.updateUser(new User()));
    }

    @Test
    void testDeactivateBike() {
        assertEquals(new Integer(0), controller.deactivateBike(1));
    }

    @Test
    void testDeactivatePark() {
        assertEquals(new Integer(0), controller.deactivatePark(1));
    }

    @Test
    void testRemoveBikeById() {
        assertEquals(new Integer(0), controller.removeBikeById(1));
    }

    @Test
    void testRemoveParkById() {
        assertEquals(new Integer(0), controller.removeParkById(1));
    }

    @Test
    void testRemoveRideById() {
        assertEquals(new Integer(0), controller.removeRideById(1));
    }

    @Test
    void testRemoveUserById() {
        assertEquals(new Integer(0), controller.removeUserById(1));
    }

    @Test
    void testRemoveUserByEmail() {
        assertEquals(new Integer(0), controller.removeUserByEmail("test@example.com"));
    }

    @Test
    void testRemoveTouristicById() {
        assertEquals(new Integer(0), controller.removeTouristicPointById(1));
    }

    @Test
    void testGetAllBikes() {
        assertEquals(0, controller.getAllBikes().size());
    }

    @Test
    void testGetAllParks() {
        assertEquals(0, controller.getAllParks().size());
    }

    @Test
    void testGetAllRides() {
        assertEquals(0, controller.getAllRides().size());
    }

    @Test
    void testGetAllUsers() {
        assertEquals(0, controller.getAllUsers().size());
    }

    @Test
    void testGetAllTouristicPoints() {
        assertEquals(0, controller.getAllTouristicPoints().size());
    }

    @Test
    void testGetBikeById() {
        assertEquals(null, controller.getBikeById(1));
    }

    @Test
    void testGetParkById() {
        assertEquals(null, controller.getParkById(1));
    }

    @Test
    void testGetRideById() {
        assertEquals(null, controller.getRideById(1));
    }

    @Test
    void testGetUserById() {
        assertEquals(null, controller.getUserById(1));
    }

    @Test
    void testGetUserByEmail() {
        assertEquals(null, controller.getUserByEmail("test@example.com"));
    }

    @Test
    void testGetTouristicPointById() {
        assertEquals(null, controller.getTouristicPointById(1));
    }

    @Test
    void testGetAllElectricalActiveBikesFromPark() {
        assertEquals(new ArrayList<Bike>(), controller.getAllElectricalActiveBikesFromPark(1));
    }

    @Test
    void testGetAllMountainActiveBikesFromPark() {
        assertEquals(new ArrayList<Bike>(), controller.getAllMountainActiveBikesFromPark(1));
    }

    @Test
    void testGetAllRoadActiveBikesFromPark() {
        assertEquals(new ArrayList<Bike>(), controller.getAllRoadActiveBikesFromPark(1));
    }

    @Test
    void testGetAllUnfinishedRides() {
        assertEquals(new ArrayList<Ride>(), controller.getAllUnfinishedRides());
    }


    @Test
    void testGetUnfinishedRideOfUser() {
        assertEquals(null, controller.getUnfinishedRideOfUser(1));
    }


}
