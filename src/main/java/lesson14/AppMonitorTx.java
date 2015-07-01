package lesson14;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sergius on 30.06.2015.
 */
public class AppMonitorTx {

    public static void main(String[] args) {
        final LatchTx latch = new LatchTx();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    latch.doOpen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    latch.await();
                    System.out.println("Hello!");
                    return null;
                }
            });
        }
    }
}
