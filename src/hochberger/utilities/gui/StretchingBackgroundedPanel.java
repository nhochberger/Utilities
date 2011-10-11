package hochberger.utilities.gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

public class StretchingBackgroundedPanel extends BackgroundedPanel {

	private static final long serialVersionUID = -6328350841770902562L;

	public StretchingBackgroundedPanel(Image image, boolean isDoubleBuffered) {
		this(image, new FlowLayout(), isDoubleBuffered);
	}

	public StretchingBackgroundedPanel(Image image, LayoutManager layout, boolean isDoubleBuffered) {
		super(image, layout, isDoubleBuffered);
	}

	public StretchingBackgroundedPanel(Image image, LayoutManager layout) {
		this(image, layout, true);
	}

	public StretchingBackgroundedPanel(Image image) {
		this(image, true);
	}

	@Override
	protected PanelUI createUI(final Image image) {
		return new PanelUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D graphics = (Graphics2D) g.create();
				graphics.drawImage(image, 0, 0, c.getWidth(), c.getHeight(), null);
				graphics.dispose();
				super.paint(g, c);
			}
		};
	}
}
