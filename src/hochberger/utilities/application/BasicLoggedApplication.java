package hochberger.utilities.application;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public abstract class BasicLoggedApplication {

	private static Logger logger;

	protected static void setUpLoggingServices(Class<?> applicationClass) {
		try {
			logger = Logger.getLogger(applicationClass);
			Layout layout = new SimpleLayout();
			logger.addAppender(new ConsoleAppender(layout));
			logger.addAppender(new FileAppender(layout, "logs/main.log"));
		} catch (Exception e) {
			System.err.println("Error while setting up logging service. Application will be shut down.");
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

	public static class LoggerNotInitializedException extends RuntimeException {

		private static final long serialVersionUID = 5684561354843006032L;

		public LoggerNotInitializedException() {
			super("Logging services were not yet initialized.");
		}

	}
}
