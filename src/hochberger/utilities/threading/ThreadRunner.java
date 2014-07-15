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
package hochberger.utilities.threading;

public class ThreadRunner {

    private ThreadRunner() {
        super();
    }

    public static void startThread(final Runnable runnable) {
        new Thread(runnable).start();
    }
    
    public static void startThread(final Runnable runnable, final String threadName) {
        new Thread(runnable, threadName).start();
    }
}
