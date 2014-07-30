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
        StringBuilder result = new StringBuilder();
        if (Iterables.isEmpty(iterable)) {
            return Text.empty();
        }
        for (Object object : iterable) {
            result.append(object.toString());
            result.append(separator);
        }
        int end = result.length();
        result.delete(end - separator.length(), end);
        return result.toString();
    }

    public static Iterable<String> toIterable(final String source,
            final String separator) {
        return Arrays.asList(Text.emptyIfNull(source).split(separator));
    }

    public static String trim(final String source) {
        return Text.emptyIfNull(source).trim();
    }

    public static Iterable<String> trimAll(final Iterable<String> source) {
        List<String> result = new LinkedList<>();
        for (String string : source) {
            result.add(Text.trim(string));
        }
        return result;
    }
}
