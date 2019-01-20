package lapr.project.controller;

import org.junit.jupiter.api.BeforeEach;

class BaseControllerTest {

    private static final String DATABASE_URL = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private static final String DATABASE_USERNAME = "bddad_na4_B";
    private static final String DATABASE_PASSWORD = "qwerty";

    private static BaseController baseController;

//    @BeforeAll
//    static void start() {
//        baseController = new BaseController(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//        DataHandler dataHandler = new DataHandler(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//        try {
//            new TreeUtils().loadStoreIntoDatabase(dataHandler);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @BeforeEach
    void testSetup() {

    }
//
//    /**
//     * Test getAllBikes, addBike and removeBikeById
//     */
//    @Test
//    void testGeneralBikesById() {
//        int sizeBefore = baseController.getAllBikes().size();
//        Bike bike = new Bike();
//        bike.setIdPark(1);
//        bike.setType("electrical");
//        bike.setCurrentBattery(2000.0);
//        bike.setMaxBattery(3000.0);
//        bike.setIsActive(true);
//        Integer idAddedBike = baseController.addBike(bike);
//        baseController.removeBikeById(idAddedBike);
//        int sizeAfter = baseController.getAllBikes().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Test getAllParks, addPark and removeParkById
//     */
//    @Test
//    void testGeneralParksById() {
//        int sizeBefore = baseController.getAllParks().size();
//        Park park = new Park();
//        park.setGeoLatitude(5.0);
//        park.setGeoLongitude(5.0);
//        park.setGeoAltitude(5.0);
//        park.setMaxCapacityElectric(10);
//        park.setMaxCapacityStandard(20);
//        park.setActive(false);
//        Integer idAddedPark = baseController.addPark(park);
//        baseController.removeParkById(idAddedPark);
//        int sizeAfter = baseController.getAllParks().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Test getAllRides, addRide and removeRideById
//     */
//    @Test
//    void testGeneralRidesById() {
//        int sizeBefore = baseController.getAllRides().size();
//        String now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
//        Ride ride = new Ride();
//        ride.setIdBike(1);
//        ride.setIdUser(1);
//        ride.setIdStartPark(1);
//        ride.setTimestampStart(now);
//        Integer idAddedRide = baseController.addRide(ride);
//        baseController.removeRideById(idAddedRide);
//        int sizeAfter = baseController.getAllRides().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Test getAllUsers, addUser and removeUserById
//     */
//    @Test
//    void testGeneralUsersById() {
//        int sizeBefore = baseController.getAllUsers().size();
//        User user = new User();
//        user.setEmail("target_email@gmail.com");
//        user.setHashedSaltedPassword("123");
//        user.setCreditCardNumber("123455678");
//        user.setHeight(1.8);
//        user.setWeight(80.0);
//        user.setCreditPoints(10);
//        user.setIsAdmin(false);
//        Integer idAddedUser = baseController.addUser(user);
//        baseController.removeUserById(idAddedUser);
//        int sizeAfter = baseController.getAllUsers().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Test getAllUsers, addUser and removeUserByEmail
//     */
//    @Test
//    void testGeneralUsersByEmail() {
//        int sizeBefore = baseController.getAllUsers().size();
//        User user = new User();
//        user.setEmail("target_email@gmail.com");
//        user.setHashedSaltedPassword("123");
//        user.setCreditCardNumber("123455678");
//        user.setHeight(1.8);
//        user.setWeight(80.0);
//        user.setCreditPoints(10);
//        user.setIsAdmin(false);
//        Integer idAddedUser = baseController.addUser(user);
//        baseController.removeUserByEmail("target_email@gmail.com");
//        int sizeAfter = baseController.getAllUsers().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }

}
