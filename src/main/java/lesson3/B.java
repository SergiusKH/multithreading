package lesson3;

/**
 * Created by Sergius on 05.06.2015.
 */
public class B {

    static int data = 0;
    static volatile boolean runB = true;

    public static void main(String[] args) {
        data = 1;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Start " + Thread.currentThread().getName());
                data = 2;
                System.out.println("thread data " + data);

            }
        });

        thread.start();

        while (thread.isAlive());
        System.out.println("main data "+data);


    }
}
