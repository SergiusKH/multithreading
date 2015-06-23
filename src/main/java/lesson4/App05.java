package lesson4;

/**
 * Created by Sergius on 05.06.2015.
 */
public class App05 {
    static volatile boolean in = false;

    public static void main(String[] args) {
        final Object monitor0 = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor0) {
                    in = true;
//                    try {
//                        Thread.sleep(Long.MAX_VALUE);
//                    } catch (InterruptedException e) {}
                    //while(true) {Thread.yield();}
                    try {
                        monitor0.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

        System.out.println("A");
        while (!in);
        System.out.println("B");
        synchronized (monitor0) {
            System.out.println("C");
        }
    }
}
