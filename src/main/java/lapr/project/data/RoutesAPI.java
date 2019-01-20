package lapr.project.data;

import lapr.project.model.Route;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoutesAPI extends DatabaseAPI {

    public RoutesAPI() {
        super();
    }

    public RoutesAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    /**
     * Queries the database directly and returns a list with all the routes.
     */
    public List<Route> getAllRoutes(Integer idRoute, Double latitudeA, Double longitudeA, Double latitudeB, Double longitudeB, String routeDirection, Double aerodynamicCoefficient, Double windDirection, Double windSpeed) {
        List<Route> routeList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Route WHERE 1=1 ");
        if (idRoute != null)
            query.append(" AND id_route = ").append(idRoute);
        if (latitudeA != null)
            query.append(" AND latitude_a = ").append(latitudeA);
        if (longitudeA != null)
            query.append(" AND longitude_a = ").append(longitudeA);
        if (latitudeB != null)
            query.append(" AND latitude_b = ").append(latitudeB);
        if (longitudeB != null)
            query.append(" AND longitude_b = ").append(longitudeB);
        if (routeDirection != null)
            query.append(" AND route_direction = ").append(routeDirection);
        if (aerodynamicCoefficient != null)
            query.append(" AND aerodynamic_coefficient = ").append(aerodynamicCoefficient);
        if (windDirection != null)
            query.append(" AND wind_direction = ").append(windDirection);
        if (windSpeed != null)
            query.append(" AND wind_speed = ").append(windSpeed);

        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                routeList.add(extractRouteFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return routeList;
    }

    /**
     * Queries the database directly and returns a list with all the routes.
     */
    public List<Route> getAllRoutes() {
        return getAllRoutes(null, null, null, null, null, null, null, null, null);
    }


    /**
     * Queries the database directly and returns a specific route by id.
     */
    public Route getRouteById(Integer id) {
        Route route = null;
        List<Route> routeList = getAllRoutes(id, null, null, null, null, null, null, null, null);

        if (!routeList.isEmpty()) {
            route = routeList.get(0);
        }

        return route;
    }

    /**
     * Queries the database directly and returns a specific route by coordinates.
     */
    public Route getRouteByCoordinates(Double latitutideA, Double longitudeA, Double latitutideB, Double longitudeB) {
        Route route = null;
        List<Route> routeList = getAllRoutes(null, latitutideA, longitudeA, latitutideB, longitudeB, null, null, null, null);

        if (!routeList.isEmpty()) {
            route = routeList.get(0);
        } else {
            routeList = getAllRoutes(null, latitutideB, longitudeB, latitutideA, longitudeA, null, null, null, null);
            if (!routeList.isEmpty()) {
                route = routeList.get(0);
            }
        }

        return route;
    }

    /**
     * Removed a specific route from the database, return it's id.
     */
    public Integer removeRouteById(Integer id) {
        Integer idRouteRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeRouteById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, id);
            callableStatement.execute();
            idRouteRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idRouteRemoved;
    }

    /**
     * Update a specific route from the database,  given it's route, return it's id.
     */
    public Integer updateRoute(Route route) {
        return updateRoute(route.getIdRoute(), route.getLatitudeA(), route.getLongitudeA(), route.getLatitudeB(), route.getLongitudeB(), route.getRouteDirection(), route.getAerodynamicCoefficient(), route.getWindDirection(), route.getWindSpeed());
    }

    /**
     * Make the update call to the database
     */
    private Integer updateRoute(Integer idRoute, Double latitudeA, Double longitudeA, Double latitudeB, Double longitudeB, String routeDirection, Double aerodynamicCoefficient, Double windDirection, Double windSpeed) {
        Integer idRouteUpdated = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call updateRoute(?,?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, idRoute);
            callableStatement.setDouble(3, latitudeA);
            callableStatement.setDouble(4, longitudeA);
            callableStatement.setDouble(5, latitudeB);
            callableStatement.setDouble(6, longitudeB);
            callableStatement.setString(7, routeDirection);
            callableStatement.setDouble(8, aerodynamicCoefficient);
            callableStatement.setDouble(9, windDirection);
            callableStatement.setDouble(10, windSpeed);
            callableStatement.execute();
            idRouteUpdated = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idRouteUpdated;
    }

    public Integer addRoute(Route r) {
        return addRoute(r.getLatitudeA(), r.getLongitudeA(), r.getLatitudeB(), r.getLongitudeB(), r.getRouteDirection(), r.getAerodynamicCoefficient(), r.getWindDirection(), r.getWindSpeed());
    }

    private Integer addRoute(Double latitudeA, Double longitudeA, Double latitudeB, Double longitudeB, String routeDirection, Double aerodynamicCoefficient, Double windDirection, Double windSpeed) {
        Integer idRouteAdded = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call addRoute(?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setDouble(2, latitudeA);
            callableStatement.setDouble(3, longitudeA);
            callableStatement.setDouble(4, latitudeB);
            callableStatement.setDouble(5, longitudeB);
            callableStatement.setString(6, routeDirection);
            callableStatement.setDouble(7, aerodynamicCoefficient);
            callableStatement.setDouble(8, windDirection);
            callableStatement.setDouble(9, windSpeed);
            callableStatement.execute();
            idRouteAdded = callableStatement.getInt(1);
            // 3. Close all connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idRouteAdded;
    }

}
