package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author teresa
 */
class RideTest {

    @BeforeEach
    void setUp() {
        Locale.setDefault(new Locale("pt", "PT"));
    }

    /**
     * Test of getIdRide method, of class Ride.
     */
    @Test
    void testGetIdRide() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r1 = new Ride();
        r1.setIdRide(1);
        r1.setIdBike(1);
        r1.setIdUser(1);
        r1.setIdStartPark(1);
        r1.setIdEndPark(2);
        r1.setTimestampStart(date1);
        r1.setTimestampFinish(date2);

        Ride r2 = new Ride();
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        assertNull(r2.getIdRide());
        Integer expResult = 1;
        Integer result = r1.getIdRide();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdRide method, of class Ride.
     */
    @Test
    void testSetIdRide() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(2);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Integer idRide = 3;
        r2.setIdRide(idRide);
        assertEquals(r2.getIdRide(), idRide);
    }

    /**
     * Test of getIdBikemethod, of class Ride.
     */
    @Test
    void testGetIdBike() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r1 = new Ride();
        r1.setIdRide(1);
        r1.setIdBike(1);
        r1.setIdUser(1);
        r1.setIdStartPark(1);
        r1.setIdEndPark(2);
        r1.setTimestampStart(date1);
        r1.setTimestampFinish(date2);
        Integer expResult = 1;
        Integer result = r1.getIdBike();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdBike method, of class Ride.
     */
    @Test
    void testSetIdBike() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(2);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Integer idBike = 3;
        r2.setIdBike(idBike);
        assertEquals(r2.getIdBike(), idBike);
    }

    /**
     * Test of getIdUser method, of class Ride.
     */
    @Test
    void testGetIdUser() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(2);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(2);
        r2.setIdEndPark(1);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Integer expResult = 1;
        Integer result = r2.getIdUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdUser method, of class Ride.
     */
    @Test
    void testSetIdUser() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(2);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Integer idUser = 2;
        r2.setIdUser(idUser);
        assertEquals(r2.getIdUser(), idUser);
    }

    /**
     * Test of getIdStartPark method, of class Ride.
     */
    @Test
    void testGetIdStartPark() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(1);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Integer expResult = 1;
        Integer result = r2.getIdStartPark();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdStartPark method, of class Ride.
     */
    @Test
    void testSetIdStartPark() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(2);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Integer idStartPark = 1;
        r2.setIdStartPark(idStartPark);
    }

    /**
     * Test of getIdEndPark method, of class Ride.
     */
    @Test
    void testGetIdEndPark() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(1);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Integer expResult = 2;
        Integer result = r2.getIdEndPark();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdEndPark method, of class Ride.
     */
    @Test
    void testSetIdEndPark() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(1);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Integer idEndPark = 2;
        r2.setIdEndPark(idEndPark);
        assertEquals(idEndPark, r2.getIdEndPark());
    }

    /**
     * Test of getTimestampStart method, of class Ride.
     */
    @Test
    void testGetTimestampStart() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(1);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        String result = r2.getTimestampStart();
        assertEquals(date1, result);
    }

    /**
     * Test of setTimestampStart method, of class Ride.
     */
    @Test
    void testSetTimestampStart() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(1);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        r2.setTimestampStart(date2);
        assertEquals(r2.getTimestampStart(), date2);
    }

    /**
     * Test of getTimestampFinish method, of class Ride.
     */
    @Test
    void testGetTimestampFinish() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(1);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Ride instance = r2;
        String expResult = date2;
        String result = instance.getTimestampFinish();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTimestampFinish method, of class Ride.
     */
    @Test
    void testSetTimestampFinish() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(1);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        r2.setTimestampFinish(date1);
        assertEquals(r2.getTimestampFinish(), date1);
    }

    /**
     * Test of hashCode method, of class Ride.
     */
    @Test
    void testHashCode() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r2 = new Ride();
        r2.setIdRide(1);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Ride instance = r2;
        int expResult = 77971001;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Ride.
     */
    @Test
    void testEquals() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r1 = new Ride();
        r1.setIdRide(2);
        r1.setIdBike(1);
        r1.setIdUser(1);
        r1.setIdStartPark(1);
        r1.setIdEndPark(2);
        r1.setTimestampStart(date1);
        r1.setTimestampFinish(date2);
        Ride r2 = new Ride();
        r2.setIdRide(2);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Object obj = r1;
        Ride instance = r2;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Ride.
     */
    @Test
    void testEquals2() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r1 = new Ride();
        r1.setIdRide(1);
        r1.setIdBike(1);
        r1.setIdUser(1);
        r1.setIdStartPark(1);
        r1.setIdEndPark(2);
        r1.setTimestampStart(date1);
        r1.setTimestampFinish(date2);
        Ride r2 = new Ride();
        r2.setIdRide(2);
        r2.setIdBike(1);
        r2.setIdUser(1);
        r2.setIdStartPark(1);
        r2.setIdEndPark(2);
        r2.setTimestampStart(date1);
        r2.setTimestampFinish(date2);
        Object obj = r1;
        Ride instance = r2;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Ride.
     */
    @Test
    void testEquals3() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        ;
        Ride r1 = new Ride();
        r1.setIdRide(1);
        r1.setIdBike(1);
        r1.setIdUser(1);
        r1.setIdStartPark(1);
        r1.setIdEndPark(2);
        r1.setTimestampStart(date1);
        r1.setTimestampFinish(date2);
        Object obj = r1;
        Ride instance = r1;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Ride.
     */
    @Test
    void testEquals4() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r1 = new Ride();
        r1.setIdRide(1);
        r1.setIdBike(1);
        r1.setIdUser(1);
        r1.setIdStartPark(1);
        r1.setIdEndPark(2);
        r1.setTimestampStart(date1);
        r1.setTimestampFinish(date2);
        Object obj = null;
        Ride instance = r1;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Ride.
     */
    @Test
    void testToString() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r1 = new Ride();
        r1.setIdRide(1);
        r1.setIdBike(1);
        r1.setIdUser(1);
        r1.setIdStartPark(1);
        r1.setIdEndPark(2);
        r1.setTimestampStart(date1);
        r1.setTimestampFinish(date2);
        String expResult = "RIDE: idRide = 1, idBike = 1, idUser = 1, "
            + "idStartPark = 1, idEndPark = 2";
        String result = r1.toString();
        assertEquals(expResult, result);
    }

    @Test
    void getSetIdInvoice() {
        String date1 = "29-11-2018 17-00-00";
        String date2 = "29-11-2018 18-00-00";
        Ride r1 = new Ride();
        r1.setIdRide(1);
        r1.setIdBike(1);
        r1.setIdUser(1);
        r1.setIdStartPark(1);
        r1.setIdEndPark(2);
        r1.setTimestampStart(date1);
        r1.setTimestampFinish(date2);
        r1.setIdInvoice(50);

        assertNotEquals(5, r1.getIdInvoice());
        r1.setIdInvoice(5);
        assertEquals(5, r1.getIdInvoice().intValue());
    }
}

