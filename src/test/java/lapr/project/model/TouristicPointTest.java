package lapr.project.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TouristicPointTest {

    private TouristicPoint tp0 = null;
    private TouristicPoint tp1 = new TouristicPoint();
    private TouristicPoint tp2 = new TouristicPoint();
    private TouristicPoint tp3 = new TouristicPoint();

    private Park park1 = new Park();
    private Park park2 = new Park();

    @BeforeAll
    static void startUp() {
        Locale.setDefault(new Locale("pt", "PT"));
    }

    @BeforeEach
    void setUp() {
        tp1.setIdTouristicPoint(1);
        tp1.setGeoLatitude(50.50);
        tp1.setGeoLongitude(-8.6);
        tp1.setGeoAltitude(500.0);

        tp2.setIdTouristicPoint(1);
        tp2.setGeoLatitude(-8.6);
        tp2.setGeoLongitude(50.50);
        tp2.setGeoAltitude(600.0);

        tp3.setIdTouristicPoint(2);
        tp3.setGeoLatitude(51.50);
        tp3.setGeoLongitude(-18.6);
        tp3.setGeoAltitude(501.0);

        park1.setIdPark(1);
        park2.setIdPark(2);
    }

    @Test
    void getSetIdTouristicPointTest() {
        Integer expected = 5;
        assertNotEquals(expected, tp1.getIdTouristicPoint());

        tp1.setIdTouristicPoint(expected);
        assertEquals(expected, tp1.getIdTouristicPoint());
    }

    @Test
    void getSetLatitudeTest() {
        Double expected = Double.MAX_VALUE;
        assertNotEquals(expected, tp1.getGeoLatitude());

        tp1.setGeoLatitude(expected);
        assertEquals(expected, tp1.getGeoLatitude());
    }

    @Test
    void getSetLongitureTest() {
        Double expected = Double.MAX_VALUE;
        assertNotEquals(expected, tp1.getGeoLongitude());

        tp1.setGeoLongitude(expected);
        assertEquals(expected, tp1.getGeoLongitude());
    }

    @Test
    void getSetAltitudeTest() {
        Double expected = Double.MAX_VALUE;
        assertNotEquals(expected, tp1.getGeoAltitude());

        tp1.setGeoAltitude(expected);
        assertEquals(expected, tp1.getGeoAltitude());
    }

    @Test
    void toStringTest() {
        String expected1 = "TOURISTICPOINT - 1 - 50,50 - -8,60 - 500,00";
        assertEquals(expected1, tp1.toString());

        String expected2 = "TOURISTICPOINT - 2 - 51,50 - -18,60 - 501,00";
        assertEquals(expected2, tp3.toString());
    }

    @Test
    void equalsTest() {
        //same object
        assertEquals(tp1, tp1);

        // same id test
        assertEquals(tp1, tp2);

        // null tests
        assertNotEquals(tp1, tp0);
        assertNotEquals(tp2, tp0);
        assertNotEquals(tp3, tp0);

        // different id tests
        assertNotEquals(tp1, tp3);
        assertNotEquals(tp2, tp3);

        // different object tests
        assertNotEquals(tp1, park1);
        assertNotEquals(tp2, park1);
        assertNotEquals(tp3, park1);
        assertNotEquals(tp1, park2);
        assertNotEquals(tp2, park2);
        assertNotEquals(tp3, park2);
    }

    @Test
    void hashCodeTest() {

        // same id test
        assertEquals(tp1.hashCode(), tp2.hashCode());

        // different id tests
        assertNotEquals(tp1.hashCode(), tp3.hashCode());
        assertNotEquals(tp2.hashCode(), tp3.hashCode());

        // different object tests
        assertNotEquals(tp1.hashCode(), park1.hashCode());
        assertNotEquals(tp2.hashCode(), park1.hashCode());
        assertNotEquals(tp3.hashCode(), park1.hashCode());
        assertNotEquals(tp1.hashCode(), park2.hashCode());
        assertNotEquals(tp2.hashCode(), park2.hashCode());
        assertNotEquals(tp3.hashCode(), park2.hashCode());
    }

    @Test
    void getSetDescricao() {
        String expected = "maladeMão";
        assertNotEquals(expected, tp1.getDescricao());
        tp1.setDescricao(expected);
        assertEquals(expected, tp1.getDescricao());
    }
}
