package lapr.project.model;

public class Bike {


    // Instance Variables
    //---------------------------------------------------------------------

    private Integer idBike = null;
    private String description;
    private Double dragCoefficient;
    private Integer idPark;
    private BikeType type;
    private Double currentBattery;
    private Double maxBattery;
    private Double weight = 0.0;
    private String batteryType = "a";
    private boolean isActive;

    // Constructors
    //---------------------------------------------------------------------


    public Bike() {
        // empty constructor
    }

    public Bike(Bike b) {
        idBike = b.idBike;
        idPark = b.idPark;
        type = b.type;
        currentBattery = b.currentBattery;
        maxBattery = b.maxBattery;
        weight = b.weight;
        batteryType = b.batteryType;
        isActive = b.isActive;
        description = b.description;
        dragCoefficient = b.dragCoefficient;
    }

    // Getters & Setters
    //---------------------------------------------------------------------

    public Integer getIdBike() {
        return this.idBike;
    }

    public void setIdBike(Integer idBike) {
        this.idBike = idBike;
    }

    public Integer getIdPark() {
        return idPark;
    }

    public void setIdPark(Integer idPark) {
        this.idPark = idPark;
    }

    public BikeType getType() {
        return type;
    }

    public void setType(String type) {
        switch (type.toUpperCase()) {
            case "MOUNTAIN":
                this.type = BikeType.MOUNTAIN;
                break;
            case "ELECTRICAL":
                this.type = BikeType.ELECTRICAL;
                break;
            case "ROAD":
                this.type = BikeType.ROAD;
                break;
            default:
                this.type = BikeType.MOUNTAIN;
        }
    }

    public Double getCurrentBattery() {
        return currentBattery;
    }

    public void setCurrentBattery(Double currentBattery) {
        this.currentBattery = currentBattery;
    }

    /**
     * Gets the current percentage of the bike
     *
     * @return
     */
    public Long getCurrentBatteryLevel() {
        if (this.type == BikeType.ELECTRICAL) {
            return Math.round((this.getCurrentBattery() / this.getMaxBattery() * 100.0));
        } else {
            return 0L;
        }
    }

    public Double getMaxBattery() {
        return maxBattery;
    }

    public void setMaxBattery(Double maxBattery) {
        this.maxBattery = maxBattery;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDragCoefficient() {
        return dragCoefficient;
    }

    public void setDragCoefficient(Double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    // Equals, hashCode & toString
    //---------------------------------------------------------------------

    @Override
    public String toString() {
        if (this.getType() == BikeType.ELECTRICAL) {
            return String.format("Bike - %d - %d - %s - %.2f - %.2f - %b", this.idBike, this.idPark, this.type, this.currentBattery, this.maxBattery, this.isActive);
        }
        return String.format("Bike - %d - %d - %s - %b", this.idBike, this.idPark, this.type, this.isActive);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        Bike bike = (Bike) obj;

        return this.idBike.equals(bike.idBike);
    }

    @Override
    public int hashCode() {
        return this.idBike.hashCode();
    }

}
