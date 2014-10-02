package hochberger.utilities.text.i18n;

public class I18N {

    private final DirectI18N directI18N;

    public I18N(final String key) {
        this(key, new String[0]);
    }

    public I18N(final String key, final String... arguments) {
        super();
        this.directI18N = new DirectI18N(I18NFramework.getTextForKey(key),
                arguments);
    }

    @Override
    public String toString() {
        return this.directI18N.toString();
    }
}
