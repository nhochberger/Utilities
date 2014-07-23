package hochberger.utilities.text;

public final class Text {

    private Text() {
        super();
    }

    public static String empty() {
        return "";
    }

    public static String space() {
        return " ";
    }

    public static String newLine() {
        return "\n";
    }

    public static String fromIterable(final Iterable<?> iterable) {
        return fromIterable(iterable, Text.empty());
    }

    public static String fromIterable(final Iterable<?> iterable,
            final String separator) {
        StringBuilder result = new StringBuilder();
        for (Object object : iterable) {
            result.append(object.toString());
            result.append(separator);
        }
        int end = result.length();
        result.delete(end - separator.length(), end);
        return result.toString();
    }
}
