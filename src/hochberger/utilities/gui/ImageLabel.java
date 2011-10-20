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
