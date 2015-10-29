package hochberger.utilities.timing;

public class Sleeper {

    private Sleeper() {
        super();
    }

    public static void sleep(final long milis) {
        try {
            Thread.sleep(milis);
        } catch (final InterruptedException e) {
            // do nothing on purpose
        }
    }

}
