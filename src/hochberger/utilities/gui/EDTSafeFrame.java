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

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.LayoutManager;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public abstract class EDTSafeFrame {

    private JFrame frame;
    private final String title;
    private SupposedToBeMaximized maximizedExpectation;

    private enum SupposedToBeMaximized {
        YES {
            @Override
            public void applyExpectationTo(final JFrame frame) {
                frame.setExtendedState(frame.getExtendedState()
                        | Frame.MAXIMIZED_BOTH);
            }
        },
        NO {
            @Override
            public void applyExpectationTo(final JFrame frame) {
                // do nothing here
            }
        };

        public abstract void applyExpectationTo(JFrame frame);
    }

    public EDTSafeFrame(final String title) {
        super();
        this.title = title;
        this.maximizedExpectation = SupposedToBeMaximized.NO;
    }

    /**
     * Returns the current JFrame.<br />
     * May be <code>null</code> if used outside <code>buildUI()</code>.
     */
    protected JFrame frame() {
        return this.frame;
    }

    protected Container getContentPane() {
        return frame().getContentPane();
    }

    protected void setContentPane(final Container contentPane) {
        frame().setContentPane(contentPane);
    }

    /**
     * Convenience method delegating to
     * <code>frame().getContentPane().add(component)</code>
     */
    protected void add(final JComponent component) {
        frame().getContentPane().add(component);
    }

    /**
     * Convenience method delegating to
     * <code>frame().getContentPane().add(component, constraint)</code>
     */
    protected void add(final JComponent component, final Object constraint) {
        frame().getContentPane().add(component, constraint);
    }

    /**
     * Convenience method delegating to <code>frame().setTitle(title)</code>
     */
    protected void setTitle(final String title) {
        frame().setTitle(title);
    }

    /**
     * Convenience method delegating to
     * <code>frame().setLayout(layoutManager)</code>
     */
    protected void useLayoutManager(final LayoutManager layoutManager) {
        frame().setLayout(layoutManager);
    }

    /**
     * Convenience method replacing <code>frame().setSize(dimension)</code>
     */
    protected void setSize(final int width, final int height) {
        this.frame.setSize(new Dimension(width, height));
    }

    protected void exitOnClose() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void notResizable() {
        this.frame.setResizable(false);
    }

    private void buildUIInternal() {
        this.frame = new JFrame();
        preBuildHooks();
        setTitle(this.title);
        buildUI();
        postBuildHooks();
    }

    /**
     * Override this method in subclasses. Will be executed upon
     * <code>show()</code> is called the first time. Do not manipulate the GUI
     * outside of this method.<br />
     * You will be provided with an instance of <code>JFrame</code> which can be
     * accessed via {@link frame()}.
     */
    protected abstract void buildUI();

    /**
     * this method is executed directly after the frame is created but directly
     * before buildUI() is called
     */
    protected void preBuildHooks() {
        // intended for subclassing
    }

    /**
     * this method is executed directly after buildUI() has finished
     */
    protected void postBuildHooks() {
        // intended for subclassing
    }

    /**
     * Sets the visibility of the frame to <code>true</code> and, if not already
     * done, builds the UI.<br />
     * Note that this method will take care that building the UI and displaying
     * it is performed on the EDT.
     */
    public final void show() {
        performBlockingOnEDT(new Runnable() {

            @Override
            public void run() {
                if (!isBuilt()) {
                    buildUIInternal();
                }
                frame().setVisible(true);
                EDTSafeFrame.this.maximizedExpectation
                        .applyExpectationTo(frame());
            }
        });
    }

    /**
     * EDT-safe delegate to <code>frame().setVisible(false)</code>.
     */
    public final void hide() {
        performBlockingOnEDT(new Runnable() {

            @Override
            public void run() {
                if (isBuilt()) {
                    frame().setVisible(false);
                }
            }
        });
    }

    /**
     * EDT-safe delegate to <code>frame().dispose()</code>.
     */
    public final void dispose() {
        performBlockingOnEDT(new Runnable() {

            @Override
            public void run() {
                if (isBuilt()) {
                    frame().dispose();
                }
            }
        });
        this.frame = null;
    }

    protected void performBlockingOnEDT(final Runnable runnable) {
        try {
            EventQueue.invokeAndWait(runnable);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        } catch (final InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected boolean isBuilt() {
        return null != frame();
    }

    protected void center() {
        frame().setLocationRelativeTo(null);
    }

    /**
     * can be called at any point, prior to and after showing the frame
     */
    public void maximize() {
        if (!(isBuilt() && frame().isVisible())) {
            this.maximizedExpectation = SupposedToBeMaximized.YES;
        }
        SupposedToBeMaximized.NO.applyExpectationTo(frame());
        return;
    }

    protected void setIcon(final Image image) {
        frame().setIconImage(image);
    }
}
