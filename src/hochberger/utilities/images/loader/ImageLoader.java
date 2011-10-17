package hochberger.utilities.images.loader;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageLoader {

    private ImageLoader() {
        super();
    }

    /*
     * By making the indirection via loadIcon(..), the properties, e.g. width and height,
     * have proper values when the image is returned. 
     */
    public static Image loadImage(final String filePath) {
    	return ((ImageIcon) loadIcon(filePath)).getImage();
    }

    public static Icon loadIcon(final String filePath) {
        return new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource(filePath)));
    }
}
