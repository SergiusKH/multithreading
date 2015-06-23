package lesson3;

/**
 * Created by Sergius on 05.06.2015.
 */
public class A {

    static int data = 0;
    static volatile boolean runA = true;
    static volatile boolean runB = true;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Start " + Thread.currentThread().getName());
                while (true) {
                    boolean b = runB;
                    System.out.println(data);
                }
            }
        });
        thread.start();
        data = 1;
        runA = false;
    }
}
