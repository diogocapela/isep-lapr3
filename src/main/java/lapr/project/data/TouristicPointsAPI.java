package lapr.project.data;

import lapr.project.model.TouristicPoint;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TouristicPointsAPI extends DatabaseAPI {

    public TouristicPointsAPI() {
        super();
    }

    public TouristicPointsAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }


    public List<TouristicPoint> getAllTouristicPoints(Integer idTouristicPoint, String descricao, Double geoLatitude, Double geoLongitude, Double geoAltitude) {
        List<TouristicPoint> touristicPointsList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Touristic_point WHERE 1=1 ");
        if (idTouristicPoint != null)
            query.append(" AND id_touristic_point = ").append(idTouristicPoint);
        if (descricao != null)
            query.append(" AND descricao = ").append(descricao);
        if (geoLatitude != null)
            query.append(" AND geo_latitude = ").append(geoLatitude);
        if (geoLongitude != null)
            query.append(" AND geo_longitude = ").append(geoLongitude);
        if (geoAltitude != null)
            query.append(" AND geo_altitude = ").append(geoAltitude);

        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                touristicPointsList.add(extractTouristicPointFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return touristicPointsList;
    }

    public List<TouristicPoint> getAllTouristicPoints() {
        return getAllTouristicPoints(null, null, null, null, null);
    }

    /**
     * Queries the database directly and returns a specific park by id.
     *
     * @param id
     * @return
     */
    public TouristicPoint getTouristicPointById(Integer id) {
        TouristicPoint tp = null;

        List<TouristicPoint> tpList = getAllTouristicPoints(id, null, null, null, null);

        if (!tpList.isEmpty()) {
            tp = tpList.get(0);
        }

        return tp;
    }

    /**
     * Queries the database directly and returns a specific park by by coordinates.
     */
    public TouristicPoint getTouristicPointByCoordinates(Double latitude, Double longitude) {
        TouristicPoint tp = null;

        List<TouristicPoint> tpList = getAllTouristicPoints(null, null, latitude, longitude, null);

        if (!tpList.isEmpty()) {
            tp = tpList.get(0);
        }

        return tp;
    }


    /**
     * Removed a specific touristic point from the database, return it's id.
     */
    public Integer removeTouristicPointById(Integer id) {
        Integer idTouristicPointRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeTouristic_pointById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, id);
            callableStatement.execute();
            idTouristicPointRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idTouristicPointRemoved;
    }

    public Integer addTouristicPoint(TouristicPoint tp) {
        return addTouristicPoint(tp.getDescricao(), tp.getGeoLatitude(), tp.getGeoLongitude(), tp.getGeoAltitude());
    }

    private Integer addTouristicPoint(String descricao, Double geoLatitude, Double geoLongitude, Double geoAltitude) {
        Integer idTouristicPointAdded = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call addTouristic_point(?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setString(2, descricao);
            callableStatement.setDouble(3, geoLatitude);
            callableStatement.setDouble(4, geoLongitude);
            callableStatement.setDouble(5, geoAltitude);
            callableStatement.execute();
            idTouristicPointAdded = callableStatement.getInt(1);
            // 3. Close all connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idTouristicPointAdded;
    }


}
