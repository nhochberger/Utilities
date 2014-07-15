package hochberger.utilities.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFile {

    private final File source;

    private CopyFile(final File source) {
        super();
        this.source = source;
    }

    public static CopyFile from(final File source) {
        return new CopyFile(source);
    }

    public void to(final File destination) throws IOException {
        if (!destination.getParentFile().exists()) {
            destination.getParentFile().mkdirs();
        }
        if (!destination.exists()) {
            destination.createNewFile();
        }
        FileChannel reader = null;
        FileChannel writer = null;
        try {
            reader = new FileInputStream(this.source).getChannel();
            writer = new FileOutputStream(destination).getChannel();
            writer.transferFrom(reader, 0, reader.size());
        } finally {
            Closer.close(reader);
            Closer.close(writer);
        }
    }
}
