package lesson14;

/**
 * Created by Sergius on 30.06.2015.
 */
public class LatchMonitor {

    private boolean open = false;

    public synchronized void doOpen() {
        open = true;
        this.notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        while (!open) {
            this.wait();
        }
    }
}
