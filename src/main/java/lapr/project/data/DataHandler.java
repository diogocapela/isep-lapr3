package lapr.project.data;


import lapr.project.utils.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Exemplo de classe cujas instâncias manipulam dados de BD Oracle.
 */
public class DataHandler {

    private String jdbcUrl;
    private String username;
    private String password;

    /**
     * A ligação à BD.
     */
    private Connection connection;

    /**
     * A invocação de "stored procedures".
     */
    private CallableStatement callableStatement;

    /**
     * Conjunto de resultados retornados por "stored procedures".
     */
    private ResultSet resultSet;

    /**
     * Use connection properties set on file application.properties
     */
    public DataHandler() {
        this.jdbcUrl = System.getProperty("database.url");
        this.username = System.getProperty("database.username");
        this.password = System.getProperty("database.password");
    }

    /**
     * Constrói uma instância de "DataHandler" recebendo, por parâmetro, o URL
     * da BD e as credenciais do utilizador.
     *
     * @param jdbcUrl  o URL da BD.
     * @param username o nome do utilizador.
     * @param password a password do utilizador.
     */
    public DataHandler(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        connection = null;
        callableStatement = null;
        resultSet = null;
    }

    /**
     * Singleton.
     */
    protected Connection getConnection() {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    /**
     * Estabelece a ligação à BD.
     */
    protected void openConnection() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Allows running entire scripts
     *
     * @param fileName
     * @throws IOException
     * @throws SQLException
     */
    public void scriptRunner(String fileName) throws IOException, SQLException {
        Logger.log("Loading data from " + fileName + "...");
        openConnection();
        ScriptRunner runner = new ScriptRunner(getConnection(), false, false);
        runner.runScript(new BufferedReader(new FileReader(fileName)));
        closeAll();
    }

    /**
     * Fecha os objetos "ResultSet", "CallableStatement" e "Connection", e
     * retorna uma mensagem de erro se alguma dessas operações não for bem
     * sucedida. Caso contrário retorna uma "string" vazia.
     */
    protected String closeAll() {
        StringBuilder message = new StringBuilder();

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                message.append(e.getMessage());
                message.append("\n");
            }
            resultSet = null;
        }

        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (Exception e) {
                message.append(e.getMessage());
                message.append("\n");
            }
            callableStatement = null;
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                message.append(e.getMessage());
                message.append("\n");
            }
            connection = null;
        }
        return message.toString();
    }

}
