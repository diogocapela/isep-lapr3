package lapr.project.model;

public class Invoice {

    private static final String CLASSNAME = "INVOICE";

    private Integer idInvoice;
    private Integer idUser;
    private Double totalCost;
    private Double moneyPaid;
    private Integer pointsUsed;
    private String status;

    public Invoice() {
    }

    public Invoice(Integer idInvoice, Integer idUser, Double totalCost, Double moneyPaid, Integer pointsUsed, String status) {
        this.idInvoice = idInvoice;
        this.idUser = idUser;
        this.totalCost = totalCost;
        this.moneyPaid = moneyPaid;
        this.pointsUsed = pointsUsed;
        this.status = status;
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(Double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public Integer getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(Integer pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Invoice invoice = (Invoice) o;
        return idInvoice.equals(invoice.idInvoice);
    }

    @Override
    public int hashCode() {
        return (CLASSNAME + idInvoice).hashCode();
    }

    @Override
    public String toString() {
        return "Invoice{" +
            "idInvoice=" + idInvoice +
            ", idUser=" + idUser +
            ", totalCost=" + totalCost +
            ", moneyPaid=" + moneyPaid +
            ", pointsUsed=" + pointsUsed +
            ", status='" + status + '\'' +
            '}';
    }
}
