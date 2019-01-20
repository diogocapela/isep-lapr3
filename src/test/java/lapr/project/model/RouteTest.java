package lapr.project.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {

    private Route route0 = null;
    private Route route1;
    private Route route2;
    private Route route3;

    private Ride ride1;

    @BeforeAll
    static void startUp() {
        Locale.setDefault(new Locale("pt", "PT"));
    }

    @BeforeEach
    void setUp() {
        route1 = new Route(1.0, 2.0, 15.0, 3.0, "bi", 0.2, 23.0, 20.0);
        route2 = new Route(1.0, 2.0, 15.0, 3.0, "uni", 0.2, 23.0, 20.0);
        route3 = new Route(1.0, 2.0, 15.0, 3.0, "bi", 0.2, 230.0, 20.0);

        ride1 = new Ride();
        ride1.setIdRide(1);
    }

    @Test
    void getSetIdRoute() {
        assertNotEquals(5, route1.getIdRoute());
        route1.setIdRoute(5);
        assertEquals(5, route1.getIdRoute().intValue());
    }

    @Test
    void getSetLatitudeA() {
        assertNotEquals(90.0, route1.getLatitudeA());
        route1.setLatitudeA(90.0);
        assertEquals(90.0, route1.getLatitudeA().doubleValue());
    }

    @Test
    void getSetLongitudeA() {
        assertNotEquals(90.0, route1.getLongitudeA());
        route1.setLongitudeA(90.0);
        assertEquals(90.0, route1.getLongitudeA().doubleValue());
    }

    @Test
    void getSetLatitudeB() {
        assertNotEquals(90.0, route1.getLatitudeB());
        route1.setLatitudeB(90.0);
        assertEquals(90.0, route1.getLatitudeB().doubleValue());
    }

    @Test
    void getSetLongitudeB() {
        assertNotEquals(90.0, route1.getLongitudeB());
        route1.setLongitudeB(90.0);
        assertEquals(90.0, route1.getLongitudeB().doubleValue());
    }

    @Test
    void getSetRouteDirection() {
        assertNotEquals("a", route1.getRouteDirection());
        route1.setRouteDirection("a");
        assertEquals("a", route1.getRouteDirection());
    }

    @Test
    void getSetAerodynamicCoefficient() {
        assertNotEquals(90.0, route1.getAerodynamicCoefficient());
        route1.setAerodynamicCoefficient(90.0);
        assertEquals(90.0, route1.getAerodynamicCoefficient().doubleValue());
    }

    @Test
    void getSetWindDirection() {
        assertNotEquals(90.0, route1.getWindDirection());
        route1.setWindDirection(90.0);
        assertEquals(90.0, route1.getWindDirection().doubleValue());
    }

    @Test
    void getSetWindSpeed() {
        assertNotEquals(90.0, route1.getWindSpeed());
        route1.setWindSpeed(90.0);
        assertEquals(90.0, route1.getWindSpeed().doubleValue());
    }

    @Test
    void equalsTest() {
        assertEquals(route1, route1);
        assertEquals(route1, route2);
        assertNotEquals(route1, route0);
        assertNotEquals(route1, ride1);
    }

    @Test
    void hashCodeTest() {
        assertEquals(route1.hashCode(), route1.hashCode());
        assertEquals(route1.hashCode(), route2.hashCode());
        assertNotEquals(ride1.hashCode(), route1.hashCode());
    }

    @Test
    void toStringTest() {
        String s1 = null;
        String s2 = null;
        assertNull(s1);
        assertNull(s2);
        assertEquals(s1, s2);

        s1 = route1.toString();
        s2 = route2.toString();
        assertNotNull(s1);
        assertNotNull(s2);
        assertNotEquals(s1, s2);
    }
}
