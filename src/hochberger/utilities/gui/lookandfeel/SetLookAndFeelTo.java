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
package hochberger.utilities.gui.lookandfeel;

import hochberger.utilities.logging.LogToConsole;

import javax.swing.UIManager;

public class SetLookAndFeelTo {

    private SetLookAndFeelTo() {
        super();
    }

    public static void systemLookAndFeel() {
        setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    public static void crossPlatformLookAndFeel() {
        setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }

    public static void customLookAndFeel(final String lookAndFeelClassName) {
        setLookAndFeel(lookAndFeelClassName);
    }
    
    public static void nimbusLookAndFeel() {
    	setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    }

    private static void setLookAndFeel(final String lookAndFellClassName) {
        try {
            UIManager.setLookAndFeel(lookAndFellClassName);
        } catch (Exception e) {
            LogToConsole.debug("Unable to set Look and Feel to " + lookAndFellClassName, e);
        }
    }
}
