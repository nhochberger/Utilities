package hochberger.utilities.gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

public abstract class BackgroundedPanel extends JPanel {

	private static final long serialVersionUID = -2770665145529513039L;

	public BackgroundedPanel(Image image) {
		this(image, true);
	}

	public BackgroundedPanel(Image image, boolean isDoubleBuffered) {
		this(image, new FlowLayout(), isDoubleBuffered);
	}

	public BackgroundedPanel(Image image, LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		setUI(createUI(image));
	}

	public BackgroundedPanel(Image image, LayoutManager layout) {
		this(image, layout, true);
	}

	protected abstract PanelUI createUI(Image image);
}
