package lapr.project.data;

import lapr.project.model.Bike;
import lapr.project.model.BikeType;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BikesAPI extends DatabaseAPI {

    public BikesAPI() {
        super();
    }

    public BikesAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }


    /**
     * Queries the database directly and returns a list with all the bikes according to the filters
     */
    public List<Bike> getAllBikes(Integer idBike, String description, Double dragCoefficient, Integer idPark, BikeType bikeType, Double currentBattery, Double maxBattery, Double weight, String batteryType, Boolean isActive) {
        List<Bike> bikeList = new ArrayList<>();
        // build dynamic query
        // totally vulnerable to SQLi
        StringBuilder query = new StringBuilder("SELECT * FROM Bike WHERE 1=1 ");
        if (idBike != null)
            query.append(" AND id_bike = ").append(idBike);
        if (description != null)
            query.append(" AND description = '").append(description).append("'");
        if (dragCoefficient != null)
            query.append(" AND drag_coefficient = ").append(dragCoefficient);
        if (idPark != null)
            query.append(" AND id_park = ").append(idPark);
        if (bikeType != null)
            query.append(" AND bike_type = '").append(bikeType.toString()).append("'");
        if (currentBattery != null)
            query.append(" AND current_battery = ").append(currentBattery);
        if (maxBattery != null)
            query.append(" AND max_battery = ").append(maxBattery);
        if (weight != null)
            query.append(" AND weight = ").append(weight);
        if (batteryType != null)
            query.append(" AND batteryType = ").append(batteryType);
        if (isActive != null)
            query.append(" AND is_active = ").append(isActive ? "1" : "0");

        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                bikeList.add(extractBikeFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return bikeList;
    }

    /**
     * Queries the database directly and returns a list with all the bikes.
     */
    public List<Bike> getAllBikes() {
        return getAllBikes(null, null, null, null, null, null, null, null, null, null);
    }


    /**
     * Queries the database directly and returns a specific bike by id.
     */
    public Bike getBikeById(Integer id) {
        Bike bike = null;

        List<Bike> bikeList = getAllBikes(id, null, null, null, null, null, null, null, null, null);

        if (!bikeList.isEmpty()) {
            bike = bikeList.get(0);
        }

        return bike;
    }

    /**
     * Queries the database directly and returns a specific bike by description.
     */
    public Bike getBikeByDescription(String description) {
        Bike bike = null;

        List<Bike> bikeList = getAllBikes(null,  description, null, null, null, null, null, null, null, null);

        if (!bikeList.isEmpty()) {
            bike = bikeList.get(0);
        }

        return bike;
    }

    /**
     * Queries the database directly and returns a list with all the active bikes in a park.
     */
    public List<Bike> getAllActiveBikesFromPark(Integer idPark) {
        return getAllBikes(null, null, null, idPark, null, null, null, null, null, true);
    }

    /**
     * Queries the database directly and returns a list with all the active electrical bikes in a park.
     */
    public List<Bike> getAllElectricalActiveBikesFromPark(Integer idPark) {
        return getAllBikes(null, null, null, idPark, BikeType.ELECTRICAL, null, null, null, null, true);
    }

    /**
     * Queries the database directly and returns a list with all the active mountain bikes in a park.
     */
    public List<Bike> getAllMountainActiveBikesFromPark(Integer idPark) {
        return getAllBikes(null, null, null, idPark, BikeType.MOUNTAIN, null, null, null, null, true);
    }

    /**
     * Queries the database directly and returns a list with all the active road bikes in a park.
     */
    public List<Bike> getAllRoadActiveBikesFromPark(Integer idPark) {
        return getAllBikes(null, null, null, idPark, BikeType.ROAD, null, null, null, null, true);
    }


    /**
     * Removed a specific bike from the database, return it's id.
     */
    public Integer removeBikeById(Integer id) {
        Integer idBikeRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeBikeById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, id);
            callableStatement.execute();
            idBikeRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idBikeRemoved;
    }


    /**
     * Update a specific bike from the database,  given it's bike, return it's id.
     */
    public Integer updateBike(Bike bike) {
        return updateBike(bike.getIdBike(), bike.getDescription(), bike.getDragCoefficient(), bike.getIdPark(), bike.getType().toString(), bike.getCurrentBattery(), bike.getMaxBattery(), bike.getWeight(), bike.getBatteryType(), bike.getIsActive());
    }

    /**
     * Make the update call to the database
     */
    private Integer updateBike(Integer idBike, String description, Double dragCoefficient, Integer idPark, String type, Double currentBattery, Double maxBattery, Double weight, String batteryType, boolean isActive) {
        Integer idRideUpdated = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call updateBike(?,?,?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, idBike);

            if (description == null) {
                callableStatement.setObject(3, description);
            } else {
                callableStatement.setString(3, description);
            }

            if (dragCoefficient == null) {
                callableStatement.setDouble(4, 0.0);
            } else {
                callableStatement.setDouble(4, dragCoefficient);
            }

            if (idPark == null) {
                callableStatement.setObject(5, idPark);
            } else {
                callableStatement.setInt(5, idPark);
            }
            callableStatement.setString(6, type);
            if (currentBattery == null) {
                callableStatement.setObject(7, currentBattery);
            } else {
                callableStatement.setDouble(7, currentBattery);
            }
            if (maxBattery == null) {
                callableStatement.setObject(8, maxBattery);
            } else {
                callableStatement.setDouble(8, maxBattery);
            }
            callableStatement.setDouble(9, weight);
            if (batteryType == null) {
                callableStatement.setObject(10, null);
            } else {
                callableStatement.setString(10, batteryType);
            }
            callableStatement.setInt(11, isActive ? 1 : 0);
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


    /**
     * Deactivate a bike
     */
    public Integer deactivateBikeById(Integer idBike) {
        Integer idBikeDeactivated = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call deactivateBikeById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, idBike);
            callableStatement.execute();
            idBikeDeactivated = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idBikeDeactivated;
    }


    public Integer addBike(Bike b) {
        return addBike(b.getIdPark(), b.getDescription(), b.getDragCoefficient(), b.getType().toString(), b.getCurrentBattery(), b.getMaxBattery(), b.getWeight(), b.getBatteryType(), b.getIsActive());
    }

    private Integer addBike(Integer idPark, String description, Double dragCoefficient, String type, Double currentBattery, Double maxBattery, Double weight, String batteryType, boolean isActive) {
        Integer idAddedBike = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call addBike(?,?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            if (idPark == null) {
                callableStatement.setObject(2, idPark);
            } else {
                callableStatement.setInt(2, idPark);
            }

            if (description == null) {
                callableStatement.setObject(3, description);
            } else {
                callableStatement.setString(3, description);
            }

            if (dragCoefficient == null) {
                callableStatement.setDouble(4, 0.0);
            } else {
                callableStatement.setDouble(4, dragCoefficient);
            }

            callableStatement.setString(5, type);
            if (currentBattery == null) {
                callableStatement.setObject(6, currentBattery);
            } else {
                callableStatement.setDouble(6, currentBattery);
            }
            if (maxBattery == null) {
                callableStatement.setObject(7, maxBattery);
            } else {
                callableStatement.setDouble(7, maxBattery);
            }
            callableStatement.setDouble(8, weight);
            if (batteryType == null) {
                callableStatement.setObject(9, null);
            } else {
                callableStatement.setString(9, batteryType);
            }
            callableStatement.setInt(10, isActive ? 1 : 0);
            callableStatement.execute();
            idAddedBike = callableStatement.getInt(1);
            // 3. Close all connections
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idAddedBike;
    }


}
