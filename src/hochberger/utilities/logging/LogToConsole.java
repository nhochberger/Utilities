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
package hochberger.utilities.logging;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LogToConsole {

    private static final Logger LOGGER = Logger.getLogger("logger");

    static {
        BasicConfigurator.configure();
    }

    private LogToConsole() {
        super();
    }

    public static void debug(final Object message) {
        LOGGER.debug(message);
    }

    public static void debug(final String message, final Throwable throwable) {
        LOGGER.debug(message, throwable);
    }

    public static void info(final Object message) {
        LOGGER.info(message);
    }

    public static void info(final String message, final Throwable throwable) {
        LOGGER.info(message, throwable);
    }

    public static void warn(final Object message) {
        LOGGER.warn(message);
    }

    public static void warn(final String message, final Throwable throwable) {
        LOGGER.warn(message, throwable);
    }

    public static void error(final Object message) {
        LOGGER.error(message);
    }

    public static void error(final String message, final Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    public static void fatal(final Object message) {
        LOGGER.fatal(message);
    }

    public static void fatal(final String message, final Throwable throwable) {
        LOGGER.fatal(message, throwable);
    }
}
