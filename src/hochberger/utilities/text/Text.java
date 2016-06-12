package hochberger.utilities.text;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Iterables;

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

    public static String space(final int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += Text.space();
        }
        return result;
    }

    public static String newLine() {
        return "\n";
    }

    public static String emptyIfNull(final String text) {
        if (null == text) {
            return Text.empty();
        }
        return text;
    }

    public static String fromIterable(final Iterable<?> iterable) {
        return fromIterable(iterable, Text.empty());
    }

    public static String fromIterable(final Iterable<?> iterable,
            final String separator) {
        final StringBuilder result = new StringBuilder();
        if (Iterables.isEmpty(iterable)) {
            return Text.empty();
        }
        for (final Object object : iterable) {
            result.append(object.toString());
            result.append(separator);
        }
        final int end = result.length();
        result.delete(end - separator.length(), end);
        return result.toString();
    }

    public static Iterable<String> toIterable(final String source,
            final String separator) {
        return Arrays.asList(
                Text.emptyIfNull(source).split(Text.emptyIfNull(separator)));
    }

    public static String trim(final String source) {
        return Text.emptyIfNull(source).trim();
    }

    public static Iterable<String> trimAll(final Iterable<String> source) {
        final List<String> result = new LinkedList<>();
        for (final String string : source) {
            result.add(Text.trim(string));
        }
        return result;
    }
}
