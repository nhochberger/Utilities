package hochberger.utilities.geo;

/**
 *
 * @author Nico
 *
 * @see <a href="https://en.wikipedia.org/wiki/Geographic_coordinate_system#
 *      Expressing_latitude_and_longitude_as_linear_units">https://en.
 *      wikipedia.org/wiki/Geographic_coordinate_system#
 *      Expressing_latitude_and_longitude_as_linear_units</a>
 */
public class ToMeters {

    private ToMeters() {
        super();
    }

    public static double fromLat(final double lat) {
        return 111132.92 - 559.82 * cos(2d * lat) + 1.175 * cos(4d * lat)
                - 0.0023 * cos(6d * lat);
    }

    public static double fromLon(final double lon) {
        return 111412.84 * cos(lon) - 93.5 * cos(3d * lon)
                + 0.118 * cos(5d * lon);
    }

    private static double cos(final double a) {
        return Math.cos(a);
    }

}
