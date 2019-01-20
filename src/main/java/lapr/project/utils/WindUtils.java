package lapr.project.utils;

import lapr.project.model.Location;

public class WindUtils {

    public static Double calculateBearingBetweenLocations(Location locA, Location locB) {
        double longitude1 = locA.getGeoLongitude();
        double longitude2 = locB.getGeoLongitude();
        double latitude1 = Math.toRadians(locA.getGeoLatitude());
        double latitude2 = Math.toRadians(locB.getGeoLatitude());
        double longDiff = Math.toRadians(longitude2 - longitude1);

        double y = Math.sin(longDiff) * Math.cos(latitude2);

        double x = Math.cos(latitude1) * Math.sin(latitude2) - Math.sin(latitude1) * Math.cos(latitude2) * Math.cos(longDiff);

        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
    }

    public static Double calculateRelativeWindSpeed(Double windBearing, Double intensity, Location locA, Location locB) {
        Double pathBearing = calculateBearingBetweenLocations(locA, locB);
        double relativeWindBearing = pathBearing - windBearing;
        return Math.cos(Math.abs(relativeWindBearing)) * intensity;
    }
}
