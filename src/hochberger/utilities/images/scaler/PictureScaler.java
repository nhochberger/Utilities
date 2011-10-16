package hochberger.utilities.images.scaler;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Chet
 */
public class PictureScaler {

	/** Creates a new instance of PictureScaler */
	public PictureScaler() {
		super();
	}

	/**
	 * Convenience method that returns a scaled instance of the provided
	 * BufferedImage. <br />
	 * <br />
	 * NOTE: Original implementation taken from Filthy Rich Clients
	 * http://filthyrichclients.org/
	 * 
	 * 
	 * @param img
	 *            the original image to be scaled
	 * @param targetWidth
	 *            the desired width of the scaled instance, in pixels
	 * @param targetHeight
	 *            the desired height of the scaled instance, in pixels
	 * @param hint
	 *            one of the rendering hints that corresponds to
	 *            RenderingHints.KEY_INTERPOLATION (e.g.
	 *            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR,
	 *            RenderingHints.VALUE_INTERPOLATION_BILINEAR,
	 *            RenderingHints.VALUE_INTERPOLATION_BICUBIC)
	 * @param progressiveBilinear
	 *            if true, this method will use a multi-step scaling technique
	 *            that provides higher quality than the usual one-step technique
	 *            (only useful in down-scaling cases, where targetWidth or
	 *            targetHeight is smaller than the original dimensions)
	 * @return a scaled version of the original BufferedImage
	 */
	public BufferedImage getFasterScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean progressiveBilinear) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = img;
		BufferedImage scratchImage = null;
		Graphics2D g2 = null;
		int w, h;
		int prevW = ret.getWidth();
		int prevH = ret.getHeight();
		boolean isTranslucent = img.getTransparency() != Transparency.OPAQUE;

		if (progressiveBilinear) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		do {
			if (progressiveBilinear && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (progressiveBilinear && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			if (scratchImage == null || isTranslucent) {
				// Use a single scratch buffer for all iterations
				// and then copy to the final, correctly-sized image
				// before returning
				scratchImage = new BufferedImage(w, h, type);
				g2 = scratchImage.createGraphics();
			}
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);
			prevW = w;
			prevH = h;

			ret = scratchImage;
		} while (w != targetWidth || h != targetHeight);

		if (g2 != null) {
			g2.dispose();
		}

		// If we used a scratch buffer that is larger than our target size,
		// create an image of the right size and copy the results into it
		if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
			scratchImage = new BufferedImage(targetWidth, targetHeight, type);
			g2 = scratchImage.createGraphics();
			g2.drawImage(ret, 0, 0, null);
			g2.dispose();
			ret = scratchImage;
		}

		return ret;
	}

	public BufferedImage getFasterScaledInstance(Image img, int targetWidth, int targetHeight, Object hint, boolean progressiveBilinear) {
		BufferedImage image = null;
		if (img instanceof BufferedImage) {
			image = (BufferedImage) img;
		}
		image = createBufferedImageFrom(img);
		return getFasterScaledInstance(image, targetWidth, targetHeight, hint, progressiveBilinear);
	}

	private BufferedImage createBufferedImageFrom(Image img) {
		BufferedImage image = createCompatibleImageFrom(img);
		Graphics graphics = image.getGraphics().create();
		graphics.drawImage(img, 0, 0, null);
		graphics.dispose();
		return image;
	}

	private BufferedImage createCompatibleImageFrom(Image img) {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(img.getWidth(null), img.getHeight(null));
	}

	public BufferedImage scale(Image img, int targetWidth, int targetHeight) {
		return getFasterScaledInstance(img, targetWidth, targetHeight, RenderingHints.VALUE_INTERPOLATION_BICUBIC, true);
	}

	public BufferedImage scale(BufferedImage img, int targetWidth, int targetHeight) {
		return getFasterScaledInstance(img, targetWidth, targetHeight, RenderingHints.VALUE_INTERPOLATION_BICUBIC, true);
	}

	public BufferedImage scale(Image img, Dimension targetDimension) {
		return scale(img, targetDimension.width, targetDimension.height);
	}

	public BufferedImage scale(BufferedImage img, Dimension targetDimension) {
		return scale(img, targetDimension.width, targetDimension.height);
	}
}
