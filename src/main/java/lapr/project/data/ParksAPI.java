package lapr.project.data;

import lapr.project.model.Park;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParksAPI extends DatabaseAPI {

    public ParksAPI() {
        super();
    }

    public ParksAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    /**
     * Queries the database directly and returns a list with all the parks.
     */
    public List<Park> getAllParks(Integer idPark, String descricao, Double geoLatitude, Double geoLongitude, Double geoAltitude, Integer maxCapacityElectric, Integer maxCapacityStandard, Double voltage, Double intensity, Boolean isActive) {
        List<Park> parkList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Park WHERE 1=1 ");
        if (idPark != null)
            query.append(" AND id_park = ").append(idPark);
        if (descricao != null)
            query.append(" AND descricao = ").append(descricao);
        if (geoLatitude != null)
            query.append(" AND geo_latitude = ").append(geoLatitude);
        if (geoLongitude != null)
            query.append(" AND geo_longitude = ").append(geoLongitude);
        if (geoAltitude != null)
            query.append(" AND geo_altitude = ").append(geoAltitude);
        if (maxCapacityElectric != null)
            query.append(" AND max_capacity_electric = ").append(maxCapacityElectric);
        if (maxCapacityStandard != null)
            query.append(" AND max_capacity_standard = ").append(maxCapacityStandard);
        if (intensity != null)
            query.append(" AND intensity = ").append(intensity);
        if (voltage != null)
            query.append(" AND voltage = ").append(voltage);
        if (isActive != null)
            query.append(" AND is_active = ").append(isActive ? "1" : "0");

        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                parkList.add(extractParkFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return parkList;
    }

    /**
     * Queries the database directly and returns a list with all the parks.
     */
    public List<Park> getAllParks() {
        return getAllParks(null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * Queries the database directly and returns a specific park by id.
     */
    public Park getParkById(Integer id) {
        Park park = null;
        List<Park> parkList = getAllParks(id, null, null, null, null, null, null, null, null, null);

        if (!parkList.isEmpty()) {
            park = parkList.get(0);
        }

        return park;
    }

    /**
     * Queries the database directly and returns a specific park by coordinates.
     */
    public Park getParkByCoordinates(Double latitutide, Double longitude) {
        Park park = null;

        List<Park> parkList = getAllParks(null, null, latitutide, longitude, null, null, null, null, null, null);

        if (!parkList.isEmpty()) {
            park = parkList.get(0);
        }

        return park;
    }

    /**
     * Removed a specific park from the database, return it's id.
     */
    public Integer removeParkById(Integer id) {
        Integer idParkRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeParkById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, id);
            callableStatement.execute();
            idParkRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idParkRemoved;
    }


    /**
     * Update a specific park from the database,  given it's park, return it's id.
     */
    public Integer updatePark(Park park) {
        return updatePark(park.getIdPark(), park.getDescricao(), park.getGeoLatitude(), park.getGeoLongitude(), park.getGeoAltitude(), park.getMaxCapacityElectric(), park.getMaxCapacityStandard(), park.getIntensity(), park.getVoltage(), park.getActive());
    }

    /**
     * Make the update call to the database
     */
    private Integer updatePark(Integer idPark, String descricao, Double geoLatitude, Double geoLongitude, Double geoAltitude, Integer maxCapacityElectric, Integer maxCapacityStandard, Double intensity, Double voltage, boolean isActive) {
        Integer idParkUpdated = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call updatePark(?,?,?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, idPark);
            callableStatement.setString(3, descricao);
            callableStatement.setDouble(4, geoLatitude);
            callableStatement.setDouble(5, geoLongitude);
            callableStatement.setDouble(6, geoAltitude);
            callableStatement.setInt(7, maxCapacityElectric);
            callableStatement.setInt(8, maxCapacityStandard);
            callableStatement.setDouble(9, intensity);
            callableStatement.setDouble(10, voltage);
            callableStatement.setInt(11, isActive ? 1 : 0);
            callableStatement.execute();
            idParkUpdated = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idParkUpdated;
    }


    /**
     * Deactivate a park
     */
    public Integer deactivateParkById(Integer idPark) {
        Integer idParkDeactivated = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call deactivateParkById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, idPark);
            callableStatement.execute();
            idParkDeactivated = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idParkDeactivated;
    }


    public Integer addPark(Park p) {
        return addPark(p.getDescricao(), p.getGeoLatitude(), p.getGeoLongitude(), p.getGeoAltitude(), p.getMaxCapacityElectric(), p.getMaxCapacityStandard(), p.getIntensity(), p.getVoltage(), p.getActive());
    }

    private Integer addPark(String descricao, Double geoLatitude, Double geoLongitude, Double geoAltitude, Integer maxCapacityElectric, Integer maxCapacityStandard, Double intensity, Double voltage, boolean isActive) {
        Integer idParkAdded = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call addPark(?,?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setString(2, descricao);
            callableStatement.setDouble(3, geoLatitude);
            callableStatement.setDouble(4, geoLongitude);
            callableStatement.setDouble(5, geoAltitude);
            callableStatement.setInt(6, maxCapacityElectric);
            callableStatement.setInt(7, maxCapacityStandard);
            callableStatement.setDouble(8, intensity);
            callableStatement.setDouble(9, voltage);
            callableStatement.setInt(10, isActive ? 1 : 0);
            callableStatement.execute();
            idParkAdded = callableStatement.getInt(1);
            // 3. Close all connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idParkAdded;
    }

}
