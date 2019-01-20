package lapr.project.data;

import lapr.project.model.*;

import java.sql.*;

/**
 * This class adds another way of querying the database and it doesn't
 * conflict with the other present database apis.
 * <p>
 * It allows a safe way of directly querying the database while reusing
 * the code of our model schemas.
 */
public class DatabaseAPI {

    //final variables (code smells)
    private static final String ID_USER = "id_user";
    // Instance variables
    protected Connection connection = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;
    protected CallableStatement callableStatement = null;
    // Database configuration
    private String DATABASE_URL;
    private String DATABASE_USERNAME;
    private String DATABASE_PASSWORD;

    /**
     * Default constructor.
     */
    public DatabaseAPI() {
        DATABASE_URL = System.getProperty("database.url");
        DATABASE_USERNAME = System.getProperty("database.username");
        DATABASE_PASSWORD = System.getProperty("database.password");
    }

    /**
     * Constructor FOR UNIT TESTS ONLY.
     */
    public DatabaseAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        DATABASE_URL = databaseUrl;
        DATABASE_USERNAME = databaseUsername;
        DATABASE_PASSWORD = databasePassword;
    }

    /**
     * Starts the connection. Add this to the start of every method here.
     */
    protected void startAll() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the connection. Add this to the end of every method here.
     */
    protected void closeAll() {
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Takes in a result set instance of an iteration and returns a Bike.
     * To be reused in this API whenever we need to extract a Bike from the database
     * and to avoid code duplication.
     */
    protected Bike extractBikeFromResultSet(ResultSet resultSet) throws SQLException {
        Bike bike = new Bike();
        bike.setIdBike(resultSet.getInt("id_bike"));
        bike.setIdPark(resultSet.getInt("id_park"));
        bike.setType(resultSet.getString("bike_type"));
        bike.setCurrentBattery(resultSet.getDouble("current_battery"));
        bike.setMaxBattery(resultSet.getDouble("max_battery"));
        bike.setIsActive(resultSet.getInt("is_active") == 1);
        bike.setDescription(resultSet.getString("description"));
        bike.setDragCoefficient(resultSet.getDouble("drag_coefficient"));
        bike.setWeight(resultSet.getDouble("weight"));
        bike.setBatteryType(resultSet.getString("battery_type"));
        return bike;
    }

    /**
     * Takes in a result set instance of an iteration and returns a Park.
     * To be reused in this API whenever we need to extract a Park from the database
     * and to avoid code duplication.
     */
    protected Park extractParkFromResultSet(ResultSet resultSet) throws SQLException {
        Park park = new Park();
        park.setIdPark(resultSet.getInt("id_park"));
        park.setGeoLatitude(resultSet.getDouble("geo_latitude"));
        park.setGeoLongitude(resultSet.getDouble("geo_longitude"));
        park.setGeoAltitude(resultSet.getDouble("geo_altitude"));
        park.setMaxCapacityElectric(resultSet.getInt("max_capacity_electric"));
        park.setMaxCapacityStandard(resultSet.getInt("max_capacity_standard"));
        park.setIntensity(resultSet.getDouble("intensity"));
        park.setVoltage(resultSet.getDouble("voltage"));
        park.setActive(resultSet.getInt("is_active") == 1);
        park.setDescricao(resultSet.getString("descricao"));
        return park;
    }

    /**
     * Takes in a result set instance of an iteration and returns a Ride.
     * To be reused in this API whenever we need to extract a Ride from the database
     * and to avoid code duplication.
     */
    protected Ride extractRideFromResultSet(ResultSet resultSet) throws SQLException {
        Ride ride = new Ride();
        ride.setIdRide(resultSet.getInt("id_ride"));
        ride.setIdBike(resultSet.getInt("id_bike"));
        ride.setIdUser(resultSet.getInt(ID_USER));
        ride.setIdStartPark(resultSet.getInt("id_park_start"));
        ride.setIdEndPark(resultSet.getInt("id_park_finish"));
        ride.setTimestampStart(resultSet.getString("timestamp_start"));
        ride.setTimestampFinish(resultSet.getString("timestamp_finish"));
        ride.setCost(resultSet.getDouble("ride_cost"));
        ride.setIdInvoice(resultSet.getInt("id_invoice"));
        return ride;
    }

    /**
     * Takes in a result set instance of an iteration and returns a User.
     * To be reused in this API whenever we need to extract a User from the database
     * and to avoid code duplication.
     */
    protected User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setIdUser(resultSet.getInt(ID_USER));
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setHashedSaltedPassword(resultSet.getString("user_password"));
        user.setCreditCardNumber(resultSet.getString("credit_card_number"));
        user.setHeight(resultSet.getDouble("height"));
        user.setWeight(resultSet.getDouble("weight"));
        user.setCreditPoints(resultSet.getInt("credit_points"));
        user.setIsAdmin(resultSet.getInt("is_admin") == 1);
        user.setAvgSpeed(resultSet.getDouble("avg_speed"));
        return user;
    }

    /**
     * Takes in a result set instance of an iteration and returns a TouristicPoint.
     * To be reused in this API whenever we need to extract a User from the database
     * and to avoid code duplication.
     */
    protected TouristicPoint extractTouristicPointFromResultSet(ResultSet resultSet) throws SQLException {
        TouristicPoint touristicPoint = new TouristicPoint();
        touristicPoint.setIdTouristicPoint(resultSet.getInt("id_touristic_point"));
        touristicPoint.setGeoLatitude(resultSet.getDouble("geo_latitude"));
        touristicPoint.setGeoLongitude(resultSet.getDouble("geo_longitude"));
        touristicPoint.setGeoAltitude(resultSet.getDouble("geo_altitude"));
        touristicPoint.setDescricao(resultSet.getString("descricao"));
        return touristicPoint;
    }

    /**
     * Takes in a result set instance of an iteration and returns a Invoice.
     * To be reused in this API whenever we need to extract a User from the database
     * and to avoid code duplication.
     */
    protected Invoice extractInvoiceFromResultSet(ResultSet resultSet) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setIdInvoice(resultSet.getInt("id_invoice"));
        invoice.setIdUser(resultSet.getInt(ID_USER));
        invoice.setTotalCost(resultSet.getDouble("total_cost"));
        invoice.setMoneyPaid(resultSet.getDouble("money_paid"));
        invoice.setPointsUsed(resultSet.getInt("points_used"));
        invoice.setStatus(resultSet.getString("status"));
        return invoice;
    }

    /**
     * Takes in a result set instance of an iteration and returns a Receipt.
     * To be reused in this API whenever we need to extract a User from the database
     * and to avoid code duplication.
     */
    protected Receipt extractReceiptFromResultSet(ResultSet resultSet) throws SQLException {
        Receipt receipt = new Receipt();
        receipt.setIdReceipt(resultSet.getInt("id_receipt"));
        receipt.setIdUser(resultSet.getInt(ID_USER));
        receipt.setCost(resultSet.getDouble("cost"));
        receipt.setIdInvoice(resultSet.getInt("id_invoice"));
        return receipt;
    }

    /**
     * Takes in a result set instance of an iteration and returns a Route.
     * To be reused in this API whenever we need to extract a User from the database
     * and to avoid code duplication.
     */
    protected Route extractRouteFromResultSet(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setIdRoute(resultSet.getInt("id_route"));
        route.setLatitudeA(resultSet.getDouble("latitude_a"));
        route.setLongitudeA(resultSet.getDouble("longitude_a"));
        route.setLatitudeB(resultSet.getDouble("latitude_b"));
        route.setLongitudeB(resultSet.getDouble("longitude_b"));
        route.setRouteDirection(resultSet.getString("route_direction"));
        route.setAerodynamicCoefficient(resultSet.getDouble("aerodynamic_coefficient"));
        route.setWindDirection(resultSet.getDouble("wind_direction"));
        route.setWindSpeed(resultSet.getDouble("wind_speed"));
        return route;
    }

}
