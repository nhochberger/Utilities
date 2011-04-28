package hochberger.utilities.properties;

import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

    private LoadProperties() {
        super();
    }

    public static Properties from(final String filePath) throws IOException {
        Properties result = new Properties();
        result.load(ClassLoader.getSystemResourceAsStream(filePath));
        return result;
    }
}
