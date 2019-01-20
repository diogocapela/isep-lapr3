package lapr.project.data;

import lapr.project.model.User;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersAPI extends DatabaseAPI {

    public UsersAPI() {
        super();
    }

    public UsersAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    /**
     * Queries the database directly and returns a list with all the users.
     */
    public List<User> getAllUsers(Integer idUser, String username, String email, String hashedSaltedPassword, String creditCardNumber, Double height, Double weight, Integer creditPoints, Double avgSpeed, Boolean isAdmin) {
        List<User> userList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM \"User\" WHERE 1=1 ");
        if (idUser != null)
            query.append(" AND id_user = ").append(idUser);
        if (username != null)
            query.append(" AND username = '").append(username).append("'");
        if (email != null)
            query.append(" AND email = '").append(email).append("'");
        if (hashedSaltedPassword != null)
            query.append(" AND password = '").append(hashedSaltedPassword).append("'");
        if (creditCardNumber != null)
            query.append(" AND credit_card_number = '").append(creditCardNumber).append("'");
        if (height != null)
            query.append(" AND height = ").append(height);
        if (weight != null)
            query.append(" AND weight = ").append(weight);
        if (creditPoints != null)
            query.append(" AND credit_points = ").append(creditPoints);
        if (avgSpeed != null)
            query.append(" AND avg_speed = ").append(avgSpeed);
        if (isAdmin != null)
            query.append(" AND is_admin = ").append(isAdmin ? "1" : "0");

        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                userList.add(extractUserFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

        return userList;
    }

    /**
     * Queries the database directly and returns a list with all the users.
     */
    public List<User> getAllUsers() {
        return getAllUsers(null, null, null, null, null, null, null, null, null, null);
    }


    /**
     * Queries the database directly and returns a specific user by id.
     */
    public User getUserById(Integer id) {
        User user = null;

        List<User> userList = getAllUsers(id, null, null, null, null, null, null, null, null, null);

        if (!userList.isEmpty()) {
            user = userList.get(0);
        }

        return user;
    }

    /**
     * Queries the database directly and returns a specific user by email.
     */
    public User getUserByEmail(String email) {
        User user = null;

        List<User> userList = getAllUsers(null, null, email, null, null, null, null, null, null, null);

        if (!userList.isEmpty()) {
            user = userList.get(0);
        }

        return user;
    }

    public User getUserByUsername(String username) {
        User user = null;

        List<User> userList = getAllUsers(null, username, null, null, null, null, null, null, null, null);

        if (!userList.isEmpty()) {
            user = userList.get(0);
        }
        return user;
    }

    /**
     * Removed a specific user from the database,  given it's id, return it's id.
     */
    public Integer removeUserById(Integer id) {
        Integer idUserRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeUserById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, id);
            callableStatement.execute();
            idUserRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idUserRemoved;
    }


    /**
     * Removed a specific user from the database,  given it's email, return it's id.
     */
    public Integer removeUserByEmail(String email) {
        Integer idUserRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeUserByEmail(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setString(2, email);
            callableStatement.execute();
            idUserRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idUserRemoved;
    }


    /**
     * Update a specific user from the database,  given it's park, return it's id.
     */
    public Integer updateUser(User user) {
        return updateUser(user.getIdUser(), user.getUsername(), user.getEmail(), user.getHashedSaltedPassword(), user.getCreditCardNumber(), user.getHeight(), user.getWeight(), user.getCreditPoints(), user.getAvgSpeed(), user.isIsAdmin());
    }

    /**
     * Make the update call to the database
     */
    private Integer updateUser(Integer idUser, String username, String email, String password, String creditCard, Double height, Double weight, Integer creditPoints, Double avgSpeed, boolean isAdmin) {
        Integer idUserUpdated = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call updateUserById(?,?,?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, idUser);
            callableStatement.setString(3, username);
            callableStatement.setString(4, email);
            callableStatement.setString(5, password);
            callableStatement.setString(6, creditCard);
            callableStatement.setDouble(7, height);
            callableStatement.setDouble(8, weight);
            if (creditPoints == null) {
                callableStatement.setInt(9, creditPoints);
            } else {
                callableStatement.setInt(9, creditPoints);
            }
            callableStatement.setDouble(10, avgSpeed);
            callableStatement.setInt(11, isAdmin ? 1 : 0);
            callableStatement.execute();
            idUserUpdated = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idUserUpdated;
    }


    public Integer addUser(User u1) {
        return addUser(u1.getEmail(), u1.getUsername(), u1.getHashedSaltedPassword(), u1.getCreditCardNumber(), u1.getHeight(), u1.getWeight(), u1.getCreditPoints(), u1.getAvgSpeed(), u1.isIsAdmin());
    }

    private Integer addUser(String email, String username, String password, String creditCardNumber, Double height, Double weight, Integer creditPoints, Double avgSpeed, boolean isAdmin) {
        Integer idUserAdded = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call addUser(?,?,?,?,?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setString(2, username);
            callableStatement.setString(3, email);
            callableStatement.setString(4, password);
            callableStatement.setString(5, creditCardNumber);
            callableStatement.setDouble(6, height);
            callableStatement.setDouble(7, weight);
            if (creditPoints == null) {
                callableStatement.setObject(8, null);
            } else {
                callableStatement.setInt(8, creditPoints);
            }
            callableStatement.setDouble(9, avgSpeed);
            callableStatement.setInt(10, isAdmin ? 1 : 0);
            callableStatement.execute();
            idUserAdded = callableStatement.getInt(1);
            // 3. Close all connections
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idUserAdded;
    }

}
