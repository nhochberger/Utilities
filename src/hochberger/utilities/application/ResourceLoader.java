package hochberger.utilities.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceLoader {

    public ResourceLoader() {
        super();
    }

    public static InputStream loadAsStream(final String filePath) {
        return ClassLoader.getSystemResourceAsStream(filePath);
    }

    public static File loadFile(final String filePath)
            throws FileNotFoundException {
        // throw new NotYetImplementedException();
        final URL systemResource = ClassLoader.getSystemResource(filePath);
        try {
            final File file = new File(systemResource.toURI());
            return file;
        } catch (final URISyntaxException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        // InputStream resourceAsStream = ClassLoader
        // .getSystemResourceAsStream(filePath);
        // String[] split = filePath.split("\\.");
        // File tempFile = File.createTempFile("custos_tmp_", "."
        // + split[split.length - 1]);
        // FileWriter writer = new FileWriter(tempFile);
        // InputStreamReader reader = new
        // InputStreamReader(resourceAsStream);
        // int readByte = -1;
        // while (-1 != (readByte = reader.read())) {
        // writer.write(readByte);
        // }
        // Closer.close(reader);
        // Closer.close(writer);
        // return tempFile;
        // // return new
        // File(ClassLoader.getSystemResource(filePath).toURI());
        // // } catch (final URISyntaxException e) {
        // // throw new FileNotFoundException(filePath);
    }
}
