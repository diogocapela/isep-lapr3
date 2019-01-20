package lapr.project.model;

import java.util.Objects;

public class Route {
    private static final String CLASSNAME = "ROUTE";

    private Integer idRoute = 0;
    private Double latitudeA;
    private Double longitudeA;
    private Double latitudeB;
    private Double longitudeB;
    private String routeDirection; // is either "bi" or "uni". For "uni" the direction is from the first to the second point
    private Double aerodynamicCoefficient;
    private Double windDirection;
    private Double windSpeed;

    public Route() {

    }

    public Route(Double latitudeA, Double longitudeA, Double latitudeB, Double longitudeB, String routeDirection, Double aerodynamicCoefficient, Double windDirection, Double windSpeed) {
        this.latitudeA = latitudeA;
        this.longitudeA = longitudeA;
        this.latitudeB = latitudeB;
        this.longitudeB = longitudeB;
        this.routeDirection = routeDirection;
        this.aerodynamicCoefficient = aerodynamicCoefficient;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Double getLatitudeA() {
        return latitudeA;
    }

    public void setLatitudeA(Double latitudeA) {
        this.latitudeA = latitudeA;
    }

    public Double getLongitudeA() {
        return longitudeA;
    }

    public void setLongitudeA(Double longitudeA) {
        this.longitudeA = longitudeA;
    }

    public Double getLatitudeB() {
        return latitudeB;
    }

    public void setLatitudeB(Double latitudeB) {
        this.latitudeB = latitudeB;
    }

    public Double getLongitudeB() {
        return longitudeB;
    }

    public void setLongitudeB(Double longitudeB) {
        this.longitudeB = longitudeB;
    }

    public String getRouteDirection() {
        return routeDirection;
    }

    public void setRouteDirection(String routeDirection) {
        this.routeDirection = routeDirection;
    }

    public Double getAerodynamicCoefficient() {
        return aerodynamicCoefficient;
    }

    public void setAerodynamicCoefficient(Double aerodynamicCoefficient) {
        this.aerodynamicCoefficient = aerodynamicCoefficient;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Route route = (Route) o;
        return Objects.equals(idRoute, route.idRoute);
    }

    @Override
    public int hashCode() {
        return (CLASSNAME + idRoute).hashCode();
    }

    @Override
    public String toString() {
        return "Route{" +
            "idRoute=" + idRoute +
            ", latitudeA=" + latitudeA +
            ", longitudeA=" + longitudeA +
            ", latitudeB=" + latitudeB +
            ", longitudeB=" + longitudeB +
            ", routeDirection='" + routeDirection + '\'' +
            ", aerodynamicCoefficient=" + aerodynamicCoefficient +
            ", windDirection=" + windDirection +
            ", windSpeed=" + windSpeed +
            '}';
    }
}
