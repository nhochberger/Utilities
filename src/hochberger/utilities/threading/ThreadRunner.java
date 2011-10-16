package hochberger.utilities.threading;

public class ThreadRunner {

    private ThreadRunner() {
        super();
    }

    public static void startThread(final Runnable runnable) {
        new Thread(runnable).start();
    }
    
    public static void startThread(final Runnable runnable, String threadName) {
        new Thread(runnable, threadName).start();
    }
}
