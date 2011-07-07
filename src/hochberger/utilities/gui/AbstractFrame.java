package hochberger.utilities.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public abstract class AbstractFrame {

    private JFrame frame;

    public AbstractFrame() {
        super();
    }

    /**
     * Returns the current JFrame.<br />
     * May be <code>null</code> if used outside <code>buildUI()</code>.
     */
    protected JFrame frame() {
        return this.frame;
    }

    protected void add(final JComponent component) {
        frame().add(component);
    }

    protected void setTitle(final String title) {
        frame().setTitle(title);
    }

    protected void useLayoutManager(final LayoutManager layoutManager) {
        frame().setLayout(layoutManager);
    }

    protected void setSize(final Dimension dimension) {
        this.frame.setSize(dimension);
    }

    private void buildUIInternal() {
        this.frame = new JFrame();
        buildUI();
    }

    protected abstract void buildUI();

    public void show() {
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
