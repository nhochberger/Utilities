package hochberger.utilities.application;

import hochberger.utilities.properties.LoadProperties;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

	private final Properties properties;

	public ApplicationProperties() throws IOException {
		super();
		this.properties = LoadProperties.from("settings/application.properties");
	}

	public String title() {
		return emptyIfNull(this.properties.getProperty("application.title"));
	}

	public String version() {
		return emptyIfNull(this.properties.getProperty("application.version"));
	}

	public String description() {
		return emptyIfNull(this.properties.getProperty("application.description"));
	}

	public String developers() {
		return emptyIfNull(this.properties.getProperty("application.description"));
	}

	private String emptyIfNull(String text) {
		if (null == text) {
			return "";
		}
		return text;
	}
}
