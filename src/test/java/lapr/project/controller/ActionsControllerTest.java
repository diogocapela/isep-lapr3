package lapr.project.controller;

import org.junit.jupiter.api.BeforeEach;


class ActionsControllerTest {

    private static final String DATABASE_URL = "jdbc:oracle:thin://@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    private static final String DATABASE_USERNAME = "bddad_na4_B";
    private static final String DATABASE_PASSWORD = "qwerty";
    private static ActionsController controller;
/*
   @BeforeAll
   static void start() {
       controller = new ActionsController(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
       DataHandler dataHandler = new DataHandler(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
       try {
           new Utils().loadStoreIntoDatabase(dataHandler);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }*/

    @BeforeEach
    void testSetup() {

    }
/*
    @Test
    void testGenerateParkReport(){
        //10 30.57
        //Time left until all bikes in park 6 are fully charged is: 6(h) 20(m) 27(s)
        List<String> expected = new ArrayList<>();
        expected.add("10 30.57");
        expected.add("Time left until all bikes in park 6 are fully charged is: 6(h) 20(m) 27(s)");

       assertEquals(controller.generateParkReport(6).size(), expected.size());
       assertEquals(controller.generateParkReport(6), expected);

       assertEquals(controller.generateParkReport(null), null);
       assertEquals(controller.getFreeSpacesOfPark(-1), null);
    }*/


//    @Test
//    void TouristicPointTest(){
//        TouristicPoint tp = new TouristicPoint();
//        tp.setGeoAltitude(50.0);
//        tp.setGeoLatitude(33.3);
//        tp.setGeoLongitude(33.3);
//
//        List<TouristicPoint> listBefore = controller.getAllTouristicPoints();
//
//        Integer idTp =  controller.addTouristicPoint(tp);
//        tp.setIdTouristicPoint(idTp);
//
//        List<TouristicPoint> listAfter = controller.getAllTouristicPoints();
//
//        assertEquals(listBefore.size(), listAfter.size()-1);
//
//        TouristicPoint tp1 = controller.getTouristicPointById(idTp);
//        assertEquals(tp, tp1);
//
//        controller.removeTouristicPointById(idTp);
//
//        List<TouristicPoint> listAfterRemove = controller.getAllTouristicPoints();
//
//        assertEquals(listBefore.size(), listAfterRemove.size());
//    }

//    @Test
//    void getFreeSpacesOfParkTest() {
//        Map<BikeType, Integer> freeSpaces = controller.getFreeSpacesOfPark(1);
//        int sizeElectricalBefore = freeSpaces.get(BikeType.ELECTRICAL);
//        int sizeMountainBefore = freeSpaces.get(BikeType.MOUNTAIN);
//        int sizeRoadBefore = freeSpaces.get(BikeType.ROAD);
//
//        Bike bikeElectrical = new Bike();
//        bikeElectrical.setIdPark(1);
//        bikeElectrical.setType("electrical");
//        bikeElectrical.setCurrentBattery(2000.0);
//        bikeElectrical.setMaxBattery(3000.0);
//        bikeElectrical.setIsActive(true);
//
//        Bike bikeMountain = new Bike();
//        bikeMountain.setIdPark(1);
//        bikeMountain.setType("MOUNTAIN");
//        bikeMountain.setIsActive(true);
//
//        int idElec = controller.addBike(bikeElectrical);
//        int idMount = controller.addBike(bikeMountain);
//        freeSpaces = controller.getFreeSpacesOfPark(1);
//
//        int sizeElectricalAfter = freeSpaces.get(BikeType.ELECTRICAL);
//        int sizeMountainAfter = freeSpaces.get(BikeType.MOUNTAIN);
//        int sizeRoadAfter = freeSpaces.get(BikeType.ROAD);
//
//        assertEquals(sizeElectricalBefore, sizeElectricalAfter + 1);
//        assertEquals(sizeMountainBefore, sizeMountainAfter + 1);
//        assertEquals(sizeRoadBefore, sizeRoadAfter + 1);
//
//        controller.removeBikeById(idElec);
//        controller.removeBikeById(idMount);
//    }
//
//   /* @Test
//    void getAvailableBikesOfPark() {
//        Map<BikeType, List<Bike>> availableBikes = controller.getAvailableBikesOfPark(2);
//
//        int sizeElectricalBefore = availableBikes.get(BikeType.ELECTRICAL).size();
//        int sizeMountainBefore = availableBikes.get(BikeType.MOUNTAIN).size();
//        int sizeRoadBefore = availableBikes.get(BikeType.ROAD).size();
//
//        Bike bikeElectrical = new Bike();
//        bikeElectrical.setIdPark(2);
//        bikeElectrical.setType("electrical");
//        bikeElectrical.setCurrentBattery(2000.0);
//        bikeElectrical.setMaxBattery(3000.0);
//        bikeElectrical.setIsActive(true);
//
//        Bike bikeMountain = new Bike();
//        bikeMountain.setIdPark(2);
//        bikeMountain.setType("MOUNTAIN");
//        bikeMountain.setIsActive(true);
//
//        Bike bikeRoad = new Bike();
//        bikeRoad.setIdPark(2);
//        bikeRoad.setType("ROAD");
//        bikeRoad.setIsActive(true);
//
//        Integer b1 = controller.addBike(bikeElectrical);
//        Integer b2 = controller.addBike(bikeMountain);
//        Integer b3 = controller.addBike(bikeRoad);
//
//        availableBikes = controller.getAvailableBikesOfPark(2);
//
//        int sizeElectricalAfter = availableBikes.get(BikeType.ELECTRICAL).size();
//        int sizeMountainAfter = availableBikes.get(BikeType.MOUNTAIN).size();
//        int sizeRoadAfter = availableBikes.get(BikeType.ROAD).size();
//
//        assertEquals(sizeElectricalBefore, sizeElectricalAfter - 1);
//        assertEquals(sizeMountainBefore, sizeMountainAfter - 1);
//        assertEquals(sizeRoadBefore, sizeRoadAfter - 1);
//
//        controller.removeBikeById(b1);
//        controller.removeBikeById(b2);
//        controller.removeBikeById(b3);
//    }*/
//
//    @Test
//    void lockAndUnlockBikeTest() {
//        //SIZE OF ALL UNFINISHED RIDES BEFORE ANYTHING IS DONE
//        int sizeUnfinishedBefore = controller.getAllUnfinishedRides().size();
//
//        //CREATING OBJECTS FOR TESTING
//        User u1 = new User();
//        u1.setEmail("jjjqughsifhrhw@gmail.com");
//        u1.setHashedSaltedPassword("12345");
//        u1.setCreditCardNumber("123456789");
//        u1.setHeight(1.8);
//        u1.setWeight(80.0);
//        u1.setCreditPoints(0);
//        u1.setIsAdmin(false);
//        System.out.println(u1);
//        Integer idUser = controller.addUser(u1);    /////REMOVER
//
//
//        Park p11 = new Park();
//        p11.setGeoLatitude(5.0);
//        p11.setGeoLongitude(5.0);
//        p11.setGeoAltitude(5.0);
//        p11.setMaxCapacityElectric(10);
//        p11.setMaxCapacityStandard(20);
//        p11.setActive(false);
//        Integer idP11 = controller.addPark(p11); ////REMOVER
//
//        Park p12 = new Park();
//        p12.setGeoLatitude(10.0);
//        p12.setGeoLongitude(10.0);
//        p12.setGeoAltitude(40.0);
//        p12.setMaxCapacityElectric(10);
//        p12.setMaxCapacityStandard(20);
//        p12.setActive(false);
//        Integer idP12 = controller.addPark(p12);  /////REMOVER
//
//        Bike bike = new Bike();
//        bike.setIdPark(idP11);
//        bike.setType("electrical");
//        bike.setCurrentBattery(2000.0);
//        bike.setMaxBattery(3000.0);
//        bike.setIsActive(true);
//        int idBike = controller.addBike(bike);    ////REMOVER
//
//        //FIRST  TEST    ################
//        controller.unlockBike(idUser, idBike, idP11);
//
//        int sizeUnfinishedAfter = controller.getAllUnfinishedRides().size();
//
//        //TESTING IF A RIDE WAS CREATED
//        assertEquals(sizeUnfinishedBefore, sizeUnfinishedAfter - 1);
//
//        Ride ride1 = controller.lockBike(idUser, idBike, idP12);
//        int sizeUnfinishedAfterLock = controller.getAllUnfinishedRides().size();
//
//        //TESTING IF A RIDE WAS FINISHED
//        assertEquals(sizeUnfinishedBefore, sizeUnfinishedAfterLock);
//
//        Bike bike2 = controller.getBikeById(idBike);
//        User user2 = controller.getUserById(idUser);
//
//        //TESTING IF THE ID OF THE BIKE WAS SET AS THE DESTINATION PARK
//        assertEquals(idP12, bike2.getIdPark());
//
//        //TESTING IF 5 CREDIT POINTS WERE CREDITED TO THE USER
//        assertEquals(new Integer(5), user2.getCreditPoints());
//
//        //SECOND TEST    ##################
//        Park p13 = controller.getParkById(idP12);
//        p13.setGeoAltitude(100.0);
//        controller.updatePark(p13);
//
//        bike2 = controller.getBikeById(idBike);
//        bike2.setIdPark(idP11);
//        controller.updateBike(bike2);
//        controller.unlockBike(idUser, idBike, idP11);
//        Ride ride2 = controller.lockBike(idUser, idBike, idP12);
//        bike2 = controller.getBikeById(idBike);
//        user2 = controller.getUserById(idUser);
//
//        //TESTING IF THE ID OF THE BIKE WAS SET AS THE DESTINATION PARK
//        assertEquals(idP12, bike2.getIdPark());
//
//        //TESTING IF ALTITUDE DIFFERENCE>50 IF IT ADDS 15 CREDIT POINTS
//        assertEquals(new Integer(20), user2.getCreditPoints());
//
//        //REMOVE ALL OBJECTS CREATED
//        controller.removeRideById(ride1.getIdRide());
//        controller.removeRideById(ride2.getIdRide());
//        controller.removeUserById(idUser);
//        controller.removeBikeById(idBike);
//        controller.removeParkById(idP11);
//        controller.removeParkById(idP12);
//    }
}
