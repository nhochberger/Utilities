package hochberger.utilities.text.i18n;

public class DirectI18N implements I18N {

    private final String resolvedText;

    public DirectI18N(final String text) {
        this(text, new String[0]);
    }

    /**
     *
     * @param text
     *            - the text containing placeholders ${i} to be replaced with
     *            the i_th element of arguments. starting with i = 0
     * @param arguments
     *            - arguments to be placed in the text
     */
    public DirectI18N(final String text, final String... arguments) {
        super();
        String workingText = text;
        for (int i = 0; i < arguments.length; i++) {
            workingText = workingText.replace("${" + i + "}", arguments[i]);
        }
        this.resolvedText = workingText;
    }

    @Override
    public String toString() {
        return this.resolvedText;
    }
}
