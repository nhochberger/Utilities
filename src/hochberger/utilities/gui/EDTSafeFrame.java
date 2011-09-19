package hochberger.utilities.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public abstract class EDTSafeFrame {

    private JFrame frame;

    public EDTSafeFrame() {
        super();
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
     * Convenience method delegating to <code>frame().setTitle(title)</code>
     */
    protected void setTitle(final String title) {
        frame().setTitle(title);
    }

    /**
     * Convenience method delegating to <code>frame().setLayout(layoutManager)</code>
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

    private void buildUIInternal() {
        this.frame = new JFrame();
        buildUI();
    }

    /**
     * Override this method in subclasses. Will be executed upon <code>show()</code>
     * is called the first time. Do not manipulate the GUI outside of this method.<br />
     * You will be provided with an instance of <code>JFrame</code> which can be accessed 
     * via {@link frame()}.
     */
    protected abstract void buildUI();

    /**
     * Sets the visibility of the frame to <code>true</code> and, if not already done,
     * builds the UI.<br />
     * Note that this method will take care that building the UI and displaying it
     * is performed on the EDT. 
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

    private void performBlockingOnEDT(final Runnable runnable) {
        try {
            EventQueue.invokeAndWait(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
