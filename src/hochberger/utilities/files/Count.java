package hochberger.utilities.files;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Count {

    private Count() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * from
     * http://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-
     * java
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static int linesIn(final File file) throws IOException {
        final InputStream is = new BufferedInputStream(
                new FileInputStream(file));
        try {
            final byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }
}
