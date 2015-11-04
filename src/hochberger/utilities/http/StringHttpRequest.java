package hochberger.utilities.http;

import hochberger.utilities.files.Closer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StringHttpRequest {

    private final String urlPath;

    public StringHttpRequest(final String urlPath) {
        super();
        this.urlPath = urlPath;
    }

    public String performRequest() throws IOException {
        final URL url = new URL(this.urlPath);
        final HttpURLConnection connection = (HttpURLConnection) url
                .openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.connect();

        final BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        final StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        Closer.close(in);
        connection.disconnect();
        return response.toString();
    }

    public String performRequest(final int retries) throws IOException {
        IOException exception = null;
        for (int i = 0; i < retries; i++) {
            try {
                return performRequest();
            } catch (final IOException e) {
                exception = e;
            }
        }
        throw exception;
    }
}
