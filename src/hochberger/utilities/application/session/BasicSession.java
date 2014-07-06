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
	
	public BasicSession(ApplicationProperties properties, EventBus eventBus, Logger logger) {
		this.properties = properties;
		eventuBus = eventBus;
		this.logger = logger;
		sessionVariables = new HashMap<String, Object>();
	}
	
	public void setSessionVariable(String key, Object value) {
		sessionVariables.put(key, value);
	}
	
	public Object getSessionVariable(String key) {
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
