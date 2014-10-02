package hochberger.utilities.text.i18n;

import hochberger.utilities.properties.LoadProperties;

import java.io.IOException;
import java.util.Properties;

public enum I18NFramework {
    INSTANCE;

    private Properties texts;

    public static void setUpI18N(final Properties serializedTexts) {
        INSTANCE.setUpI18NInstance(serializedTexts);
    }

    public static void setUpI18N(final String filePath) throws MissingI18NTexts {
        try {
            setUpI18N(LoadProperties.from(filePath));
        } catch (IOException e) {
            throw new MissingI18NTexts(filePath);
        }
    }

    private void setUpI18NInstance(final Properties serializedTexts) {
        this.texts = serializedTexts;
    }

    public static String getTextForKey(final String key) {
        return INSTANCE.getTextForKeyInstance(key);
    }

    private String getTextForKeyInstance(final String key) {
        return this.texts.getProperty(key, "<missing: " + key + ">");
    }

    private I18NFramework() {
        this.texts = new Properties();
    }

    public static class MissingI18NTexts extends Exception {

        private static final long serialVersionUID = 5599133868747238565L;

        public MissingI18NTexts(final String missingFile) {
            super("Unable to load I18N texts from files: " + missingFile + ".");
        }
    }
}
