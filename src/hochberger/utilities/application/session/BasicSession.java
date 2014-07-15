package hochberger.utilities.application.session;

import hochberger.utilities.application.ApplicationProperties;
import hochberger.utilities.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class BasicSession {

	private final EventBus eventuBus;
	private final ApplicationProperties properties;
	private final Logger logger;
	private final Map<String, Object> sessionVariables;

	public BasicSession(final ApplicationProperties properties, final EventBus eventBus,
			final Logger logger) {
		this.properties = properties;
		eventuBus = eventBus;
		this.logger = logger;
		sessionVariables = new HashMap<String, Object>();
	}

	public void setSessionVariable(final String key, final Object value) {
		sessionVariables.put(key, value);
		logger.info("Setting session variable \"" + key + "\" to \"" + value
				+ "\"");
	}

	public Object getSessionVariable(final String key) {
		return sessionVariables.get(key);
	}

	public ApplicationProperties getProperties() {
		return properties;
	}

	public EventBus getEventBus() {
		return eventuBus;
	}

	public Logger getLogger() {
		return logger;
	}
}
