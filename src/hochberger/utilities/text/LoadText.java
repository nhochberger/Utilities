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
package hochberger.utilities.text;

import hochberger.utilities.files.Closer;

import java.io.InputStream;
import java.util.Scanner;

public class LoadText {

    private LoadText() {
        super();
    }

    public static String from(final String filePath) {
        StringBuilder result = new StringBuilder();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filePath);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            result.append(scanner.nextLine());
            result.append(System.getProperty("line.separator"));
        }
        Closer.close(scanner);
        return result.toString();
    }
}
