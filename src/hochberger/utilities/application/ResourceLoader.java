package hochberger.utilities.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class ResourceLoader {

    public ResourceLoader() {
        super();
    }

    public static File loadFile(final String filePath)
            throws FileNotFoundException {
        try {
            return new File(ClassLoader.getSystemResource(filePath).toURI());
        } catch (final URISyntaxException e) {
            throw new FileNotFoundException(filePath);
        }
    }
}
