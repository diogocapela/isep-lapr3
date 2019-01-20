package lapr.project.data;

class BikesAPITest {
//
//
//    private static final String DATABASE_URL = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
//    private static final String DATABASE_USERNAME = "bddad_na4_B";
//    private static final String DATABASE_PASSWORD = "qwerty";
//
//    private static BikesAPI bikesAPI;
//
//    @BeforeAll
//    static void start() {
//        bikesAPI = new BikesAPI(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
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
//     * Test getAllBikes, addBike and removeBikeById
//     */
//    @Test
//    void testGeneralBikesById() {
//        int sizeBefore = bikesAPI.getAllBikes().size();
//        Bike bikeElectrical = new Bike();
//        bikeElectrical.setIdPark(1);
//        bikeElectrical.setType("electrical");
//        bikeElectrical.setCurrentBattery(2000.0);
//        bikeElectrical.setMaxBattery(3000.0);
//        bikeElectrical.setIsActive(true);
//        Integer idAddedBike = bikesAPI.addBike(bikeElectrical);
//        Integer idRemovedBike = bikesAPI.removeBikeById(idAddedBike);
//        int sizeAfter = bikesAPI.getAllBikes().size();
//        assertEquals(sizeBefore, sizeAfter);
//    }
//
//    /**
//     * Update
//     */
//    @Test
//    void testUpdateBike() {
//        Bike bike = new Bike();
//        bike.setIdPark(1);
//        bike.setType("electrical");
//        bike.setCurrentBattery(2000.0);
//        bike.setMaxBattery(3000.0);
//        bike.setIsActive(true);
//        Integer idBike = bikesAPI.addBike(bike);
//        bike = bikesAPI.getBikeById(idBike);
//        Integer idPark = 2;
//        bike.setIdPark(idPark);
//        idBike = bikesAPI.updateBike(bike);
//        bike = bikesAPI.getBikeById(idBike);
//        assertEquals(idPark, bike.getIdPark(), "testUpdateBike");
//        bikesAPI.removeBikeById(idBike);
//    }
//
//    /**
//     * Deactivate
//     */
//    @Test
//    void testDeactivateBikeById() {
//        Bike bike = new Bike();
//        bike.setIdPark(1);
//        bike.setType("electrical");
//        bike.setCurrentBattery(2000.0);
//        bike.setMaxBattery(3000.0);
//        bike.setIsActive(true);
//        Integer idBike = bikesAPI.addBike(bike);
//        bike = bikesAPI.getBikeById(idBike);
//        idBike = bikesAPI.deactivateBikeById(idBike);
//        bike = bikesAPI.getBikeById(idBike);
//        assertEquals(false, bike.getIsActive(), "testDeactivateBikeById");
//        bikesAPI.removeBikeById(idBike);
//    }
//
//
//    @Test
//    void testGetAllActiveBikesFromPark() {
//        List<Bike> activeBikesBefore = bikesAPI.getAllActiveBikesFromPark(2);
//        Bike bike = new Bike();
//        bike.setIdPark(2);
//        bike.setType("electrical");
//        bike.setCurrentBattery(2000.0);
//        bike.setMaxBattery(3000.0);
//        bike.setIsActive(true);
//        Integer idBike = bikesAPI.addBike(bike);
//        List<Bike> activeBikesAfter = bikesAPI.getAllActiveBikesFromPark(2);
//        assertEquals(activeBikesBefore.size(), activeBikesAfter.size()-1);
//        bikesAPI.removeBikeById(idBike);
//
//
//    }
//
//    @Test
//    void testGetAllElectricalActiveBikesFromPark() {
//        List<Bike> activeBikesBefore = bikesAPI.getAllElectricalActiveBikesFromPark(2);
//        Bike bike = new Bike();
//        bike.setIdPark(2);
//        bike.setType("electrical");
//        bike.setCurrentBattery(2000.0);
//        bike.setMaxBattery(3000.0);
//        bike.setIsActive(true);
//        Integer idBike = bikesAPI.addBike(bike);
//        List<Bike> activeBikesAfter = bikesAPI.getAllElectricalActiveBikesFromPark(2);
//        assertEquals(activeBikesBefore.size(), activeBikesAfter.size()-1);
//        bikesAPI.removeBikeById(idBike);
//    }
//
//    @Test
//    void testGetAllMountainActiveBikesFromPark() {
//        List<Bike> activeBikesBefore = bikesAPI.getAllMountainActiveBikesFromPark(2);
//        Bike bike = new Bike();
//        bike.setIdPark(2);
//        bike.setType("mountain");
//        bike.setCurrentBattery(null);
//        bike.setMaxBattery(null);
//        bike.setIsActive(true);
//        Integer idBike = bikesAPI.addBike(bike);
//        List<Bike> activeBikesAfter = bikesAPI.getAllMountainActiveBikesFromPark(2);
//        assertEquals(activeBikesBefore.size(), activeBikesAfter.size()-1);
//        bikesAPI.removeBikeById(idBike);
//    }
//
//    @Test
//    void testGetAllRoadActiveBikesFromPark() {
//        List<Bike> activeBikesBefore = bikesAPI.getAllRoadActiveBikesFromPark(2);
//        Bike bike = new Bike();
//        bike.setIdPark(2);
//        bike.setType("road");
//        bike.setCurrentBattery(null);
//        bike.setMaxBattery(null);
//        bike.setIsActive(true);
//        Integer idBike = bikesAPI.addBike(bike);
//        List<Bike> activeBikesAfter = bikesAPI.getAllRoadActiveBikesFromPark(2);
//        assertEquals(activeBikesBefore.size(), activeBikesAfter.size()-1);
//        bikesAPI.removeBikeById(idBike);
//    }
//
}
