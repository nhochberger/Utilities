package hochberger.utilities.text;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public final class CommonDateTimeFormatters {

    private CommonDateTimeFormatters() {
        super();
    }

    public static DateTimeFormatter hourMinuteSecond() {
        return new DateTimeFormatterBuilder().appendHourOfDay(2)
                .appendLiteral(":").appendMinuteOfHour(2).appendLiteral(":")
                .appendSecondOfMinute(2).toFormatter();
    }

    public static DateTimeFormatter hourMinute() {
        return new DateTimeFormatterBuilder().appendHourOfDay(2)
                .appendLiteral(":").appendMinuteOfHour(2).toFormatter();
    }

    public static DateTimeFormatter dayMonthYearOnlyDigits() {
        return new DateTimeFormatterBuilder().appendDayOfMonth(2)
                .appendLiteral(".").appendMonthOfYear(2).appendLiteral(".")
                .appendYear(4, 4).toFormatter();
    }
}
