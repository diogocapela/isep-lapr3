package lapr.project.utils;

import lapr.project.data.DataHandler;
import lapr.project.model.Bike;
import lapr.project.model.Location;
import lapr.project.model.Route;
import lapr.project.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class Utils {

    private Utils() {
    }

    /**
     * Populates the database with data from the test store. To use ONLY IN UNIT
     * TESTS to avoid code duplication.
     */
    public static void loadStoreIntoDatabase(DataHandler dataHandler) throws IOException, SQLException {
        /*
        !!!!!!!!!!NAO DESCOMENTAR ISTO||||||||||||||||||||||
                */
        /*dataHandler.scriptRunner("./src/main/resources/store/drops.sql");
        dataHandler.scriptRunner("./src/main/resources/store/creates.sql");
        dataHandler.scriptRunner("./src/main/resources/store/triggers.sql");
        dataHandler.scriptRunner("./src/main/resources/store/inserts.sql");
        dataHandler.scriptRunner("./src/main/resources/store/functions.sql");
        dataHandler.scriptRunner("./src/main/resources/store/procedures.sql");*/
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0.
     * Uses Haversine method as its base.
     * <p>
     * latitudeUser, longitudeUser user locatio;
     * latitudePark, longitudePark Park loctation;
     * height of of Park;
     *
     * @param latitudeUser
     * @param longitudeUser
     * @param heightUser
     * @param latitudePark
     * @param longitudePark
     * @param heightPark
     * @return distance
     */
    public static double calculateDistanceBetweenTwoCoordinates(
        Double latitudeUser, Double longitudeUser, Double heightUser,
        Double latitudePark, Double longitudePark, Double heightPark) {
        final int earthRadius = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(latitudePark - latitudeUser);
        double lonDistance = Math.toRadians(longitudePark - longitudeUser);
        double heightDistance = Math.abs(heightUser - heightPark);
        double aux1 = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(latitudeUser)) * Math.cos(Math.toRadians(latitudePark))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double aux2 = 2 * Math.atan2(Math.sqrt(aux1), Math.sqrt(1 - aux1));
        double distance = earthRadius * aux2 * 1000; // convert from km to meters

        distance = Math.pow(distance, 2) + Math.pow(heightDistance, 2);

        return Math.sqrt(distance);
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0.
     * Uses Haversine method as its base.
     * <p>
     * latitudeUser, longitudeUser user locatio;
     * latitudePark, longitudePark Park loctation;
     * height of of Park;
     *
     * @param latitudeUser
     * @param longitudeUser
     * @param latitudePark
     * @param longitudePark
     * @return distance
     */
    public static double calculateFlatDistanceBetweenTwoCoordinates(
        Double latitudeUser, Double longitudeUser,
        Double latitudePark, Double longitudePark) {
        return calculateDistanceBetweenTwoCoordinates(latitudeUser, longitudeUser, 0.0, latitudePark, longitudePark, 0.0);
    }

    /**
     * Method that returns the amount of necessary energy to travel between two Locations
     *
     * @param user
     * @param bike
     * @param firstLocation
     * @param secondLocation
     * @param route
     * @return energy in Wh, Double, necessary energy to travel between two locations
     */
    public static Double calculateEnergyToTravelBetweenLocations(User user,
                                                                 Bike bike,
                                                                 Location firstLocation,
                                                                 Location secondLocation,
                                                                 Double speed,
                                                                 Route route) {

        final Double g = 9.8; //earth gravitational aceleration m/s2

        Double distance = Utils.calculateFlatDistanceBetweenTwoCoordinates(
            firstLocation.getGeoLatitude(),
            firstLocation.getGeoLongitude(),
            secondLocation.getGeoLatitude(),
            secondLocation.getGeoLongitude());
        Double heightDif = secondLocation.getGeoAltitude() - firstLocation.getGeoAltitude();
        // grade, declive = tg(alfa) // sin(angle) = heightDif / distance
        double slope;
        if (distance > 0) {
            slope = heightDif / distance; // sine of slope
        } else {
            return 0.0; // if distance is 0, no energy is spent
        }


        Double mediumVel = speed;
        Double mBike = 25D; //medium mass bike(25kg)
        Double mUser = 75D; //medium mass bike(25kg)
        if (user != null && user.getWeight() != null) {
            mUser = user.getWeight();
            if (mediumVel == null) {
                mediumVel = user.getAvgSpeed();
            }
        }
        if (mediumVel == null) {
            mediumVel = 20.0 * 1000 / 3600;
        }
        double dragCoefficient = 1.15; // cyclist Tops position
        if (bike != null && bike.getWeight() != null) {
            mBike = bike.getWeight();
            dragCoefficient = bike.getDragCoefficient();
        }
        double mass = mBike + mUser; // mBike + (mUser or actual user weight)

        double wind = 0.0;
        double rrc = 0.0053; // avg of rolling resistance for bikes on different road types
        if (route != null) {
            wind = WindUtils.calculateRelativeWindSpeed(route.getWindDirection(), route.getWindSpeed(), firstLocation, secondLocation);
            rrc = route.getAerodynamicCoefficient();
        }
        final double airDensity = 1.225; // kg/m^3 at sea level (negligible changes within a city, which is the range of an E-BIKE
        double airDragSpeed = (Math.abs(wind) < 1.5) ? Math.pow(mediumVel, 3) : (mediumVel * Math.pow(wind, 2));
        double frontalArea = 0.632; // cyclist Tops position;
        double direction = wind > 0 ? -1 : 1;
        double powerAirDrag = direction * 0.5 * airDensity * airDragSpeed * dragCoefficient * frontalArea;

        double powerRollingResistance = mediumVel * mass * g * rrc;

        double powerGradient = mediumVel * mass * g * slope;

        double powerAcceleration = 0.0; // as we are assuming constant speed throughout the ride, acceleration is 0

        double driveTrainEfficiency = 0.93;

        // total power per second
        double totalPower = (powerAirDrag + powerRollingResistance + powerGradient + powerAcceleration) / driveTrainEfficiency;

        double timeDiff = Utils.calculateDistanceBetweenTwoCoordinates(
            firstLocation.getGeoLatitude(),
            firstLocation.getGeoLongitude(),
            firstLocation.getGeoAltitude(),
            secondLocation.getGeoLatitude(),
            secondLocation.getGeoLongitude(),
            secondLocation.getGeoAltitude()) / mediumVel;

        //          W     *     h
        return totalPower * timeDiff / 3600;
    }

    /**
     * @param hour
     * @return
     */
    public static String convertHoursToHoursMinutesSeconds(Double hour) {
        if (hour == null) {
            return null;
        }
        Integer hours = hour.intValue();
        Double minutes = (hour * 60) % 60;
        Double seconds = (hour * (60 * 60)) % 60;
        return (String.format("%s(h) %s(m) %s(s)", hours, minutes.intValue(), seconds.intValue()));
    }

    public static Long getTimeDifference(String time1, String time2) {
        if (time1 == null || time2 == null) {
            return null;
        }

        Date date1 = convertStringToDate(time1);
        Date date2 = convertStringToDate(time2);
        long time1Millis;
        long time2Millis;

        if (date1 != null && date2 != null) {
            time1Millis = date1.getTime();
            time2Millis = date2.getTime();
        } else {
            return null;
        }

        return time1Millis - time2Millis;
    }

    public static Long getTimeDifferenceInSeconds(String time1, String time2) {
        Long seconds = getTimeDifference(time1, time2);
        return seconds == null ? null : TimeUnit.SECONDS.convert(Math.abs(seconds), TimeUnit.MILLISECONDS);
    }

    public static Long getTimeDifferenceInMinutes(String time1, String time2) {
        Long seconds = getTimeDifferenceInSeconds(time1, time2);
        return seconds == null ? null : seconds / 60;
    }

    public static Long getTimeDifferenceInDays(String time1, String time2) {
        Long diffInMinutes = getTimeDifferenceInMinutes(time1, time2);
        return diffInMinutes == null ? null : diffInMinutes / 1440;
    }

    public static Long getTimeDifferenceInMinutes(Date timeStart, Date timeFinish) {
        if (timeStart == null || timeFinish == null) {
            return null;
        }
        long diffInMillis = Math.abs(timeStart.getTime() - timeFinish.getTime());
        return TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    public static Long getTimeDifferenceInDays(Date timeStart, Date timeFinish) {
        Long diffInMinutes = getTimeDifferenceInMinutes(timeStart, timeFinish);
        if (diffInMinutes == null) {
            return null;
        } else {
            return diffInMinutes / 1440;
        }
    }

    public static String convertDateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }

    public static Date convertStringToDate(String string) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(string);
        } catch (Exception e) {
            return null;
        }
    }

}
