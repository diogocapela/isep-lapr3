package lapr.project.data;

import lapr.project.model.Invoice;
import oracle.jdbc.OracleTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoicesAPI extends DatabaseAPI {

    public InvoicesAPI() {
        super();
    }

    public InvoicesAPI(String databaseUrl, String databaseUsername, String databasePassword) {
        super(databaseUrl, databaseUsername, databasePassword);
    }

    public List<Invoice> getAllInvoices(Integer idInvoice, Integer idUser, Double totalCost, Double moneyPaid, Integer pointsUsed, String status) {
        List<Invoice> invoiceList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Invoice WHERE 1=1 ");
        if (idInvoice != null)
            query.append(" AND id_invoice = ").append(idInvoice);
        if (idUser != null)
            query.append(" AND id_user = ").append(idUser);
        if (totalCost != null)
            query.append(" AND total_cost = ").append(totalCost);
        if (moneyPaid != null)
            query.append(" AND money_paid = ").append(moneyPaid);
        if (pointsUsed != null)
            query.append(" AND points_used = ").append(pointsUsed);
        if (status != null)
            query.append(" AND status = ").append(status);

        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            resultSet = statement.executeQuery(query.toString());
            // 3. Get the data
            while (resultSet.next()) {
                invoiceList.add(extractInvoiceFromResultSet(resultSet));
            }
            // 4. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return invoiceList;
    }

    public List<Invoice> getAllInvoices() {
        return getAllInvoices(null, null, null, null, null, null);
    }

    public Invoice getInvoiceById(Integer idInvoice) {
        Invoice invoice = null;
        List<Invoice> invoiceList = getAllInvoices(idInvoice, null, null, null, null, null);

        if (!invoiceList.isEmpty()) {
            invoice = invoiceList.get(0);
        }
        return invoice;
    }

    public List<Invoice> getAllInvoicesOfUser(Integer idUser) {
        return getAllInvoices(null, idUser, null, null, null, null);
    }

    public Integer addInvoice(Invoice in) {
        return addInvoice(in.getIdUser(), in.getTotalCost(), in.getMoneyPaid(), in.getPointsUsed(), in.getStatus());
    }

    private Integer addInvoice(Integer idUser, Double totalCost, Double moneyPaid, Integer pointsUsed, String status) {
        Integer idInvoiceAdded = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call addInvoice(?,?,?,?,?) }");
            callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
            callableStatement.setInt(2, idUser);
            callableStatement.setDouble(3, totalCost);
            callableStatement.setDouble(4, moneyPaid);
            callableStatement.setInt(5, pointsUsed);
            callableStatement.setString(6, status);
            callableStatement.execute();
            idInvoiceAdded = callableStatement.getInt(1);
            // 3. Close all connection
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idInvoiceAdded;
    }

    public Integer removeInvoiceById(Integer id) {
        Integer idInvoiceRemoved = null;
        try {
            // 1. Start the connection
            startAll();
            // 2. Query the database
            callableStatement = connection.prepareCall("{ ? = call removeInvoiceById(?) }");
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER);
            callableStatement.setInt(2, id);
            callableStatement.execute();
            idInvoiceRemoved = callableStatement.getInt(1);
            // 3. Close the connection
            closeAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return idInvoiceRemoved;
    }

}
