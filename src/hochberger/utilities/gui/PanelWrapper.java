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

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class PanelWrapper {

    private PanelWrapper() {
        super();
    }

    public static JPanel wrap(final JComponent... components) {
        JPanel panel = new JPanel(new FlowLayout());
        for (JComponent component : components) {
            panel.add(component);
        }
        return panel;
    }

    public static JPanel wrapWithBackground(final Color color, final JComponent... components) {
        JPanel result = wrap(components);
        result.setBackground(color);
        return result;
    }
}
