package hochberger.utilities.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import hochberger.utilities.application.ApplicationShutdownEvent;
import hochberger.utilities.application.session.BasicSession;

public class WindowClosedApplicationShutdownEventPublisher
        extends WindowAdapter {

    private final BasicSession session;

    public WindowClosedApplicationShutdownEventPublisher(
            final BasicSession session) {
        super();
        this.session = session;
    }

    @Override
    public void windowClosed(final WindowEvent e) {
        super.windowClosed(e);
        this.session.getEventBus()
                .publishFromEDT(new ApplicationShutdownEvent());
    }
}
