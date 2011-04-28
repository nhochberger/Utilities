package hochberger.utilities.threading;

public class ThreadRunner {

    private ThreadRunner() {
        super();
    }

    public static void startThread(final Runnable runnable) {
        new Thread(runnable).start();
    }
}
