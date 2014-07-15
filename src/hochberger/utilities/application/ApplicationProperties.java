/*******************************************************************************
 * Copyright (c) 2011 Nico Hochberger.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Nico Hochberger - initial API and implementation
 ******************************************************************************/
package hochberger.utilities.application;

import hochberger.utilities.properties.LoadProperties;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    private final Properties properties;

    public ApplicationProperties() throws PropertiesNotFoundException {
        super();
        try {
            this.properties = LoadProperties
                    .from("settings/application.properties");
        } catch (final NullPointerException e) {
            throw new PropertiesNotFoundException();
        } catch (final IOException e) {
            throw new PropertiesNotFoundException();
        }
    }

    public String title() {
        return emptyIfNull(this.properties.getProperty("application.title"));
    }

    public String version() {
        return emptyIfNull(this.properties.getProperty("application.version"));
    }

    public String description() {
        return emptyIfNull(this.properties
                .getProperty("application.description"));
    }

    public String developers() {
        return emptyIfNull(this.properties
                .getProperty("application.description"));
    }

    public String otherProperty(final String propertyName) {
        return emptyIfNull(this.properties.getProperty(propertyName));
    }

    private String emptyIfNull(final String text) {
        if (null == text) {
            return "";
        }
        return text;
    }

    public static class PropertiesNotFoundException extends IOException {

        private static final long serialVersionUID = -4794347516673490873L;

        public PropertiesNotFoundException() {
            super("Unable to load properties");
        }
    }
}
