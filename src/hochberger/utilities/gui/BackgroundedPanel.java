/*******************************************************************************
 * Copyright (c) 2011 Nico Hochberger.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Nico Hochberger - initial API and implementation
 ******************************************************************************/
package hochberger.utilities.gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.plaf.PanelUI;

public abstract class BackgroundedPanel extends JPanel {

	private static final long serialVersionUID = -2770665145529513039L;

	public BackgroundedPanel(final Image image) {
		this(image, true);
	}

	public BackgroundedPanel(final Image image, final boolean isDoubleBuffered) {
		this(image, new FlowLayout(), isDoubleBuffered);
	}

	public BackgroundedPanel(final Image image, final LayoutManager layout, final boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		setUI(createUI(image));
	}

	public BackgroundedPanel(final Image image, final LayoutManager layout) {
		this(image, layout, true);
	}

	protected abstract PanelUI createUI(Image image);
}
