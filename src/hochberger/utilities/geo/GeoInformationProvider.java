package hochberger.utilities.geo;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class GeoInformationProvider {

    private final Logger logger;

    public GeoInformationProvider(final Logger logger) {
        super();
        this.logger = logger;
    }

    public GeoInformation retrieveGeoInformation() {
        try {
            final GeoInformationRequest request = new GeoInformationRequest();
            final String result = request.performRequest();
            GeoInformation information = new Gson().fromJson(result,
                    GeoInformation.class);
            this.logger.info("Location retrieved: " + information);
            return information;
        } catch (final IOException e) {
            this.logger.error("Unable to retrieve geo location information");
            return new NullGeoInformation();
        }
    }
}
