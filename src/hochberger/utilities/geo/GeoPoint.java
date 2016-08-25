package hochberger.utilities.geo;

public class GeoPoint {

    private static final int MEAN_EARTH_RADIUS = 6371;
    private final double lat;
    private final double lon;
    private final double alt;

    public GeoPoint(final double lat, final double lon, final double alt) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
    }

    public double getAlt() {
        return this.alt;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    /**
     * Determines the approximate distance between two GeoPoints based on the
     * formula for equirectangular approximation described at
     * <a href="http://www.movable-type.co.uk/scripts/latlong.html">http://www.
     * movable-type.co.uk/scripts/latlong.htm</a>
     *
     * @param other
     *            - the point to which the distance is to be measured
     * @return the distance between this point and the point given by other
     */
    public double distanceTo(final GeoPoint other) {
        final double deltaX = Math.toRadians(other.lon - this.lon)
                * Math.cos(Math.toRadians(other.lat + this.lat) / 2d);
        final double deltaY = Math.toRadians(other.lat - this.lat);
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2))
                * MEAN_EARTH_RADIUS;
    }
}
