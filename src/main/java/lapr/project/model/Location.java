package lapr.project.model;

public abstract class Location {

    private Double geoLatitude;
    private Double geoLongitude;
    private Double geoAltitude;

    public Double getGeoLatitude() {
        return geoLatitude;
    }

    public void setGeoLatitude(Double geoLatitude) {
        this.geoLatitude = geoLatitude;
    }

    public Double getGeoLongitude() {
        return geoLongitude;
    }

    public void setGeoLongitude(Double geoLongitude) {
        this.geoLongitude = geoLongitude;
    }

    public Double getGeoAltitude() {
        return geoAltitude;
    }

    public void setGeoAltitude(Double geoAltitude) {
        this.geoAltitude = geoAltitude;
    }

}
