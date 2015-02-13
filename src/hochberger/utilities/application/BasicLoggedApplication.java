package hochberger.utilities.application;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public abstract class BasicLoggedApplication implements Lifecycle {

    private static Logger logger;

    protected static void setUpLoggingServices(final Class<?> applicationClass) {
        try {
            logger = Logger.getLogger(applicationClass);
            final Layout layout = new PatternLayout("%d %p (%t): %m%n");
            logger.addAppender(new ConsoleAppender(layout, "System.out"));
            logger.addAppender(new FileAppender(layout, "logs/main.log"));
        } catch (final Exception e) {
            System.err
                    .println("Error while setting up logging service. Application will be shut down.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Logger getLogger() {
        if (null == logger) {
            throw new LoggerNotInitializedException();
        }
        return logger;
    }

    @Override
    public void start() {
        logger().info("Starting application");
    }

    @Override
    public void stop() {
        logger().info("Stop application");
    }

    protected Logger logger() {
        return getLogger();
    }

    public static class LoggerNotInitializedException extends RuntimeException {

        private static final long serialVersionUID = 5684561354843006032L;

        public LoggerNotInitializedException() {
            super("Logging services were not yet initialized.");
        }
    }
}
