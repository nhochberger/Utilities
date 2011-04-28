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

    private static void setLookAndFeel(final String lookAndFellClassName) {
        try {
            UIManager.setLookAndFeel(lookAndFellClassName);
        } catch (Exception e) {
            LogToConsole.debug("Unable to set Look and Feel to " + lookAndFellClassName, e);
        }
    }
}
