package hochberger.utilities.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class PanelWrapper {

    private PanelWrapper() {
        super();
    }

    public static JPanel wrap(final JComponent... components) {
        JPanel panel = new JPanel(new FlowLayout());
        for (JComponent component : components) {
            panel.add(component);
        }
        return panel;
    }

    public static JPanel wrapWithBackground(final Color color, final JComponent... components) {
        JPanel result = wrap(components);
        result.setBackground(color);
        return result;
    }
}
