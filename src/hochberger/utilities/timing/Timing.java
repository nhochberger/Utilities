package hochberger.utilities.timing;

import org.apache.log4j.Logger;

public class Timing {

    private long startNanos;
    private long stopNanos;
    private boolean running = false;

    public Timing() {
        super();
    }

    public void start() {
        this.running = true;
        this.startNanos = System.nanoTime();
    }

    public void stop() {
        this.stopNanos = System.nanoTime();
        this.running = false;
    }

    public long getNanos() {
        if (this.running) {
            return this.stopNanos - this.startNanos;
        }
        return System.nanoTime() - this.startNanos;
    }

    public void reportOn(final Logger logger) {
        logger.info("Time measured: " + String.valueOf(getNanos())
                + " nanoseconds.");
    }
}
