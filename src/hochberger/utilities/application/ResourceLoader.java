package hochberger.utilities.application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import hochberger.utilities.exceptions.NotYetImplementedException;

public class ResourceLoader {

    public ResourceLoader() {
        super();
    }

    public static InputStream loadAsStream(final String filePath) {
        return ClassLoader.getSystemResourceAsStream(filePath);
    }

    public static InputStream loadStream(final String filePath) {
        return ClassLoader.getSystemResourceAsStream(filePath);
    }

    public static File loadFile(final String filePath) throws IOException {
        throw new NotYetImplementedException();
        // final InputStream resourceAsStream = ClassLoader
        // .getSystemResourceAsStream(filePath);
        // final String[] split = filePath.split("\\.");
        // final File tempFile = File.createTempFile("tmp_",
        // "." + split[split.length - 1]);
        // final FileWriter writer = new FileWriter(tempFile);
        // final InputStreamReader reader = new InputStreamReader(
        // resourceAsStream);
        // int readByte = -1;
        // while (-1 != (readByte = reader.read())) {
        // writer.write(readByte);
        // }
        // Closer.close(reader);
        // Closer.close(writer);
        // return tempFile;
        // return new File(ClassLoader.getSystemResource(filePath).toURI());
        // // } catch (final URISyntaxException e) {
        // // throw new FileNotFoundException(filePath);
    }
}
