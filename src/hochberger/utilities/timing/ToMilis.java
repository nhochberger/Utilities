package hochberger.utilities.timing;

public class ToMilis {

    private ToMilis() {
        super();
    }

    public static long seconds(final long seconds) {
        return 1000 * seconds;
    }

    public static long seconds(final double seconds) {
        return (long) (1000 * seconds);
    }

    public static long minutes(final long minutes) {
        return minutes * seconds(60);
    }

    public static long minutes(final double minutes) {
        return (long) (minutes * seconds(60));
    }

    public static long hours(final long hours) {
        return hours * minutes(60);
    }

    public static long hours(final double hours) {
        return (long) (hours * minutes(60));
    }

    public static long days(final long days) {
        return days * hours(24);
    }

    public static long days(final double days) {
        return (long) (days * hours(24));
    }
}
