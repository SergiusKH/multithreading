package lesson5;

/**
 * Created by Sergius on 07.06.2015.
 */
public class PC {
    public static void main(String[] args) {
        SingleElementBuffer2 buffer = new SingleElementBuffer2();
        new Thread(new Producer(buffer, 4000, 1)).start();
        new Thread(new Consumer(buffer, 1)).start();
    }
}
