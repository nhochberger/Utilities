package hochberger.utilities.geo;

import hochberger.utilities.http.StringHttpRequest;

public class GeoInformationRequest extends StringHttpRequest {

    private static final String FREEGEOIP_JSON = "http://freegeoip.net/json/";

    public GeoInformationRequest() {
        super(FREEGEOIP_JSON);
    }
}
