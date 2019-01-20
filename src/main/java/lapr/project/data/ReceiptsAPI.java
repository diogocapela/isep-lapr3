package lapr.project.data;

import lapr.project.model.Receipt;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceiptsAPI extends DatabaseAPI {

    public ReceiptsAPI() {
        super();
    }

    public ReceiptsAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    public Integer addReceipt(Receipt re) {
        return addReceipt(re.getIdUser(), re.getCost());
    }

    private Integer addReceipt(Integer idUser, Double cost) {
        Integer idReceiptAdded = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call addReceipt(?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setInt(2, idUser);
            callableStatement.setDouble(3, cost);
            callableStatement.execute();
            idReceiptAdded = callableStatement.getInt(1);
            // 3. Close all connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idReceiptAdded;
    }


    public List<Receipt> getAllReceipts(Integer idReceipt, Integer idInvoice, Integer idUser, Double cost) {
        List<Receipt> receiptList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Receipt WHERE 1=1 ");
        if (idReceipt != null)
            query.append(" AND id_receipt = ").append(idReceipt);
        if (idInvoice != null)
            query.append(" AND id_invoice = ").append(idInvoice);
        if (idUser != null)
            query.append(" AND id_user = ").append(idUser);
        if (cost != null)
            query.append(" AND cost = ").append(cost);

        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                receiptList.add(extractReceiptFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return receiptList;
    }

    public List<Receipt> getAllReceipts() {
        return getAllReceipts(null, null, null, null);
    }

    public Receipt getReceiptById(Integer idReceipt) {
        Receipt receipt = null;
        List<Receipt> receiptList = getAllReceipts(idReceipt, null, null, null);

        if (!receiptList.isEmpty()) {
            receipt = receiptList.get(0);
        }
        return receipt;
    }

    public List<Receipt> getAllReceiptsFromUser(Integer idUser) {
        return getAllReceipts(null, null, idUser, null);
    }

    public Integer removeReceiptById(Integer id) {
        Integer idReceiptRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeReceiptById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, id);
            callableStatement.execute();
            idReceiptRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idReceiptRemoved;
    }

}
