package lapr.project.controller;

import lapr.project.data.*;
import lapr.project.model.*;
import lapr.project.utils.Utils;
import lapr.project.utils.WindUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ⚠️ DO NOT TOUCH THIS FILE, EVER!
 * ⚠️ IF YOUR PROJECT HAS ERROS, RUN MAVEN CLEAN, THEN MAVEN COMPIL
 * ⚠️ DO NOT TOUCH!
 */
public class ActionsControllerMockitoTest {

    static private ActionsController controller;
    static private RequestBikeController requestBikeController;
    static private LeaveBikeController leaveBikeController;
    static private ChargingController chargingController;
    static private BikesAPI bikesAPI;
    static private ParksAPI parksAPI;
    static private RidesAPI ridesAPI;
    static private UsersAPI usersAPI;
    static private TouristicPointsAPI tourAPI;
    static private InvoicesAPI invoicesAPI;
    static private ReceiptsAPI receiptsAPI;
    static private RoutesAPI routesAPI;

    private List<Park> allParks;
    private Park park1 = new Park();
    private Park park2 = new Park();
    private Park park3 = new Park();
    private Park park4 = new Park();
    private Park park5 = new Park();
    private Park park6 = new Park();
    private Park park7 = new Park();
    private Park park8 = new Park();
    private Park park9 = new Park();
    private Park park10 = new Park();
    // parks for electrical energy
    private Park park11 = new Park();
    private Park park12 = new Park();
    private Park park13 = new Park();

    private List<TouristicPoint> allTourPoints;
    private TouristicPoint tourPoint1 = new TouristicPoint();
    private TouristicPoint tourPoint2 = new TouristicPoint();
    private TouristicPoint tourPoint3 = new TouristicPoint();
    private TouristicPoint tourPoint4 = new TouristicPoint();
    private TouristicPoint tourPoint5 = new TouristicPoint();


    private User user1 = new User();
    private User user2 = new User();
    private User user3 = new User();
    private User user4 = new User();
    private User user5 = new User();
    private User user6 = new User();
    private User user7 = new User();
    private User user8 = new User();
    private User user9 = new User();
    private User user10 = new User();

    private Bike bike1 = new Bike();
    private Bike bike2 = new Bike();
    private Bike bike3 = new Bike();
    private Bike bike4 = new Bike();
    private Bike bike5 = new Bike();
    private Bike bike6 = new Bike();
    private Bike bike7 = new Bike();
    private Bike bike8 = new Bike();
    private Bike bike9 = new Bike();
    private Bike bike10 = new Bike();
    private Bike bike11 = new Bike();

    private Ride ride1 = new Ride();
    private Ride ride2 = new Ride();
    private Ride ride3 = new Ride();
    private Ride ride4 = new Ride();
    private Ride ride5 = new Ride();
    private Ride ride6 = new Ride();
    private Ride ride7 = new Ride();
    private Ride ride8 = new Ride();
    private Ride ride9 = new Ride();

    private Bike mntnBike1 = new Bike();
    private Bike roadBike1 = new Bike();
    private Bike elecBike1 = new Bike();

    private List<Bike> mntnBikes = new LinkedList<>();
    private List<Bike> roadBikes = new LinkedList<>();
    private List<Bike> elecBikes = new LinkedList<>();

    private Route route1 = new Route();
    private Route route2 = new Route();

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
        controller = new ActionsController(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);
        requestBikeController = new RequestBikeController(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);
        leaveBikeController = new LeaveBikeController(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);
        chargingController = new ChargingController(bikesAPI, parksAPI, ridesAPI, usersAPI, tourAPI, invoicesAPI, receiptsAPI, routesAPI);

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

        park3.setIdPark(3);
        park3.setGeoLatitude(83.199101);
        park3.setGeoLongitude(88.788584);
        park3.setGeoAltitude(20.59);
        park3.setMaxCapacityElectric(36);
        park3.setMaxCapacityStandard(47);
        park3.setActive(true);

        park4.setIdPark(4);
        park4.setGeoLatitude(76.994929);
        park4.setGeoLongitude(30.457319);
        park4.setGeoAltitude(59.39);
        park4.setMaxCapacityElectric(16);
        park4.setMaxCapacityStandard(42);
        park4.setActive(true);

        park5.setIdPark(5);
        park5.setGeoLatitude(43.840578);
        park5.setGeoLongitude(55.34291);
        park5.setGeoAltitude(79.05);
        park5.setMaxCapacityElectric(22);
        park5.setMaxCapacityStandard(27);
        park5.setActive(true);

        park6.setIdPark(6);
        park6.setGeoLatitude(69.887512);
        park6.setGeoLongitude(72.96357);
        park6.setGeoAltitude(40.13);
        park6.setMaxCapacityElectric(17);
        park6.setMaxCapacityStandard(13);
        park6.setActive(true);

        park7.setIdPark(7);
        park7.setGeoLatitude(45.359339);
        park7.setGeoLongitude(73.849242);
        park7.setGeoAltitude(58.67);
        park7.setMaxCapacityElectric(38);
        park7.setMaxCapacityStandard(25);
        park7.setActive(true);

        park8.setIdPark(8);
        park8.setGeoLatitude(46.496049);
        park8.setGeoLongitude(61.032356);
        park8.setGeoAltitude(47.32);
        park8.setMaxCapacityElectric(36);
        park8.setMaxCapacityStandard(36);
        park8.setActive(true);

        park9.setIdPark(9);
        park9.setGeoLatitude(27.807557);
        park9.setGeoLongitude(80.360704);
        park9.setGeoAltitude(22.82);
        park9.setMaxCapacityElectric(50);
        park9.setMaxCapacityStandard(38);
        park9.setActive(true);

        park10.setIdPark(10);
        park10.setGeoLatitude(39.630354);
        park10.setGeoLongitude(48.266987);
        park10.setGeoAltitude(81.88);
        park10.setMaxCapacityElectric(15);
        park10.setMaxCapacityStandard(17);
        park10.setActive(true);

        allParks = Arrays.asList(park1, park2, park3, park4, park5, park6, park7, park8, park9, park10);

        // https://www.latlong.net/ for coordinates
        // castelo queijo
        park11.setIdPark(11); // 41.167331,-8.688336
        park11.setGeoLatitude(41.167331);
        park11.setGeoLongitude(-8.688336);
        park11.setGeoAltitude(10.0);
        park11.setActive(true);
        // farol foz
        park12.setIdPark(12); // 41.150004,-8.676257
        park12.setGeoLatitude(41.150004);
        park12.setGeoLongitude(-8.676257);
        park12.setGeoAltitude(10.0);
        park12.setActive(true);
        // rotunda boavista
        park13.setIdPark(13); // 41.157905,-8.629128
        park13.setGeoLatitude(41.157905);
        park13.setGeoLongitude(-8.629128);
        park13.setGeoAltitude(105.0);
        park13.setActive(true);

        //

        tourPoint1.setIdTouristicPoint(1);
        tourPoint1.setGeoLatitude(50.630354);
        tourPoint1.setGeoLongitude(20.266987);
        tourPoint1.setGeoAltitude(20.88);

        tourPoint2.setIdTouristicPoint(2);
        tourPoint2.setGeoLatitude(39.630354);
        tourPoint2.setGeoLongitude(48.266987);
        tourPoint2.setGeoAltitude(99.50);

        tourPoint3.setIdTouristicPoint(3);
        tourPoint3.setGeoLatitude(67.123354);
        tourPoint3.setGeoLongitude(89.466987);
        tourPoint3.setGeoAltitude(15.88);

        tourPoint4.setIdTouristicPoint(4);
        tourPoint4.setGeoLatitude(58.340354);
        tourPoint4.setGeoLongitude(43.236987);
        tourPoint4.setGeoAltitude(50.00);

        tourPoint5.setIdTouristicPoint(5);
        tourPoint5.setGeoLatitude(32.676254);
        tourPoint5.setGeoLongitude(14.432987);
        tourPoint5.setGeoAltitude(7.22);

        allTourPoints = Arrays.asList(tourPoint1, tourPoint2, tourPoint3, tourPoint4, tourPoint5);

        //

        user1.setIdUser(1);
        user1.setEmail("rpele0@japanpost.jp");
        user1.setPassword("Vq1Vf6ov");
        user1.setCreditCardNumber("3538203239798171");
        user1.setHeight(92.74);
        user1.setWeight(115.19);
        user1.setIsAdmin(true);
        user1.setAvgSpeed(20.0 * 1000 / 3600);

        user2.setIdUser(2);
        user2.setEmail("elaflin1@pen.io");
        user2.setPassword("QhUCaXB10");
        user2.setCreditCardNumber("3584424481215291");
        user2.setHeight(146.4);
        user2.setWeight(132.9);
        user2.setIsAdmin(true);

        user3.setIdUser(3);
        user3.setEmail("ejonin2@hibu.com");
        user3.setPassword("uF0vod0Ym");
        user3.setCreditCardNumber("3569302036892237");
        user3.setHeight(167.11);
        user3.setWeight(110.5);
        user3.setIsAdmin(true);

        user4.setIdUser(4);
        user4.setEmail("rbradburne3@upenn.edu");
        user4.setPassword("rOQ2ObRqox");
        user4.setCreditCardNumber("5038905519576032");
        user4.setHeight(221.81);
        user4.setWeight(107.52);
        user4.setIsAdmin(true);

        user5.setIdUser(5);
        user5.setEmail("aalderman4@4shared.com");
        user5.setPassword("ggsqTjU");
        user5.setCreditCardNumber("6709431263859250337");
        user5.setHeight(160.07);
        user5.setWeight(144.71);
        user5.setIsAdmin(true);

        user6.setIdUser(6);
        user6.setEmail("awingrove5@msu.edu");
        user6.setPassword("akLMNmNW");
        user6.setCreditCardNumber("5602230586551685");
        user6.setHeight(149.67);
        user6.setWeight(102.06);
        user6.setIsAdmin(true);

        user7.setIdUser(7);
        user7.setEmail("gtofpik6@squarespace.com");
        user7.setPassword("t53QXVk5");
        user7.setCreditCardNumber("5602235473045436");
        user7.setHeight(199.71);
        user7.setWeight(45.75);
        user7.setIsAdmin(true);

        user8.setIdUser(8);
        user8.setEmail("afarraway7@gnu.org");
        user8.setPassword("JaOViO6bK");
        user8.setCreditCardNumber("3531702517070283");
        user8.setHeight(123.9);
        user8.setWeight(142.08);
        user8.setIsAdmin(true);

        user9.setIdUser(9);
        user9.setEmail("sdudhill8@buzzfeed.com");
        user9.setPassword("D4wIeW");
        user9.setCreditCardNumber("3543135873818118");
        user9.setHeight(201.57);
        user9.setWeight(149.77);
        user9.setIsAdmin(true);

        user10.setIdUser(10);
        user10.setEmail("mcuckoo9@reference.com");
        user10.setPassword("Cn9wSI4MQ");
        user10.setCreditCardNumber("337941236489117");
        user10.setHeight(181.04);
        user10.setWeight(120.9);
        user10.setIsAdmin(true);

        //

        bike1.setIdBike(1);
        bike1.setIdPark(2);
        bike1.setType("MOUNTAIN");
        bike1.setCurrentBattery(null);
        bike1.setMaxBattery(null);
        bike1.setWeight(6.0);
        bike1.setBatteryType(null);
        bike1.setIsActive(true);
        bike1.setDragCoefficient(1.05);

        bike2.setIdBike(2);
        bike2.setIdPark(9);
        bike2.setType("MOUNTAIN");
        bike2.setCurrentBattery(null);
        bike2.setMaxBattery(null);
        bike2.setWeight(6.0);
        bike2.setBatteryType(null);
        bike2.setIsActive(true);

        bike3.setIdBike(3);
        bike3.setIdPark(3);
        bike3.setType("MOUNTAIN");
        bike3.setCurrentBattery(null);
        bike3.setMaxBattery(null);
        bike3.setWeight(6.0);
        bike3.setBatteryType(null);
        bike3.setIsActive(true);

        bike4.setIdBike(4);
        bike4.setIdPark(8);
        bike4.setType("ROAD");
        bike4.setCurrentBattery(null);
        bike4.setMaxBattery(null);
        bike4.setWeight(4.0);
        bike4.setBatteryType(null);
        bike4.setIsActive(true);

        bike5.setIdBike(5);
        bike5.setIdPark(5);
        bike5.setType("ROAD");
        bike5.setCurrentBattery(null);
        bike5.setMaxBattery(null);
        bike5.setWeight(4.0);
        bike5.setBatteryType(null);
        bike5.setIsActive(true);

        bike6.setIdBike(6);
        bike6.setIdPark(2);
        bike6.setType("ROAD");
        bike6.setCurrentBattery(null);
        bike6.setMaxBattery(null);
        bike6.setWeight(4.0);
        bike6.setBatteryType(null);
        bike6.setIsActive(true);
        bike6.setDragCoefficient(1.15);

        bike7.setIdBike(7);
        bike7.setIdPark(2);
        bike7.setType("ELECTRICAL");
        bike7.setCurrentBattery(89.38);
        bike7.setMaxBattery(15.2);
        bike7.setWeight(8.0);
        bike7.setBatteryType(null);
        bike7.setIsActive(true);
        bike7.setDragCoefficient(1.15);

        bike8.setIdBike(8);
        bike8.setIdPark(3);
        bike8.setType("ELECTRICAL");
        bike8.setCurrentBattery(12.99);
        bike8.setMaxBattery(98.47);
        bike8.setWeight(8.0);
        bike8.setBatteryType(null);
        bike8.setIsActive(true);

        bike9.setIdBike(9);
        bike9.setIdPark(9);
        bike9.setType("ELECTRICAL");
        bike9.setCurrentBattery(45.26);
        bike9.setMaxBattery(19.27);
        bike9.setWeight(8.0);
        bike9.setBatteryType(null);
        bike9.setIsActive(true);

        bike10.setIdBike(10);
        bike10.setIdPark(6);
        bike10.setType("ELECTRICAL");
        bike10.setCurrentBattery(30.57);
        bike10.setMaxBattery(52.89);
        bike10.setWeight(8.0);
        bike10.setBatteryType(null);
        bike10.setIsActive(true);

        bike11.setIdBike(11);
        bike11.setIdPark(3);
        bike11.setType("ELECTRICAL");
        bike11.setCurrentBattery(5.96);
        bike11.setMaxBattery(30.00);
        bike11.setWeight(15.0);
        bike11.setBatteryType("30.00");
        bike11.setIsActive(true);


        //

        ride1.setIdRide(1);
        ride1.setIdBike(1);
        ride1.setIdUser(1);
        ride1.setIdStartPark(1);
        ride1.setIdEndPark(2);
        ride1.setTimestampStart("10-10-2018 16:16:16");
        ride1.setTimestampFinish("10-10-2018 18:16:16");
        ride1.setCost(41.0);

        ride2.setIdRide(2);
        ride2.setIdBike(2);
        ride2.setIdUser(2);
        ride2.setIdStartPark(2);
        ride2.setIdEndPark(3);
        ride2.setTimestampStart("11-10-2018 16:16:16");
        ride2.setTimestampFinish("11-10-2018 18:16:16");
        ride2.setCost(32.5);

        ride3.setIdRide(3);
        ride3.setIdBike(3);
        ride3.setIdUser(3);
        ride3.setIdStartPark(3);
        ride3.setIdEndPark(4);
        ride3.setTimestampStart("12-10-2018 16:16:16");
        ride3.setTimestampFinish("12-10-2018 18:16:16");
        ride3.setCost(23.5);

        ride4.setIdRide(4);
        ride4.setIdBike(4);
        ride4.setIdUser(4);
        ride4.setIdStartPark(4);
        ride4.setIdEndPark(null);
        ride4.setTimestampStart("13-10-2018 16:16:16");
        ride4.setTimestampFinish(null);
        ride4.setCost(0.0);

        ride5.setIdRide(5);
        ride5.setIdBike(5);
        ride5.setIdUser(5);
        ride5.setIdStartPark(5);
        ride5.setIdEndPark(null);
        ride5.setTimestampStart("14-10-2018 16:16:16");
        ride5.setTimestampFinish(null);
        ride5.setCost(0.0);

        ride6.setIdRide(6);
        ride6.setIdBike(6);
        ride6.setIdUser(7);
        ride6.setIdStartPark(park11.getIdPark());
        ride6.setIdEndPark(park12.getIdPark());
        ride6.setTimestampStart("14-10-2018 16:16:16");
        ride6.setTimestampFinish("14-10-2018 18:16:16");
        ride6.setCost(0.0);

        ride7.setIdRide(7);
        ride7.setIdBike(6);
        ride7.setIdUser(7);
        ride7.setIdStartPark(park11.getIdPark());
        ride7.setIdEndPark(park13.getIdPark());
        ride7.setTimestampStart("14-10-2018 16:16:16");
        ride7.setTimestampFinish("14-10-2018 18:16:16");
        ride7.setCost(0.0);

        ride8.setIdRide(8);
        ride8.setIdBike(7);
        ride8.setIdUser(7);
        ride8.setIdStartPark(park11.getIdPark());
        ride8.setIdEndPark(park13.getIdPark());
        ride8.setTimestampStart("14-10-2018 16:16:16");
        ride8.setTimestampFinish("14-10-2018 18:16:16");
        ride8.setCost(0.0);

        ride9.setIdRide(9);
        ride9.setIdBike(7);
        ride9.setIdUser(8);
        ride9.setIdStartPark(park11.getIdPark());
        ride9.setIdEndPark(park13.getIdPark());
        ride9.setTimestampStart("14-10-2018 16:16:16");
        ride9.setTimestampFinish("14-10-2018 18:16:16");
        ride9.setCost(0.0);

        mntnBike1.setIdBike(1);
        mntnBike1.setIdPark(2);
        mntnBike1.setType(BikeType.MOUNTAIN.toString());
        mntnBike1.setIsActive(true);

        roadBike1.setIdBike(2);
        roadBike1.setIdPark(2);
        roadBike1.setType(BikeType.ROAD.toString());
        roadBike1.setIsActive(true);

        elecBike1.setIdBike(3);
        elecBike1.setIdPark(2);
        elecBike1.setType(BikeType.ELECTRICAL.toString());
        elecBike1.setCurrentBattery(1000.0);
        elecBike1.setCurrentBattery(2000.0);
        elecBike1.setIsActive(true);

        mntnBikes.add(mntnBike1);
        roadBikes.add(roadBike1);
        elecBikes.add(elecBike1);

        route1.setLatitudeA(park11.getGeoLatitude());
        route1.setLongitudeA(park11.getGeoLongitude());
        route1.setLatitudeB(park12.getGeoLatitude());
        route1.setLongitudeB(park12.getGeoLongitude());
        route1.setWindSpeed(0.0);
        route1.setWindDirection(WindUtils.calculateBearingBetweenLocations(park11, park12));
        route1.setRouteDirection("bi");
        route1.setAerodynamicCoefficient(0.0053);

        route2.setLatitudeA(park11.getGeoLatitude());
        route2.setLongitudeA(park11.getGeoLongitude());
        route2.setLatitudeB(park13.getGeoLatitude());
        route2.setLongitudeB(park13.getGeoLongitude());
        route2.setWindSpeed(0.0);
        route2.setWindDirection(WindUtils.calculateBearingBetweenLocations(park11, park13));
        route2.setRouteDirection("bi");
        route2.setAerodynamicCoefficient(0.0053);
    }

    @Test
    void testGetAvailableBikesOfPark() {
        assertNull(controller.getAvailableBikesOfPark(2));

        when(parksAPI.getParkById(2)).thenReturn(park2);
        when(bikesAPI.getAllMountainActiveBikesFromPark(2)).thenReturn(mntnBikes);
        when(bikesAPI.getAllRoadActiveBikesFromPark(2)).thenReturn(roadBikes);
        when(bikesAPI.getAllElectricalActiveBikesFromPark(2)).thenReturn(elecBikes);

        List<Bike> expectedList1 = new LinkedList<>();
        List<Bike> expectedList2 = new LinkedList<>();
        List<Bike> expectedList3 = new LinkedList<>();
        expectedList1.add(roadBike1);
        expectedList2.add(mntnBike1);
        expectedList3.add(elecBike1);
        Map<BikeType, List<Bike>> expected = new EnumMap<>(BikeType.class);
        expected.put(BikeType.ROAD, expectedList1);
        expected.put(BikeType.MOUNTAIN, expectedList2);
        expected.put(BikeType.ELECTRICAL, expectedList3);
        assertEquals(expected, controller.getAvailableBikesOfPark(2));
    }

    @Test
    void testGetDistanceFromUserToPark() {
        assertEquals(new Double(0.0), controller.getDistanceFromUserToPark(10.0, 10.0, 0D, 1));

        when(parksAPI.getParkById(1)).thenReturn(park1);

        double expected = 4275566.15;
        assertTrue(Math.abs(expected - controller.getDistanceFromUserToPark(10.0, 10.0, 0D, 1)) < 0.01);
    }

    @Test
    void testGetFreeSpacesOfPark() {
        assertNull(controller.getFreeSpacesOfPark(1));

        when(parksAPI.getParkById(1)).thenReturn(park1);
        Map<BikeType, Integer> expected = new EnumMap<>(BikeType.class);
        expected.put(BikeType.ROAD, park1.getMaxCapacityStandard());
        expected.put(BikeType.MOUNTAIN, park1.getMaxCapacityStandard());
        expected.put(BikeType.ELECTRICAL, park1.getMaxCapacityElectric());
        assertEquals(expected, controller.getFreeSpacesOfPark(1));

        // electric bike change
        when(parksAPI.getParkById(2)).thenReturn(park2);
        when(bikesAPI.getBikeById(1)).thenReturn(bike1);
        List<Bike> bikes = new LinkedList<>();
        bikes.add(bike1);
        when(bikesAPI.getAllMountainActiveBikesFromPark(1)).thenReturn(bikes);
        when(usersAPI.getUserById(1)).thenReturn(user1);

        requestBikeController.unlockBike(1, 1, 2);
        bike1.setIdPark(0);
        leaveBikeController.leaveBike(1, 1, 1);

        expected.put(BikeType.ROAD, park1.getMaxCapacityStandard() - 1);
        expected.put(BikeType.MOUNTAIN, park1.getMaxCapacityStandard() - 1);
        expected.put(BikeType.ELECTRICAL, park1.getMaxCapacityElectric());
        assertEquals(expected, controller.getFreeSpacesOfPark(1));

        // mountain bike change
        when(bikesAPI.getBikeById(7)).thenReturn(bike7);
        List<Bike> bikes2 = new LinkedList<>();
        bikes2.add(bike7);
        when(bikesAPI.getAllElectricalActiveBikesFromPark(1)).thenReturn(bikes2);

        requestBikeController.unlockBike(1, 7, 2);
        bike7.setIdPark(0);
        leaveBikeController.leaveBike(1, 7, 1);

        expected.put(BikeType.ROAD, park1.getMaxCapacityStandard() - 1);
        expected.put(BikeType.MOUNTAIN, park1.getMaxCapacityStandard() - 1);
        expected.put(BikeType.ELECTRICAL, park1.getMaxCapacityElectric() - 1);
        assertEquals(expected, controller.getFreeSpacesOfPark(1));

        // road bike change
        when(bikesAPI.getBikeById(6)).thenReturn(bike6);
        List<Bike> bikes3 = new LinkedList<>();
        bikes3.add(bike6);
        when(bikesAPI.getAllRoadActiveBikesFromPark(1)).thenReturn(bikes3);

        requestBikeController.unlockBike(1, 6, 2);
        bike6.setIdPark(0);
        leaveBikeController.leaveBike(1, 6, 1);

        expected.put(BikeType.ROAD, park1.getMaxCapacityStandard() - 2);
        expected.put(BikeType.MOUNTAIN, park1.getMaxCapacityStandard() - 2);
        expected.put(BikeType.ELECTRICAL, park1.getMaxCapacityElectric() - 1);
        assertEquals(expected, controller.getFreeSpacesOfPark(1));
    }

    @Test
    void testCalculateCaloriesBurntBetweenParks() {
        /*Teste a ser revisto*/
        when(bikesAPI.getBikeById(6)).thenReturn(bike6);
        when(bikesAPI.getBikeById(7)).thenReturn(bike7);
        when(usersAPI.getUserById(7)).thenReturn(user7);
        when(usersAPI.getUserById(8)).thenReturn(user8);
        when(parksAPI.getParkById(11)).thenReturn(park11);
        when(parksAPI.getParkById(12)).thenReturn(park12);
        when(parksAPI.getParkById(13)).thenReturn(park13);
        when(routesAPI.getRouteByCoordinates(41.167331, -8.688336, 41.150004, -8.676257)).thenReturn(route1);
        when(routesAPI.getRouteByCoordinates(41.167331, -8.688336, 41.157905, -8.629128)).thenReturn(route2);

        //teste à mesma viagem vento favoravel vs. vento contrario
        route1.setWindSpeed(10.0);
        Double result1 = controller.calculateCaloriesBurntBetweenParks(ride6);
        route1.setWindSpeed(-10.0);
        Double result2 = controller.calculateCaloriesBurntBetweenParks(ride6);
        assertTrue(result1 < result2);

        //teste duas viagens uma mais exigente do que outra
        route1.setWindSpeed(10.0);
        route2.setWindSpeed(10.0);
        result1 = controller.calculateCaloriesBurntBetweenParks(ride6);
        result2 = controller.calculateCaloriesBurntBetweenParks(ride7);
        assertTrue(result1 < result2);

        //teste duas bikes diferentes - road vs electrical
        result1 = controller.calculateCaloriesBurntBetweenParks(ride7);
        result2 = controller.calculateCaloriesBurntBetweenParks(ride8);
        assertTrue(result1 > result2);

        //teste dois users um mais pesado do que o outro
        result1 = controller.calculateCaloriesBurntBetweenParks(ride8);
        result2 = controller.calculateCaloriesBurntBetweenParks(ride9);
        assertTrue(result1 > result2);

        //teste dois users um mais pesado do que o outro
        when(bikesAPI.getBikeById(1)).thenReturn(bike1);
        when(parksAPI.getParkById(1)).thenReturn(park1);
        when(parksAPI.getParkById(2)).thenReturn(park2);
        when(ridesAPI.getRideById(1)).thenReturn(ride1);
        when(routesAPI.getRouteByCoordinates(park1.getGeoLatitude(), park1.getGeoLongitude(), park2.getGeoLatitude(), park2.getGeoLongitude())).thenReturn(route1);
        route1.setWindSpeed(10.0);
        double result3 = controller.calculateCaloriesBurntBetweenParks(ride1);
        route1.setWindSpeed(-10.0);
        double result4 = controller.calculateCaloriesBurntBetweenParks(ride1);
        assertTrue(result3 > result4);

        route1.setWindSpeed(10.0);
        when(bikesAPI.getBikeById(1)).thenReturn(bike7);
        double result5 = controller.calculateCaloriesBurntBetweenParks(ride1);
        route1.setWindSpeed(-10.0);
        double result6 = controller.calculateCaloriesBurntBetweenParks(ride1);
        assertTrue(result5 > result6);
        assertTrue(result3 > result5);
        assertTrue(result4 > result6);
    }

    @Test
    void testCalculateElectricalEnergyToTravelBetweenLocations() {

        Integer idUser = user1.getIdUser();
        Integer idBike = bike1.getIdBike();

        Location cqueijo = park11;
        Location ffoz = park12;
        Location rboavista = park13;
        when(routesAPI.getRouteByCoordinates(park11.getGeoLatitude(), park11.getGeoLongitude(), park12.getGeoLatitude(), park12.getGeoLongitude())).thenReturn(route1);
        route1.setWindDirection(WindUtils.calculateBearingBetweenLocations(park11, park12));

        // teste para estabelecer standard
        //double videoExpected = 8.0 * 2.175;
        double expected = 12.30;
        double val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, ffoz, null);
        double diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        expected = 0.0;
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, cqueijo, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        // TODO : NUNCA PODE SER ENERGIA negativa, caso contrário a bike carrega se o vento fosse a favor !!
        // positive wind test - reduces/nullifies power cost
        route1.setWindSpeed(19.7);
        expected = -108.90; // expected energy in KW/s
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, ffoz, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        // negative wind test - amplifies power cost (A LOT 10x more than with no wind)
        route1.setWindSpeed(-19.7);
        expected = 115.65;
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, ffoz, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        when(usersAPI.getUserById(1)).thenReturn(user1);

        // user weight test
        route1.setWindSpeed(0.0);
        expected = 13.66;
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, ffoz, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        // user weight + positive wind test - reduces/nullifies power cost
        route1.setWindSpeed(19.7);
        expected = -107.55; // expected energy in KW/s
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, ffoz, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        // user weight + negative wind test - amplifies power cost (A LOT 10x more than with no wind)
        route1.setWindSpeed(-19.7);
        expected = 117.01;
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, ffoz, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        // user weight + positive light wind test - reduces/nullifies power cost
        route1.setWindSpeed(4.0);
        expected = 0.10; // expected energy in KW/s
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, ffoz, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        // user weight + negative light wind test - amplifies power cost
        route1.setWindSpeed(-4.0);
        expected = 9.36;
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(idUser, idBike, cqueijo, ffoz, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        // null tests
        expected = 7.33;
        route1.setWindDirection(0.0);
        user1.setAvgSpeed(3.0);
        val = controller.calculateElectricalEnergyToTravelBetweenLocations(1, null, cqueijo, ffoz, null);
        diff = Math.abs(expected - val);
        assertTrue(diff < 0.01);

        assertNull(controller.calculateElectricalEnergyToTravelBetweenLocations(null, null, null, ffoz, null));
        assertNull(controller.calculateElectricalEnergyToTravelBetweenLocations(null, null, cqueijo, null, null));
    }

    @Test
    void getNearestParksTest() {
        assertEquals(new ArrayList<Park>(), controller.getNearestParks(10.0, 10.0, 0D));

        when(parksAPI.getAllParks()).thenReturn(allParks);

        List<Park> actual = controller.getNearestParks(10.0, 10.0, 0D);
        assertTrue(actual.size() <= 5);
        assertFalse(actual.contains(park3));
        assertFalse(actual.contains(park4));
        assertFalse(actual.contains(park6));
        assertFalse(actual.contains(park7));
        assertFalse(actual.contains(park9));
        assertEquals(actual.get(0), park2);
        assertEquals(actual.get(1), park1);
        assertEquals(actual.get(2), park10);
        assertEquals(actual.get(3), park5);
        assertEquals(actual.get(4), park8);
    }

    @Test
    void doesParkHaveSpaceForThisBikeTest() {
        assertFalse(controller.doesParkHaveSpaceForThisBike(1, 2));

        when(bikesAPI.getBikeById(1)).thenReturn(bike1);
        when(parksAPI.getParkById(2)).thenReturn(park2);
        when(bikesAPI.getAllMountainActiveBikesFromPark(2)).thenReturn(mntnBikes);
        when(bikesAPI.getAllRoadActiveBikesFromPark(2)).thenReturn(roadBikes);
        when(bikesAPI.getAllElectricalActiveBikesFromPark(2)).thenReturn(elecBikes);

        assertTrue(controller.doesParkHaveSpaceForThisBike(1, 2));

        park2.setMaxCapacityStandard(1);

        assertFalse(controller.doesParkHaveSpaceForThisBike(1, 2));
    }

    @Test
    void getMostChargedElectricalBikeFromParkTest() {
        assertNull(controller.getBikeWithMostChargeFromPark(3));

        when(parksAPI.getParkById(3)).thenReturn(park3);
        assertNull(controller.getBikeWithMostChargeFromPark(3));

        List<Bike> elecBikesPark3 = Arrays.asList(bike8, bike11);
        when(bikesAPI.getAllElectricalActiveBikesFromPark(3)).thenReturn(elecBikesPark3);
        assertEquals(bike8, controller.getBikeWithMostChargeFromPark(3));

        bike11.setCurrentBattery(15.00);
        assertEquals(bike11, controller.getBikeWithMostChargeFromPark(3));
    }

    @Test
    void getBikeWith10PlusEnergyTest() {
        assertNull(controller.getBikeWith10PlusEnergyFromPark(3, 50.0));

        when(parksAPI.getParkById(3)).thenReturn(park3);
        assertNull(controller.getBikeWith10PlusEnergyFromPark(3, 50.0));

        List<Bike> elecBikesPark3 = Arrays.asList(bike8, bike11);
        when(bikesAPI.getAllElectricalActiveBikesFromPark(3)).thenReturn(elecBikesPark3);
        assertNull(controller.getBikeWith10PlusEnergyFromPark(3, 50.0));

        assertEquals(bike8, controller.getBikeWith10PlusEnergyFromPark(3, 10.0));

        assertEquals(bike11, controller.getBikeWith10PlusEnergyFromPark(3, 4.0));
    }

    @Test
    void calculateRouteEnergyTest() {
        List<Location> locList = new LinkedList<>();
        Double expected = 0.0;
        assertEquals(expected, controller.calculatePathEnergy(1, locList));

        when(usersAPI.getUserById(1)).thenReturn(user1);
        assertEquals(expected, controller.calculatePathEnergy(1, locList));

        locList.add(park11);
        locList.add(park12);
        locList.add(park13);
        expected = 78.04;
        assertTrue(expected / 2 - controller.calculatePathEnergy(1, locList) < 0.01);
    }

    @Test
    void testGenerateParkReport() {
        //creating objects
        LinkedList<Bike> bikeList = new LinkedList<>();
        List<String> actualStrings = new ArrayList<>();
        ArrayList<String> expectedStrings = new ArrayList<>();

        //testing park with no electrical bikes
        when(bikesAPI.getAllElectricalActiveBikesFromPark(11)).thenReturn(bikeList);
        expectedStrings.add("Time left until all bikes in park " + 11 + " are fully charged is: " + "0(h) 0(m) 0(s)");
        actualStrings = controller.generateParkReport(11);
        assertArrayEquals(expectedStrings.toArray(), actualStrings.toArray());

        //testing park with 2 electrical bikes

        park11.setVoltage(220.0);
        park11.setIntensity(15.0);

        bike8.setIdPark(11);
        bike9.setIdPark(11);

        bike8.setMaxBattery(200.0);
        bike9.setMaxBattery(200.0);

        bikeList.add(bike8);
        bikeList.add(bike9);

        when(bikesAPI.getAllElectricalActiveBikesFromPark(11)).thenReturn(bikeList);
        when(parksAPI.getParkById(11)).thenReturn(park11);

        String actualTime = Utils.convertHoursToHoursMinutesSeconds(chargingController.calculateHoursToCompleteChargeOfParkTotal(bikeList, park11));
        expectedStrings.clear();
        expectedStrings.add(bike8.getIdBike() + " " + bike8.getCurrentBattery());
        expectedStrings.add(bike9.getIdBike() + " " + bike9.getCurrentBattery());

        expectedStrings.add("Time left until all bikes in park " + 11 + " are fully charged is: " + actualTime);


        when(bikesAPI.getAllElectricalActiveBikesFromPark(11)).thenReturn(bikeList);
        when(parksAPI.getParkById(11)).thenReturn(park11);
        actualStrings = controller.generateParkReport(11);

        assertArrayEquals(expectedStrings.toArray(), actualStrings.toArray());
    }

    @Test
    void getParkReportTest() {
        assertTrue(controller.getParkReport(null).isEmpty());
        assertTrue(controller.getParkReport(1).isEmpty());
        //faltam testes
    }

    /*@Test
    void getParkByCoordinatesTest() {
        assertNull(controller.getParkByCoordinates(1.0, 1.0));
        when(parksAPI.get()).thenReturn(allParks);
        assertEquals(park10, controller.getParkByCoordinates(39.630354, 48.266987));
    }

    @Test
    void getTouristicPointByCoordinatesTest() {
        assertNull(controller.getTouristicPointByCoordinates(1.0, 1.0));
        when(tourAPI.getAllTouristicPoints()).thenReturn(allTourPoints);
        assertEquals(tourPoint5, controller.getTouristicPointByCoordinates(32.676254, 14.432987));
    }*/


}
