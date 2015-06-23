package lesson4;

/**
 * Created by Sergius on 05.06.2015.
 */
public class App04 {
    // Атомарность
    public static void main(String[] args) {
        final Object monitor0 = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (monitor0) {
                        System.out.println("+A");
                        System.out.println("-A");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (monitor0) {
                        System.out.println("+B");
                        System.out.println("-B");
                    }
                }
            }
        }).start();
    }
}
