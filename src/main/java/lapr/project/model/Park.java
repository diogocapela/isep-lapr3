/**
 * @author Rafael Marques
 * Sprint 1
 */
package lapr.project.model;

public class Park extends Location {

    private static final String CLASSNAME = "PARK";

    // Instance Variables
    //---------------------------------------------------------------------

    private Integer idPark = null;
    private String descricao;
    private Integer maxCapacityElectric;
    private Integer maxCapacityStandard;
    private Double intensity;
    private Double voltage;
    private Boolean isActive = true;

    // Constructors
    //---------------------------------------------------------------------

    public Park() {
        // empty constructor
    }

    // Getters & Setters
    //---------------------------------------------------------------------

    public Integer getIdPark() {
        return idPark;
    }

    public void setIdPark(Integer idPark) {
        this.idPark = idPark;
    }

    public int getMaxCapacityElectric() {
        return maxCapacityElectric;
    }

    public void setMaxCapacityElectric(int maxCapacityElectric) {
        this.maxCapacityElectric = maxCapacityElectric;
    }

    public int getMaxCapacityStandard() {
        return maxCapacityStandard;
    }

    public void setMaxCapacityStandard(int maxCapacityStandard) {
        this.maxCapacityStandard = maxCapacityStandard;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Double getIntensity() {
        return intensity;
    }

    public void setIntensity(Double intensity) {
        this.intensity = intensity;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String description) {
        this.descricao = description;
    }

    // Equals, hashCode & toString
    //---------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Park other = (Park) obj;

        return this.idPark.equals(other.idPark);
    }

    @Override
    public int hashCode() {
        return (CLASSNAME + this.idPark.toString()).hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s - %d - %f - %f - %f - %d - %d - %b", CLASSNAME, idPark, getGeoLatitude(), getGeoLongitude(), getGeoAltitude(), maxCapacityElectric, maxCapacityStandard, isActive);
    }

}
