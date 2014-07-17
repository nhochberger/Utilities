package hochberger.utilities.timing;

import org.apache.log4j.Logger;

public class Timing {

    private long startNanos;
    private long stopNanos;

    public Timing() {
        super();
    }

    public void start() {
        this.startNanos = System.nanoTime();
    }

    public void stop() {
        this.stopNanos = System.nanoTime();
    }

    public long getNanos() {
        return this.stopNanos - this.startNanos;
    }

    public void reportOn(final Logger logger) {
        logger.info("Time measured: " + String.valueOf(getNanos())
                + " nanoseconds.");
    }
}
