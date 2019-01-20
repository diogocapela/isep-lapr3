package lapr.project.utils;

import lapr.project.model.Park;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WindUtilsTest {

    @Test
    void testCalculateBearingBetweenLocations() {
        Park A = new Park();
        Park B = new Park();
        A.setGeoLatitude(39.099912);
        A.setGeoLongitude(-94.581213);
        B.setGeoLatitude(38.627089);
        B.setGeoLongitude(-90.200203);
        Integer expected = 96;
        Integer actual = WindUtils.calculateBearingBetweenLocations(A, B).intValue();
        assertEquals(expected, actual);
    }

    void testCalculateRelativeWindSpeed() {
        Park A = new Park();
        Park B = new Park();
        A.setGeoLatitude(39.099912);
        A.setGeoLongitude(-94.581213);
        B.setGeoLatitude(38.627089);
        B.setGeoLongitude(-90.200203);

        Integer expected = 7;
        Integer actual = WindUtils.calculateRelativeWindSpeed(45.0, 10.0, A, B).intValue();
        assertEquals(expected, actual);
    }
}
