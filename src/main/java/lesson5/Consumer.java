package lesson5;

/**
 * Created by Sergius on 07.06.2015.
 */
public class Consumer implements Runnable {

    private SingleElementBuffer2 buffer;
    private int id;
    private int data = 0;

    public Consumer(SingleElementBuffer2 buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("C#" + id + " data: " + buffer.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}