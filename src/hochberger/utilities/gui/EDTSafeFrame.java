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

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public abstract class EDTSafeFrame {

	private JFrame frame;
	private final String title;

	public EDTSafeFrame(String title) {
		super();
		this.title = title;
	}

	/**
	 * Returns the current JFrame.<br />
	 * May be <code>null</code> if used outside <code>buildUI()</code>.
	 */
	protected JFrame frame() {
		return this.frame;
	}

	/**
	 * Convenience method delegating to <code>frame().add(component)</code>
	 */
	protected void add(final JComponent component) {
		frame().getContentPane().add(component);
	}

	/**
	 * Convenience method delegating to
	 * <code>frame().add(component, constraint)</code>
	 */
	protected void add(final JComponent component, Object constraint) {
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
	protected void setSize(int width, int height) {
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
		setTitle(this.title);
		buildUI();
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
	 * Sets the visibility of the frame to <code>true</code> and, if not already
	 * done, builds the UI.<br />
	 * Note that this method will take care that building the UI and displaying
	 * it is performed on the EDT.
	 */
	public final void show() {
		performBlockingOnEDT(new Runnable() {

			@Override
			public void run() {
				if (null == frame()) {
					buildUIInternal();
				}
				frame().setVisible(true);
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
				if (null != frame()) {
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
				if (null != frame()) {
					frame().dispose();
				}
			}
		});
		this.frame = null;
	}

	protected void performBlockingOnEDT(final Runnable runnable) {
		try {
			EventQueue.invokeAndWait(runnable);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
