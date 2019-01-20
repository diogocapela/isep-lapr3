package lapr.project.controller;


import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InvoicesControllerMockitoTest {

    static private BikesAPI bikesAPI;
    static private ParksAPI parksAPI;
    static private RidesAPI ridesAPI;
    static private UsersAPI usersAPI;
    static private TouristicPointsAPI tourAPI;
    static private InvoicesAPI invoicesAPI;
    static private ReceiptsAPI receiptsAPI;
    static private RoutesAPI routesAPI;
    static private InvoicesController invoicesController;
    private List<Ride> rideList1;
    private List<Ride> rideList2;
    private List<Ride> rideList3;
    private User user1 = new User();
    private Ride ride11 = new Ride();
    private Ride ride12 = new Ride();
    private User user2 = new User();
    private Ride ride21 = new Ride();
    private Ride ride22 = new Ride();
    private Ride ride23 = new Ride();
    private Bike bike1 = new Bike();
    private Bike bike2 = new Bike();
    private Park park1 = new Park();
    private Park park2 = new Park();
    private Invoice invoice20 = new Invoice();
    private Invoice invoice21 = new Invoice();

    @BeforeAll
    static void startUp() {
    }

    @BeforeEach
    void setUp() {
        bikesAPI = mock(BikesAPI.class);
        parksAPI = mock(ParksAPI.class);
        ridesAPI = mock(RidesAPI.class);
        usersAPI = mock(UsersAPI.class);
        tourAPI = mock(TouristicPointsAPI.class);
        invoicesAPI = mock(InvoicesAPI.class);
        receiptsAPI = mock(ReceiptsAPI.class);
        routesAPI = mock(RoutesAPI.class);
        invoicesController = new InvoicesController(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);

        user1.setIdUser(1);
        user1.setEmail("rpele0@japanpost.jp");
        user1.setUsername("pele0");
        user1.setPassword("Vq1Vf6ov");
        user1.setCreditCardNumber("3538203239798171");
        user1.setHeight(92.74);
        user1.setWeight(115.19);
        user1.setIsAdmin(true);
        ride11.setIdRide(11);
        ride11.setIdUser(1);
        ride11.setCost(0.0);
        ride11.setTimestampStart("2018-8-14 16-16-16");
        ride11.setTimestampFinish("2018-8-14 16-36-16");
        ride12.setIdRide(12);
        ride12.setIdUser(1);
        ride12.setCost(0.0);
        ride12.setTimestampStart("2018-8-15 16-16-16");
        ride12.setTimestampFinish("2018-8-15 19-36-16");
        rideList1 = Arrays.asList(ride11, ride12);

        user2.setIdUser(2);
        user2.setEmail("rpele1@japanpost.jp");
        user2.setUsername("pele1");
        user2.setPassword("Vq1Vf6ov");
        user2.setCreditCardNumber("3538203239798171");
        user2.setHeight(92.74);
        user2.setWeight(115.19);
        user2.setCreditPoints(15);
        user2.setIsAdmin(true);
        bike1.setIdBike(1);
        bike1.setIdPark(2);
        bike1.setDescription("ad");
        bike1.setType("MOUNTAIN");
        bike1.setCurrentBattery(null);
        bike1.setMaxBattery(null);
        bike1.setWeight(6.0);
        bike1.setBatteryType(null);
        bike1.setIsActive(true);
        bike2.setIdBike(2);
        bike2.setDescription("ad");
        bike2.setIdPark(9);
        bike2.setType("MOUNTAIN");
        bike2.setCurrentBattery(null);
        bike2.setMaxBattery(null);
        bike2.setWeight(6.0);
        bike2.setBatteryType(null);
        bike2.setIsActive(true);
        ride21.setIdRide(21);
        ride21.setIdBike(1);
        ride21.setIdUser(2);
        ride21.setCost(2.0);
        ride21.setIdStartPark(1);
        ride21.setIdEndPark(2);
        ride21.setTimestampStart("2018-8-14 16:16:16");
        ride21.setTimestampFinish("2018-8-14 16:36:16");
        ride22.setIdRide(22);
        ride22.setIdBike(2);
        ride22.setIdUser(2);
        ride22.setIdInvoice(20);
        invoice20.setIdInvoice(2);
        invoice20.setStatus("paid");
        ride22.setIdStartPark(1);
        ride22.setIdEndPark(2);
        ride22.setTimestampStart("2018-8-15 16:16:16");
        ride22.setTimestampFinish("2018-8-15 19:36:16");
        ride22.setCost(10.0);
        rideList2 = Arrays.asList(ride21, ride22);
        ride23.setIdRide(23);
        ride23.setIdBike(2);
        ride23.setIdUser(2);
        ride22.setIdInvoice(21);
        invoice21.setIdInvoice(21);
        invoice21.setStatus("pending");
        ride23.setIdStartPark(1);
        ride23.setIdEndPark(2);
        ride23.setTimestampStart("2018-8-12 16:26:16");
        ride23.setTimestampFinish("2018-8-12 18:36:16");
        ride23.setCost(10.0);
        rideList3 = Arrays.asList(ride21, ride22, ride23);
        park1.setIdPark(1);
        park1.setGeoLatitude(37.24003);
        park1.setGeoLongitude(40.134159);
        park1.setGeoAltitude(71.43);
        park1.setMaxCapacityElectric(33);
        park1.setMaxCapacityStandard(18);
        park1.setActive(true);
        park2.setIdPark(2);
        park2.setGeoLatitude(17.312406);
        park2.setGeoLongitude(41.887231);
        park2.setGeoAltitude(17.59);
        park2.setMaxCapacityElectric(24);
        park2.setMaxCapacityStandard(30);
        park2.setActive(true);
    }

    @Test
    void generateInvoiceForUserTest() {
        List<String> list = new LinkedList<>();
        assertEquals(0, invoicesController.generateInvoiceForUser(1, null, list));

        list = new LinkedList<>();
        when(usersAPI.getUserByEmail("rpele0@japanpost.jp")).thenReturn(user1);
        when(usersAPI.getUserByUsername("pele0")).thenReturn(user1);
        assertEquals(0, invoicesController.generateInvoiceForUser(1, "pele0", list));

        list = new LinkedList<>();
        when(ridesAPI.getAllUserRidesWithoutInvoice(1)).thenReturn(rideList1);
        when(bikesAPI.getBikeById(1)).thenReturn(bike1);
        when(bikesAPI.getBikeById(2)).thenReturn(bike2);
        assertEquals(0, invoicesController.generateInvoiceForUser(1, "pele0", list));
        assertNotNull(list);

        when(usersAPI.getUserByEmail("rpele1@japanpost.jp")).thenReturn(user2);
        when(usersAPI.getUserByUsername("pele1")).thenReturn(user2);
        when(ridesAPI.getAllUserRidesWithoutInvoice(2)).thenReturn(rideList2);
        when(bikesAPI.getBikeById(1)).thenReturn(bike1);
        when(bikesAPI.getBikeById(2)).thenReturn(bike2);
        when(parksAPI.getParkById(1)).thenReturn(park1);
        when(parksAPI.getParkById(2)).thenReturn(park2);
        list = new LinkedList<>();
        Integer previous = user2.getCreditPoints();
        Double cost1 = invoicesController.generateInvoiceForUser(8, "pele1", list);
        assertNotNull(cost1);
        assertEquals(8, list.size());
        assertTrue(list.get(0).contains(user2.getUsername()));
        assertTrue(list.get(1).contains(previous.toString()));
        assertTrue(list.get(2).contains("0"));
        assertTrue(list.get(3).contains(Integer.toString(Math.abs(user2.getCreditPoints() - previous))));
        assertTrue(list.get(4).contains(user2.getCreditPoints().toString()));
        assertTrue(list.get(5).contains(cost1.toString()));

        list = new LinkedList<>();
        park2.setGeoAltitude(park1.getGeoAltitude() + 30);
        previous = user2.getCreditPoints();
        cost1 = invoicesController.generateInvoiceForUser(8, "pele1", list);
        assertNotNull(cost1);
        assertEquals(8, list.size());
        assertTrue(list.get(0).contains(user2.getUsername()));
        assertTrue(list.get(1).contains(previous.toString()));
        assertTrue(list.get(2).contains("10"));
        assertTrue(list.get(3).contains(Integer.toString(Math.abs(user2.getCreditPoints() - (previous + 10)))));
        assertTrue(list.get(4).contains(user2.getCreditPoints().toString()));
        assertTrue(list.get(5).contains(cost1.toString()));
        assertTrue(list.get(6).contains(ride21.getTimestampStart()));
        assertTrue(list.get(6).contains(ride21.getTimestampFinish()));
        assertTrue(list.get(7).contains(ride22.getTimestampStart()));
        assertTrue(list.get(7).contains(ride22.getTimestampFinish()));
    }

    @Test
    void getUserDebtTest() {
        List<String> list = new LinkedList<>();
        assertEquals(0, invoicesController.getUserDebt(null, list));

        list = new LinkedList<>();
        when(usersAPI.getUserByEmail("rpele0@japanpost.jp")).thenReturn(user1);
        when(usersAPI.getUserByUsername("pele0")).thenReturn(user1);
        assertEquals(0, invoicesController.getUserDebt("pele0", list));

        list = new LinkedList<>();
        when(ridesAPI.getAllUserRidesWithoutInvoice(1)).thenReturn(rideList1);
        when(bikesAPI.getBikeById(1)).thenReturn(bike1);
        when(bikesAPI.getBikeById(2)).thenReturn(bike2);
        assertEquals(0, invoicesController.getUserDebt("pele0", list));
        assertNotNull(list);

        when(usersAPI.getUserByEmail("rpele1@japanpost.jp")).thenReturn(user2);
        when(usersAPI.getUserByUsername("pele1")).thenReturn(user2);
        when(usersAPI.getUserById(2)).thenReturn(user2);
        when(ridesAPI.getAllRides()).thenReturn(rideList2);
        when(bikesAPI.getBikeById(1)).thenReturn(bike1);
        when(bikesAPI.getBikeById(2)).thenReturn(bike2);
        when(parksAPI.getParkById(1)).thenReturn(park1);
        when(parksAPI.getParkById(2)).thenReturn(park2);
        list = new LinkedList<>();
        Double cost1 = invoicesController.getUserDebt("pele1", list);
        assertNotNull(cost1);
        assertFalse(list.isEmpty());
        assertEquals("pele1", list.get(0));
        for (int i = 0; i < list.size() - 2; i++) {
            assertTrue(Utils.getTimeDifference(list.get(i + 1).split(";")[1], list.get(i + 2).split(";")[1]) < 0);
        }

        assertEquals(0.0, invoicesController.getUserDebt(null, list));
        assertEquals(0.0, invoicesController.getUserDebt("nope", list));

        when(ridesAPI.getAllRides()).thenReturn(rideList3);
        when(invoicesAPI.getInvoiceById(20)).thenReturn(invoice20);
        when(invoicesAPI.getInvoiceById(21)).thenReturn(invoice21);
        when(ridesAPI.getAllRides()).thenReturn(rideList3);
        list = new LinkedList<>();
        cost1 = invoicesController.getUserDebt("pele1", list);
        assertNotNull(cost1);
        assertFalse(list.isEmpty());
        assertEquals("pele1", list.get(0));
        for (int i = 0; i < list.size() - 2; i++) {
            assertTrue(Utils.getTimeDifference(list.get(i + 1).split(";")[1], list.get(i + 2).split(";")[1]) < 0);
        }
    }
}
