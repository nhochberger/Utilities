package hochberger.utilities.timing;

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

    public long getMilis() {
        return getNanos() / 1000000 + 1;
    }

    public long getSeconds() {
        return getNanos() / 1000000000 + 1;
    }
}
