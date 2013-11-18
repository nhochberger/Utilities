package hochberger.utilities.gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class ImageButton extends JComponent {

	private static final long serialVersionUID = -5813502587935300303L;
	private final Image defaultImage;
	private final Image hoverImage;
	private final Image clickImage;
	private Image activeImage;
	private final Image disabledImage;
	private final Dimension dimension;

	public ImageButton(Image image) {
		this(image, image, image, image);
	}

	public ImageButton(Image defaultImage, Image hoverImage, Image clickImage,
			Image disabledImage) {
		super();
		this.defaultImage = defaultImage;
		this.hoverImage = hoverImage;
		this.clickImage = clickImage;
		this.activeImage = defaultImage;
		this.disabledImage = disabledImage;
		this.dimension = determineDimension(defaultImage, hoverImage,
				clickImage);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new ImageButtonMouseAdapter());
	}

	@Override
	protected void paintComponent(Graphics g) {
		setSize(getSize());
		doLayout();
		Graphics2D graphics = (Graphics2D) g.create();
		graphics.drawImage(this.activeImage, 0, 0, null);
		graphics.dispose();
		super.paintComponent(g);
	}

	@Override
	public Dimension getSize() {
		return this.dimension;
	}

	@Override
	public Dimension getPreferredSize() {
		return getSize();
	}

	private Dimension determineDimension(Image... images) {
		int width = 0;
		int height = 0;
		for (Image image : images) {
			if (height < image.getHeight(null)) {
				height = image.getHeight(null);
			}
			if (width < image.getWidth(null)) {
				width = image.getWidth(null);
			}
		}
		return new Dimension(width, height);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
	}

	private final class ImageButtonMouseAdapter extends MouseAdapter {

		private boolean stillPressed;

		public ImageButtonMouseAdapter() {
			super();
			this.stillPressed = false;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (this.stillPressed) {
				return;
			}
			ImageButton.this.activeImage = ImageButton.this.hoverImage;
			repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (this.stillPressed) {
				return;
			}
			ImageButton.this.activeImage = ImageButton.this.defaultImage;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			this.stillPressed = true;
			ImageButton.this.activeImage = ImageButton.this.clickImage;
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			this.stillPressed = false;
			ImageButton.this.activeImage = ImageButton.this.hoverImage;
			repaint();
		}
	}
}