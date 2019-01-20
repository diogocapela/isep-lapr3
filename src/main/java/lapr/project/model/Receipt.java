package lapr.project.model;

public class Receipt {

    private static final String CLASSNAME = "RECEIPT";

    private Integer idReceipt;
    private Integer idInvoice;
    private Integer idUser;
    private Double cost;

    public Receipt() {
    }

    public Receipt(Integer idReceipt, Integer idInvoice, Integer idUser, Double cost) {
        this.idReceipt = idReceipt;
        this.idInvoice = idInvoice;
        this.idUser = idUser;
        this.cost = cost;
    }

    public Integer getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(Integer idReceipt) {
        this.idReceipt = idReceipt;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Receipt receipt = (Receipt) o;
        return idReceipt.equals(receipt.idReceipt);
    }

    @Override
    public int hashCode() {
        return (CLASSNAME + idReceipt).hashCode();
    }

    @Override
    public String toString() {
        return "Receipt{" +
            "idReceipt=" + idReceipt +
            "idInvoice=" + idInvoice +
            ", idUser=" + idUser +
            ", cost=" + cost +
            '}';
    }
}
