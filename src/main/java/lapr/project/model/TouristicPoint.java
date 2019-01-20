package lapr.project.model;

public class TouristicPoint extends Location {

    private static final String CLASSNAME = "TOURISTICPOINT";

    // Instance Variables
    //---------------------------------------------------------------------

    private Integer idTouristicPoint = null;
    private String descricao;
    //extende location portanto ja tem latitude, longitude e altitude

    // Constructors
    //---------------------------------------------------------------------

    public TouristicPoint() {
        // empty constructor
    }

    // Getters & Setters
    //---------------------------------------------------------------------

    public Integer getIdTouristicPoint() {
        return idTouristicPoint;
    }

    public void setIdTouristicPoint(Integer idTouristicPoint) {
        this.idTouristicPoint = idTouristicPoint;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Equals, hashCode & toString
    //---------------------------------------------------------------------

    @Override
    public String toString() {
        return String.format("%s - %d - %.2f - %.2f - %.2f", CLASSNAME, idTouristicPoint, getGeoLatitude(), getGeoLongitude(), getGeoAltitude());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        TouristicPoint that = (TouristicPoint) o;
        return this.idTouristicPoint.equals(that.idTouristicPoint);
    }

    @Override
    public int hashCode() {
        return (CLASSNAME + idTouristicPoint).hashCode();
    }


}
