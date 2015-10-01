package hochberger.utilities.application;

import hochberger.utilities.exceptions.NotYetImplementedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceLoader {

    public ResourceLoader() {
        super();
    }

    public static InputStream loadAsStream(final String filePath) {
        return ClassLoader.getSystemResourceAsStream(filePath);
    }

    public static File loadFile(final String filePath)
            throws FileNotFoundException {
        throw new NotYetImplementedException();
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
