/**
 * (C) Copyright Softwareschneiderei GmbH, Karlsruhe, Germany
 *
 * @author dali
 * @since 05.04.2010
 */
package com.schneide.framework.gui.swing;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

/**
 * Helper class to access the Swing UI Thread (EDT) more easily. It also helps
 * with asserting proper EDT usage.
 * 
 * @author dsl
 */
public final class EDT {

    private EDT() {
        super();
    }

    /**
     * Checks if this call is <b>always</b> made from the EDT. Same as only().
     */
    public static void always() {
        checkIsOnEDT();
    }

    /**
     * Indicates that this call can be made from the EDT or any other thread.
     * This assertion never fails, it's meant for documentation purposes.
     */
    public static void whatever() {
        // does nothing on purpose, only for documentation reasons
    }

    /**
     * Checks if this call is <b>only</b> made from the EDT. Same as always().
     */
    public static void only() {
        checkIsOnEDT();
    }

    /**
     * Checks if this call is <b>never</b> made from the EDT.
     */
    public static void never() {
        if (EDT.isThis()) {
            EDT.reportEDTUsageFailure(" was called on EDT, though it shouldn't!"); //$NON-NLS-1$
        }
    }

    protected static void checkIsOnEDT() {
        if (!EDT.isThis()) {
            EDT.reportEDTUsageFailure(" was not called on EDT, though it should have been!"); //$NON-NLS-1$
        }
    }

    protected static void reportEDTUsageFailure(final String message) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        System.err.println(ste[4].getClassName() + "->" + ste[4].getMethodName() + message); //$NON-NLS-1$
    }

    public static boolean isThis() {
        return EventQueue.isDispatchThread();
    }

    public static void perform(final Runnable actionOnEDT) {
        if (!EDT.isThis()) {
            EventQueue.invokeLater(actionOnEDT);
            return;
        }
        actionOnEDT.run();
    }

    public static void performBlocking(final Runnable actionOnEDT) {
        if (!EDT.isThis()) {
            try {
                EventQueue.invokeAndWait(actionOnEDT);
            } catch (InterruptedException e) {
                System.err.println("Could not wait on EDT action, interrupted."); //$NON-NLS-1$
                Thread.currentThread().interrupt();
            } catch (InvocationTargetException e) {
                System.err.println("Exception with performing blocking EDT action."); //$NON-NLS-1$
                e.printStackTrace();
            }
            return;
        }
        actionOnEDT.run();
    }

    // public static <T> T performQuery(final EDTQuery<T> query) {
    // performBlocking(query);
    // return query.getResult();
    // }
}
