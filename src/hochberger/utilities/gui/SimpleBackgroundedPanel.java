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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

public class SimpleBackgroundedPanel extends BackgroundedPanel {

    private static final long serialVersionUID = -8291249523290165044L;

    public SimpleBackgroundedPanel(final Image image,
            final boolean isDoubleBuffered) {
        this(image, new FlowLayout(), isDoubleBuffered);
    }

    public SimpleBackgroundedPanel(final Image image,
            final LayoutManager layout, final boolean isDoubleBuffered) {
        super(image, layout, isDoubleBuffered);
    }

    public SimpleBackgroundedPanel(final Image image, final LayoutManager layout) {
        this(image, layout, true);
    }

    public SimpleBackgroundedPanel(final Image image) {
        this(image, true);
    }

    @Override
    protected PanelUI createUI(final Image image) {
        return new PanelUI() {
            @Override
            public void paint(final Graphics g, final JComponent c) {
                final Graphics2D graphics = (Graphics2D) g.create();
                graphics.drawImage(image, 0, 0, null);
                graphics.dispose();
                super.paint(g, c);
            }
        };
    }
}
