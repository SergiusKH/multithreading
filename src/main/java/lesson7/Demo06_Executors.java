package lesson7;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Sergius on 17.06.2015.
 */
public class Demo06_Executors {
    public static void main(String[] args) {
//        Executor executor = Executors.newSingleThreadExecutor();
//        Executor executor = Executors.newFixedThreadPool(4 *      Runtime.getRuntime().availableProcessors());
        Executor executor = Executors.newCachedThreadPool();

        executor.execute(getTask());

    }

    private static Runnable getTask() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from " + Thread.currentThread());
            }
        };
    }
}
