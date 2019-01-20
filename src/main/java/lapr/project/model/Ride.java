package lapr.project.model;

/**
 * @author teresa
 */
public class Ride {

    private static final String CLASSNAME = "RIDE";

    // Instance Variables
    //---------------------------------------------------------------------
    private Integer idRide = null;
    private Integer idBike;
    private Integer idUser;
    private Integer idStartPark;
    private Integer idEndPark;
    private String timestampStart;
    private String timestampFinish;
    private Double cost = 0.0;
    private Integer idInvoice;

    public Ride() {
        // empty constructor
    }

    /**
     * Method that returns a Ride id
     *
     * @return idRide, Integer, Ride id
     */
    public Integer getIdRide() {
        return this.idRide;
    }

    /**
     * Method that specifies ride id
     *
     * @param idRide, Integer, ride id
     */
    public void setIdRide(Integer idRide) {
        this.idRide = idRide;
    }

    /**
     * Method that returns the bike id
     *
     * @return idBike, Integer, bike id
     */
    public Integer getIdBike() {
        return this.idBike;
    }

    /**
     * Method that specifies bike id
     *
     * @param idBike, Integer, bike id
     */
    public void setIdBike(Integer idBike) {
        this.idBike = idBike;
    }

    /**
     * Method that returns user id
     *
     * @return idUser, Integer, user id
     */
    public Integer getIdUser() {
        return this.idUser;
    }

    /**
     * Method that specifies user id
     *
     * @param idUser, Integer, user id
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * Method that returns start park id
     *
     * @return idStartPark, Integer, start park id
     */
    public Integer getIdStartPark() {
        return this.idStartPark;
    }

    /**
     * Method that specifies start park id
     *
     * @param idStartPark, Integer, start park id
     */
    public void setIdStartPark(Integer idStartPark) {
        this.idStartPark = idStartPark;
    }

    /**
     * Method that returns end park id
     *
     * @return idEndPark, Integer, end park id
     */
    public Integer getIdEndPark() {
        return this.idEndPark;
    }

    /**
     * Method that specifies end park id
     *
     * @param idEndPark, Integer, end park id
     */
    public void setIdEndPark(Integer idEndPark) {
        this.idEndPark = idEndPark;
    }

    /**
     * Method that returns the start timestamp
     *
     * @return timestampStart, Date, timestampStart
     */
    public String getTimestampStart() {
        return this.timestampStart;
    }

    /**
     * Method that specifies the start timestamp
     *
     * @param timestampStart, Date, timestampStart
     */
    public void setTimestampStart(String timestampStart) {
        this.timestampStart = timestampStart;
    }

    /**
     * Method that returns the finish timestamp
     *
     * @return timestampFinish, Date, timestampFinish
     */
    public String getTimestampFinish() {
        return this.timestampFinish;
    }

    /**
     * Method that specifies the finish timestamp
     *
     * @param timestampFinish, Date, timestampFinish
     */
    public void setTimestampFinish(String timestampFinish) {
        this.timestampFinish = timestampFinish;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    /**
     * Method that returns hash code value
     *
     * @return hash, int, hash value
     */
    @Override
    public int hashCode() {
        return (CLASSNAME + this.idRide.toString()).hashCode();
    }

    /**
     * Method that verifies equality between ride and another object
     *
     * @param obj, Object, another object to verify equality
     * @return true if the two are equal or false otherwise
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Ride other = (Ride) obj;

        return this.idRide.equals(other.idRide);
    }

    /**
     * Method that returns a string with the state of the object Ride
     *
     * @return stringRide.toString(), String with the state of the Ride instance
     */
    @Override
    public String toString() {
        StringBuilder stringRide = new StringBuilder(CLASSNAME + ": ");

        stringRide.append("idRide = ").append(idRide).append(", ").
            append("idBike = ").append(idBike).append(", ").
            append("idUser = ").append(idUser).append(", ").
            append("idStartPark = ").append(idStartPark).append(", ").
            append("idEndPark = ").append(idEndPark);

        return stringRide.toString();
    }


}
