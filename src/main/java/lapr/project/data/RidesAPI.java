package lapr.project.data;

import lapr.project.model.Ride;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RidesAPI extends DatabaseAPI {

    public RidesAPI() {
        super();
    }

    public RidesAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }


    /**
     * Queries the database directly and returns a list with all the rides according to the filters
     */
    public List<Ride> getAllRides(Integer idRide, Integer idBike, Integer idUser, Integer idStartPark, Integer idEndPark, String timestampStart, String timestampFinish, Integer idInvoice) {
        List<Ride> rideList = new ArrayList<>();
        // build dynamic query
        StringBuilder query = new StringBuilder("SELECT * FROM Ride WHERE 1=1 ");
        if (idRide != null)
            query.append(" AND id_ride = ").append(idRide);
        if (idBike != null)
            query.append(" AND id_bike = ").append(idBike);
        if (idUser != null)
            query.append(" AND id_user = ").append(idUser);
        if (idStartPark != null)
            query.append(" AND id_park_start = ").append(idStartPark);
        if (idEndPark != null)
            query.append(" AND id_park_end = ").append(idEndPark);
        if (timestampStart != null)
            query.append(" AND timestamp_start = to_date('").append(timestampStart).append("','yyyy-MM-dd hh:mm:ss')");
        if (timestampFinish != null)
            query.append(" AND timestamp_finish = to_date('").append(timestampFinish).append("','yyyy-MM-dd hh:mm:ss')");
        if (idInvoice != null)
            query.append(" AND id_invoice = ").append(idInvoice);

        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                rideList.add(extractRideFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return rideList;
    }

    /**
     * Queries the database directly and returns a list with all the rides.
     */
    public List<Ride> getAllRides() {
        return getAllRides(null, null, null, null, null, null, null, null);
    }

    public List<Ride> getAllUnfinishedRides() {
        List<Ride> rideList = new ArrayList<>();
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery("SELECT * FROM Ride r where r.id_park_finish is null and r.timestamp_finish is null");
            // 3. Get the data
            while (resultSet.next()) {
                rideList.add(extractRideFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return rideList;
    }

    public List<Ride> getAllUserRidesWithoutInvoice(Integer idUser) {
        List<Ride> rideList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Ride r where r.id_invoice is null ");
        if (idUser != null) {
            query.append(" and r.id_user = ").append(idUser);
        }
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                rideList.add(extractRideFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return rideList;
    }

    public Ride getUnfinishedRideOfUser(Integer idUser) {
        List<Ride> rideList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Ride r where r.id_park_finish is null and r.timestamp_finish is null");
        if (idUser != null)
            query.append(" and r.id_user = ").append(idUser);
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                rideList.add(extractRideFromResultSet(resultSet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return rideList.isEmpty() ? null : rideList.get(0);
    }

    /**
     * Queries the database directly and returns a specific ride by id.
     */
    public Ride getRideById(Integer id) {
        Ride ride = null;

        List<Ride> rideList = getAllRides(id, null, null, null, null, null, null, null);

        if (!rideList.isEmpty()) {
            ride = rideList.get(0);
        }

        return ride;
    }

    /**
     * Queries the database directly and returns a specific ride by invoice ID.
     */
    public List<Ride> getRidesByInvoice(Integer id) {
        return getAllRides(null, null, null, null, null, null, null, id);
    }

    /**
     * Removed a specific Ride from the database, return it's id.
     */
    public Integer removeRideById(Integer id) {
        Integer idRideRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeRideById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, id);
            callableStatement.execute();
            idRideRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idRideRemoved;
    }


    /**
     * Update a specific ride from the database,  given it's park, return it's id.
     */
    public Integer updateRide(Ride ride) {
        return updateRide(ride.getIdRide(), ride.getIdBike(), ride.getIdUser(), ride.getIdStartPark(), ride.getIdEndPark(), ride.getTimestampStart(), ride.getTimestampFinish(), ride.getCost(), ride.getIdInvoice());
    }

    /**
     * Make the update call to the database
     */
    private Integer updateRide(Integer idRide, Integer idBike, Integer idUser, Integer idParkStart, Integer idParkFinish, String timeStampStart, String timeStampFinish, Double cost, Integer idInvoice) {
        Integer idRideUpdated = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call updateRide(?,?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, idRide);
            callableStatement.setInt(3, idBike);
            callableStatement.setInt(4, idUser);
            callableStatement.setInt(5, idParkStart);
            if (idParkFinish == null) {
                callableStatement.setObject(6, null);
            } else {
                callableStatement.setInt(6, idParkFinish);
            }
            callableStatement.setTimestamp(7, Timestamp.valueOf(timeStampStart));
            if (timeStampFinish == null) {
                callableStatement.setObject(8, null);
            } else {
                callableStatement.setTimestamp(8, Timestamp.valueOf(timeStampFinish));
            }
            callableStatement.setDouble(9, cost);
            if (idInvoice == null) {
                callableStatement.setObject(10, null);
            } else {
                callableStatement.setInt(10, idInvoice);
            }
            callableStatement.execute();
            idRideUpdated = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idRideUpdated;
    }


    public Integer addRide(Ride ride) {
        return addRide(ride.getIdBike(), ride.getIdUser(), ride.getIdStartPark(), ride.getIdEndPark(), ride.getTimestampStart(), ride.getTimestampFinish(), ride.getCost(), ride.getIdInvoice());
    }


    private Integer addRide(Integer idBike, Integer idUser, Integer idStartPark, Integer idEndPark, String timestampStart, String timestampFinish, Double cost, Integer idInvoice) {
        Integer idRideAdded = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call addRide(?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setInt(2, idBike);
            callableStatement.setInt(3, idUser);
            callableStatement.setInt(4, idStartPark);
            callableStatement.setObject(5, idEndPark);
            callableStatement.setTimestamp(6, Timestamp.valueOf(timestampStart));
            if (timestampFinish == null) {
                callableStatement.setString(7, null);
            } else {
                callableStatement.setTimestamp(7, Timestamp.valueOf(timestampFinish));
            }
            callableStatement.setDouble(8, cost);
            if (idInvoice == null) {
                callableStatement.setObject(9, null);
            } else {
                callableStatement.setInt(9, idInvoice);
            }
            callableStatement.execute();
            idRideAdded = callableStatement.getInt(1);
            // 3. Close all connections
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idRideAdded;
    }

}
