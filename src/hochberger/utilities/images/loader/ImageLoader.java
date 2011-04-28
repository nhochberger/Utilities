package hochberger.utilities.images.loader;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageLoader {

    private ImageLoader() {
        super();
    }

    public static Image loadImage(final String filePath) {
        return Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource(filePath));
    }

    public static Icon loadIcon(final String filePath) {
        return new ImageIcon(loadImage(filePath));
    }
}
