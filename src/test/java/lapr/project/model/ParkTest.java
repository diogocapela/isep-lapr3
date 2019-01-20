package lapr.project.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ParkTest {

    private Park p11 = new Park();
    private Park p12 = new Park();
    private Park p2 = new Park();
    private Park p3 = null;

    private Ride ride1 = new Ride();

    @BeforeAll
    static void startUp() {
        Locale.setDefault(new Locale("pt", "PT"));
    }

    @BeforeEach
    void setUp() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        ride1.setIdRide(1);
        ride1.setIdBike(1);
        ride1.setIdUser(1);
        ride1.setIdStartPark(1);
        ride1.setIdEndPark(2);
        ride1.setTimestampStart(date1);
        ride1.setTimestampFinish(date2);

        p11.setIdPark(1);
        p11.setGeoLatitude(5.0);
        p11.setGeoLongitude(5.0);
        p11.setGeoAltitude(5.0);
        p11.setMaxCapacityElectric(10);
        p11.setMaxCapacityStandard(20);
        p11.setActive(false);

        p12.setIdPark(1);
        p12.setGeoLatitude(6.0);
        p12.setGeoLongitude(6.0);
        p12.setGeoAltitude(6.0);
        p12.setMaxCapacityElectric(10);
        p12.setMaxCapacityStandard(20);
        p12.setActive(false);

        p2.setIdPark(2);
        p2.setGeoLatitude(5.0);
        p2.setGeoLongitude(5.0);
        p2.setGeoAltitude(5.0);
        p2.setMaxCapacityElectric(10);
        p2.setMaxCapacityStandard(20);
        p2.setActive(false);
    }

    @Test
    void equalsTest() {
        System.out.println("Test of Park equals");
        assertEquals(p11, p12);

        assertNotEquals(p11, p2);
        assertNotEquals(p12, p2);

        assertNotEquals(p11, p3);

        // other classes instances
        assertNotEquals(p11, ride1);
    }

    @Test
    void hashCodeTest() {
        System.out.println("Test of Park hashCode");
        assertEquals(p11.hashCode(), p12.hashCode());

        assertNotEquals(p11.hashCode(), p2.hashCode());
        assertNotEquals(p12.hashCode(), p2.hashCode());


        // other classes instances
        assertNotEquals(p11.hashCode(), ride1.hashCode());

    }

    @Test
    void toStringTest() {
        System.out.println("Test of Park toString");
        String expected = null;
        assertNotEquals(expected, p11.toString());

        expected = "abc";
        assertNotEquals(expected, p11.toString());

        expected = "PARK - 1 - 5,000000 - 5,000000 - 5,000000 - 10 - 20 - false";
        assertEquals(expected, p11.toString());
        expected = "PARK - 2 - 5,000000 - 5,000000 - 5,000000 - 10 - 20 - false";
        assertEquals(expected, p2.toString());
    }

    @Test
    void setGetIdParkTest() {
        Integer val = 5;

        assertNotEquals(val, p11.getIdPark());

        p11.setIdPark(val);

        assertEquals(val, p11.getIdPark());
    }

    @Test
    void setGetGeoLatitude() {
        Double val = 1.1;

        assertNotEquals(val, p11.getGeoLatitude());

        p11.setGeoLatitude(val);

        assertEquals(val, p11.getGeoLatitude());
    }

    @Test
    void setGetGeoLongitude() {
        Double val = 2.2;

        assertNotEquals(val, p11.getGeoLongitude());

        p11.setGeoLongitude(val);

        assertEquals(val, p11.getGeoLongitude());
    }

    @Test
    void setGetGeoAltitude() {
        Double val = 3.3;

        assertNotEquals(val, p11.getGeoLongitude());

        p11.setGeoAltitude(val);

        assertEquals(val, p11.getGeoAltitude());
    }

    @Test
    void setGetMaxCapacityElectric() {
        int val = 4;

        assertNotEquals(val, p11.getMaxCapacityElectric());

        p11.setMaxCapacityElectric(val);

        assertEquals(val, p11.getMaxCapacityElectric());
    }

    @Test
    void setGetMaxCapacityStandard() {
        int val = 5;

        assertNotEquals(val, p11.getMaxCapacityStandard());

        p11.setMaxCapacityStandard(val);

        assertEquals(val, p11.getMaxCapacityStandard());
    }

    @Test
    void setGetActive() {
        Boolean val = p11.getActive();

        assertEquals(val, p11.getActive());

        p11.setActive(!val);

        assertNotEquals(val, p11.getActive());
    }
}
