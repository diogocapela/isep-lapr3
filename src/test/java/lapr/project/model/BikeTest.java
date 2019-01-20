package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BikeTest {

    @BeforeEach
    void setUp() {
        Locale.setDefault(new Locale("pt", "PT"));
    }

    @Test
    void getIdBikeTest() {
        Bike bike = new Bike();
        bike.setIdBike(1);
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        assertEquals(new Integer(1), bike.getIdBike());
    }

    @Test
    void setIdBikeTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        bike.setIdBike(2);
        Integer expected = 2;
        assertEquals(expected, bike.getIdBike());
    }


    @Test
    void getIdParkTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        Integer expected = 1;
        assertEquals(expected, bike.getIdPark());
    }

    @Test
    void setIdParkTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        bike.setIdPark(2);
        Integer expected = 2;
        assertEquals(expected, bike.getIdPark());
    }

    @Test
    void getTypeTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        BikeType expected = BikeType.ELECTRICAL;
        assertEquals(expected, bike.getType());

    }

    @Test
    void setTypeTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);

        bike.setType("MOUNTAIN");
        BikeType expected = BikeType.MOUNTAIN;
        assertEquals(expected, bike.getType());

        bike.setType("ROAD");
        expected = BikeType.ROAD;
        assertEquals(expected, bike.getType());

        bike.setType("ELECTRICAL");
        expected = BikeType.ELECTRICAL;
        assertEquals(expected, bike.getType());

        bike.setType("AAAA");
        expected = BikeType.MOUNTAIN;
        assertEquals(expected, bike.getType());
    }

    @Test
    void getCurrentBatteryTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        Double expected = 2000.0;
        assertEquals(expected, bike.getCurrentBattery());
    }

    @Test
    void getCurrentBatteryLevelTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("ELECTRICAL");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        Long expected = 67L;
        assertEquals(expected, bike.getCurrentBatteryLevel());
    }

    @Test
    void getCurrentBatteryLevelNotEletricTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("ROAD");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        Long expected = 0L;
        assertEquals(expected, bike.getCurrentBatteryLevel());
    }

    @Test
    void setCurrentBatteryTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        bike.setCurrentBattery(2300.0);
        Double expected = 2300.0;
        assertEquals(expected, bike.getCurrentBattery());
    }

    @Test
    void getMaxBatteryTest() {
        Bike testbike = new Bike();
        testbike.setIdPark(2);
        testbike.setType("electrical");
        testbike.setCurrentBattery(2000.0);
        testbike.setMaxBattery(3000.0);
        testbike.setIsActive(true);
        Double expected = 3000.0;
        assertEquals(expected, testbike.getMaxBattery());
    }

    @Test
    void setMaxBatteryTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        bike.setMaxBattery(2900.0);
        Double expected = 2900.0;
        assertEquals(expected, bike.getMaxBattery());
    }

    @Test
    void setDescriptionTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setDescription("Bike 1");
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        bike.setMaxBattery(2900.0);
        assertEquals("Bike 1", bike.getDescription());
    }

    @Test
    void setWeightTest() {
        Bike bike = new Bike();
        bike.setIdPark(1);
        bike.setType("electrical");
        bike.setDescription("Bike 1");
        bike.setWeight(0.1d);
        bike.setCurrentBattery(2000.0);
        bike.setMaxBattery(3000.0);
        bike.setIsActive(true);
        bike.setMaxBattery(2900.0);
        Double expected = 0.1d;
        assertEquals(expected, bike.getWeight());
    }

    @Test
    void equalsTest() {
        Bike testbike = new Bike();
        testbike.setIdBike(3);
        testbike.setIdPark(2);
        testbike.setType("electrical");
        testbike.setCurrentBattery(2000.0);
        testbike.setMaxBattery(3000.0);
        testbike.setIsActive(true);

        Bike testbike1 = new Bike();
        testbike1.setIdBike(1);
        testbike1.setIdPark(2);
        testbike1.setType("electrical");
        testbike1.setCurrentBattery(2000.0);
        testbike1.setMaxBattery(3000.0);
        testbike1.setIsActive(true);

        Bike testbike2 = new Bike();
        testbike2.setIdBike(1);
        testbike2.setIdPark(2);
        testbike2.setType("electrical");
        testbike2.setCurrentBattery(2000.0);
        testbike2.setMaxBattery(3000.0);
        testbike2.setIsActive(true);

        Bike testBike3 = null;
        Park testPark = new Park();

        assertEquals(testbike1, testbike2);
        assertEquals(testbike1, testbike1);
        assertNotEquals(testbike1, testPark);
        assertNotEquals(testbike1, testBike3);
        assertEquals(testbike1, testbike2);
        assertNotEquals(testbike, testbike1);
    }

    @Test
    void toStringTest() {
        String expected = "Bike - 1 - 1 - ELECTRICAL - 2000,00 - 3000,00 - true";
        Bike testbike = new Bike();
        testbike.setIdBike(1);
        testbike.setIdPark(1);
        testbike.setType("electrical");
        testbike.setCurrentBattery(2000.0);
        testbike.setMaxBattery(3000.0);
        testbike.setIsActive(true);
        assertEquals(expected, testbike.toString());
        String expected2 = "Bike - 2 - 3 - MOUNTAIN - true";
        Bike testbike2 = new Bike();
        testbike2.setIdBike(2);
        testbike2.setIdPark(3);
        testbike2.setType("mountain");

        testbike2.setIsActive(true);
        assertEquals(expected2, testbike2.toString());
    }

    @Test
    void hashCodeTest() {
        Bike testbike = new Bike();
        testbike.setIdBike(1);
        testbike.setIdPark(2);
        testbike.setType("electrical");
        testbike.setCurrentBattery(2000.0);
        testbike.setMaxBattery(3000.0);
        testbike.setIsActive(true);
        assertEquals(1, testbike.hashCode());
    }
}
