package lapr.project.data;

class ParksAPITest {

//    private static final String DATABASE_URL = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
//    private static final String DATABASE_USERNAME = "bddad_na4_B";
//    private static final String DATABASE_PASSWORD = "qwerty";
//
//    private static ParksAPI parksAPI;
//
//    @BeforeAll
//    static void start() {
//        parksAPI = new ParksAPI(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
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
//     * Test getParkById
//     */
//    @Test
//    void testGetParkById() {
//        Park park = new Park();
//        park.setGeoLatitude(5.0);
//        park.setGeoLongitude(5.0);
//        park.setGeoAltitude(5.0);
//        park.setMaxCapacityElectric(10);
//        park.setMaxCapacityStandard(20);
//        park.setActive(false);
//        Integer idAddedPark = parksAPI.addPark(park);
//        park.setIdPark(idAddedPark);
//        Park park2 = parksAPI.getParkById(idAddedPark);
//        assertEquals(park, park2);
//        parksAPI.removeParkById(idAddedPark);
//    }
//
//    /**
//     * Test getAllParks, addPark and removeParkById
//     */
//    @Test
//    void testGeneralParksById() {
//        int sizeBefore = parksAPI.getAllParks().size();
//        Park park = new Park();
//        park.setGeoLatitude(5.0);
//        park.setGeoLongitude(5.0);
//        park.setGeoAltitude(5.0);
//        park.setMaxCapacityElectric(10);
//        park.setMaxCapacityStandard(20);
//        park.setActive(false);
//        Integer idAddedPark = parksAPI.addPark(park);
//        Integer idRemovedPark = parksAPI.removeParkById(idAddedPark);
//        int sizeAfter = parksAPI.getAllParks().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Update
//     */
//    @Test
//    void testUpdatePark() {
//        Park park = new Park();
//        park.setGeoLatitude(5.0);
//        park.setGeoLongitude(5.0);
//        park.setGeoAltitude(5.0);
//        park.setMaxCapacityElectric(10);
//        park.setMaxCapacityStandard(20);
//        park.setActive(false);
//        Integer idPark = parksAPI.addPark(park);
//        park = parksAPI.getParkById(idPark);
//        Double latitude = 10.0;
//        park.setGeoLatitude(latitude);
//        idPark = parksAPI.updatePark(park);
//        park = parksAPI.getParkById(idPark);
//        assertEquals(latitude, park.getGeoLatitude(), "testUpdatePark");
//        parksAPI.removeParkById(idPark);
//    }
//
//    /**
//     * Deactivate
//     */
//    @Test
//    void testDeactivateParkById() {
//        Park park = new Park();
//        park.setGeoLatitude(5.0);
//        park.setGeoLongitude(5.0);
//        park.setGeoAltitude(5.0);
//        park.setMaxCapacityElectric(10);
//        park.setMaxCapacityStandard(20);
//        park.setActive(false);
//        Integer idPark = parksAPI.addPark(park);
//        park = parksAPI.getParkById(idPark);
//        idPark = parksAPI.deactivateParkById(idPark);
//        park = parksAPI.getParkById(idPark);
//        assertEquals(false, park.getActive(), "testDeactivateParkById");
//    }

}
