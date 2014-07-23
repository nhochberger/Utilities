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
package hochberger.utilities.properties;

import hochberger.utilities.files.Closer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

    private LoadProperties() {
        super();
    }

    public static Properties from(final String filePath) throws IOException {
        final Properties result = new Properties();
        result.load(ClassLoader.getSystemResourceAsStream(filePath));
        return result;
    }

    public static Properties fromExtern(final String filePath)
            throws IOException {
        final Properties result = new Properties();
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(filePath);
            result.load(inStream);
            return result;
        } finally {
            Closer.close(inStream);
        }
    }
}
