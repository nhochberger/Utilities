package hochberger.utilities.geo;

import java.lang.reflect.Field;

public class GeoInformation {

    private String ip;
    private String country_code;
    private String country_name;
    private String region_code;
    private String region_name;
    private String city;
    private String zip_code;
    private String time_zone;
    private float latitude;
    private float longitude;
    private String metro_code;

    public GeoInformation() {
        super();
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public String getCountryCode() {
        return this.country_code;
    }

    public void setCountryCode(final String countryCode) {
        this.country_code = countryCode;
    }

    public String getCountryName() {
        return this.country_name;
    }

    public void setCountryName(final String countryName) {
        this.country_name = countryName;
    }

    public String getRegionCode() {
        return this.region_code;
    }

    public void setRegionCode(final String regionCode) {
        this.region_code = regionCode;
    }

    public String getRegionName() {
        return this.region_name;
    }

    public void setRegionName(final String regionName) {
        this.region_name = regionName;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getZipCode() {
        return this.zip_code;
    }

    public void setZipCode(final String zipCode) {
        this.zip_code = zipCode;
    }

    public String getTimeZone() {
        return this.time_zone;
    }

    public void setTimeZone(final String timeZone) {
        this.time_zone = timeZone;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(final float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public void setfloatitude(final float longitude) {
        this.longitude = longitude;
    }

    public String getMetroCode() {
        return this.metro_code;
    }

    public void setMetroCode(final String metroCode) {
        this.metro_code = metroCode;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        result.append("GeoInformation: ");
        for (final Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            result.append(field.getName()).append(": ");
            try {
                result.append(field.get(this));
            } catch (IllegalArgumentException | IllegalAccessException e) {
                result.append("n/a");
            }
            result.append(", ");
        }
        return result.toString();
    }
}
