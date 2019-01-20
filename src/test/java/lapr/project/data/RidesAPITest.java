package lapr.project.data;

class RidesAPITest {

//    private static final String DATABASE_URL = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
//    private static final String DATABASE_USERNAME = "bddad_na4_B";
//    private static final String DATABASE_PASSWORD = "qwerty";
//
//    private static RidesAPI ridesAPI;
//
//    @BeforeAll
//    static void start() {
//        ridesAPI = new RidesAPI(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//        DataHandler dataHandler = new DataHandler(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
//        try {
//            new Utils().runTestScript(dataHandler);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @BeforeEach
//    void testSetup() {
//
//    }
//
//    /**
//     * Test getRideById
//     */
//    @Test
//    void testGetRideById() {
//        String now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
//        Ride ride = new Ride ();
//        ride.setIdBike(1);
//        ride.setIdUser(1);
//        ride.setIdStartPark(1);
//        ride.setTimestampStart(now);
//        Integer idRide=ridesAPI.addRide(ride);
//        ride.setIdRide(idRide);
//        Ride ride2 = ridesAPI.getRideById(idRide);
//        assertEquals(ride, ride2);
//    }
//
//    /**
//     * Test getAllRides, addRide and removeRideById
//     */
//    @Test
//    void testGeneralRidesById() {
//        int sizeBefore = ridesAPI.getAllRides().size();
//        String now = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
//        Ride ride = new Ride ();
//        ride.setIdBike(1);
//        ride.setIdUser(1);
//        ride.setIdStartPark(1);
//        ride.setTimestampStart(now);
//        Integer idAddedRide = ridesAPI.addRide(ride);
//        Integer idRemovedRide = ridesAPI.removeRideById(idAddedRide);
//        int sizeAfter = ridesAPI.getAllRides().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Update
//     */
//    @Test
//    void testUpdateRide() {
//        String date1 = "2018-11-11 17:00:00";
//        String date2 = "2018-11-11 18:00:00";
//        Ride ride = new Ride ();
//        ride.setIdBike(1);
//        ride.setIdUser(1);
//        ride.setIdStartPark(1);
//        ride.setTimestampStart(date1);
//        Integer idRide = ridesAPI.addRide(ride);
//        List<Ride> rideList = ridesAPI.getAllRides();
//        ride = ridesAPI.getRideById(idRide);
//        Logger.log("this idRide " + ridesAPI.getRideById(idRide));
//        Logger.log("this ride" + ride);
//        ride.setTimestampFinish(date2);
//        idRide = ridesAPI.updateRide(ride);
//        ride = ridesAPI.getRideById(idRide);
//        assertEquals(date2, ride.getTimestampFinish(), "testUpdateRide");
//        ridesAPI.removeRideById(idRide);
//    }

}
