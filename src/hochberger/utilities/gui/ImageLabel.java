package hochberger.utilities.gui;

import hochberger.utilities.images.loader.ImageLoader;

import javax.swing.Icon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {

    private static final long serialVersionUID = 3794445904000802815L;

    public ImageLabel(final Icon image) {
        super(image);
    }

    public ImageLabel(final String filePath) {
        this(ImageLoader.loadIcon(filePath));
    }
}
