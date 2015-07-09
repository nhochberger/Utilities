package hochberger.utilities.gui.font;

import hochberger.utilities.files.Closer;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

    private FontLoader() {
        super();
    }

    public static Font loadFrom(final String filePath) {
        final InputStream inputStream = ClassLoader
                .getSystemResourceAsStream(filePath);
        Font result = null;
        try {
            result = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (final FontFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            Closer.close(inputStream);
        }
        return result;
    }

    public static Font loadFromWithFallback(final String filePath,
            final Font fallback) {
        Font result = loadFrom(filePath);
        if (null == result) {
            result = fallback;
        }
        return result;
    }
}
