package lesson5;

/**
 * Created by Sergius on 07.06.2015.
 */
public class SingleElementBuffer {
    private final Object lock = new Object();
    private Integer elem = null;

    public void put(Integer elem) throws InterruptedException {
        synchronized (lock) {
            while (this.elem != null) {
                lock.wait();
            }
            this.elem = elem;
            lock.notifyAll();
        }
    }

    public Integer get() throws InterruptedException {
        synchronized (lock) {
            while (elem == null) {
                lock.wait();
            }
            Integer res = this.elem;
            this.elem = null;
            lock.notifyAll();
            return res;
        }
    }
}
