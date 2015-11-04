package hochberger.utilities.geo;

import java.io.IOException;

import com.google.gson.Gson;

public class GeoInformationProvider {

    public GeoInformationProvider() {
        super();
    }

    public GeoInformation retrieveGeoInformation() {
        try {
            final GeoInformationRequest request = new GeoInformationRequest();
            final String result = request.performRequest();
            return new Gson().fromJson(result, GeoInformation.class);
        } catch (final IOException e) {
            // geo information could not be obtained
            return new NullGeoInformation();
        }
    }
}
