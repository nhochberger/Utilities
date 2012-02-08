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
package hochberger.utilities.images.loader;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageLoader {

	private ImageLoader() {
		super();
	}

	/*
	 * By making the indirection via loadIcon(..), the properties, e.g. width
	 * and height, have proper values when the image is returned.
	 */
	public static Image loadImage(final String filePath) {
		return ((ImageIcon) loadIcon(filePath)).getImage();
	}

	public static Icon loadIcon(final String filePath) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource(filePath)));
	}
}
