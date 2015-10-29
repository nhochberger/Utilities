package hochberger.utilities.application;

import hochberger.utilities.application.session.BasicSession;
import hochberger.utilities.application.session.SessionBasedObject;
import hochberger.utilities.eventbus.EventReceiver;

public class ApplicationShutdownEventReceiver extends SessionBasedObject implements EventReceiver<ApplicationShutdownEvent> {

    private final BasicLoggedApplication application;

    public ApplicationShutdownEventReceiver(final BasicSession session, final BasicLoggedApplication application) {
        super(session);
        this.application = application;
    }

    @Override
    public void receive(final ApplicationShutdownEvent event) {
        logger().info("Received shutdown command. Trying to shutdown application.");
        this.application.stop();
    }
}
