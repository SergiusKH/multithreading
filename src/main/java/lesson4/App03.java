package lesson4;

/**
 * Created by Sergius on 05.06.2015.
 */
public class App03 {
    public static void main(String[] args) {
        final Object monitor0 = new Object();
        final Object monitor1 = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("0");
                synchronized (monitor0) {
                    while (true) {
                        System.out.println("A");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                synchronized (monitor1) {
                    while (true) {
                        System.out.println("B");
                    }
                }
            }
        }).start();
    }
}
