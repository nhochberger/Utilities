package hochberger.utilities.files;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

public class Closer {

    public Closer() {
        super();
    }

    public static void close(final Closeable closeable) {
        if (null == closeable) {
            return;
        }
        try {
            closeable.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(final Scanner closeable) {
        if (null == closeable) {
            return;
        }
        closeable.close();
    }
}
