package hochberger.utilities.geo;

public class NullGeoInformation extends GeoInformation {

    private static final String N_A = "n/a";

    public NullGeoInformation() {
        super();
    }

    @Override
    public String getIp() {
        return N_A;
    }

    @Override
    public String getCountryCode() {
        return N_A;
    }

    @Override
    public String getCountryName() {
        return N_A;
    }

    @Override
    public String getRegionCode() {
        return N_A;
    }

    @Override
    public String getRegionName() {
        return N_A;
    }

    @Override
    public String getCity() {
        return N_A;
    }

    @Override
    public String getZipCode() {
        return N_A;
    }

    @Override
    public String getTimeZone() {
        return N_A;
    }

    @Override
    public float getLatitude() {
        return 0.0f;
    }

    @Override
    public float getLongitude() {
        return 0.0f;
    }

    @Override
    public String getMetroCode() {
        return N_A;
    }
}
