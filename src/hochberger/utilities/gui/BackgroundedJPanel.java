package hochberger.utilities.gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

public class BackgroundedJPanel extends JPanel {

	private static final long serialVersionUID = -8291249523290165044L;

	public BackgroundedJPanel(Image image) {
		this(image, true);
	}

	public BackgroundedJPanel(Image image, boolean isDoubleBuffered) {
		this(image, new FlowLayout(), isDoubleBuffered);
	}

	public BackgroundedJPanel(final Image image, LayoutManager layout,
			boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		setUI(new PanelUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D graphics = (Graphics2D) g.create();
				graphics.drawImage(image, 0, 0, null);
				super.paint(g, c);
			}
		});
	}

	public BackgroundedJPanel(Image image, LayoutManager layout) {
		this(image, layout, true);
	}

}
