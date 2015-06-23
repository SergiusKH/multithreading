package lesson5;

/**
 * Created by Sergius on 07.06.2015.
 */
public class Producer implements Runnable {

    private SingleElementBuffer2 buffer;
    private int timeout;
    private int id;
    private int data = 0;

    public Producer(SingleElementBuffer2 buffer, int timeout, int id) {
        this.buffer = buffer;
        this.timeout = timeout;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("P#" + id + " data: " + ++data);
            try {
                Thread.sleep(timeout);
                buffer.put(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
