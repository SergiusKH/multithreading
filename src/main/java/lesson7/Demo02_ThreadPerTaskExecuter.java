package lesson7;

import java.util.concurrent.Executor;

/**
 * Created by Sergius on 12.06.2015.
 */
public class Demo02_ThreadPerTaskExecuter {

    public static void main(String[] args) {
        Executor executor = getExecuter();
        executor.execute(getTask());
        executor.execute(getTask());

        System.out.println("Hello from " + Thread.currentThread());
    }

    private static Runnable getTask() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from " + Thread.currentThread());
            }
        };
    }

    private static Executor getExecuter() {
        return new Executor() {
            @Override
            public void execute(Runnable command) {
                new Thread(command).start();
            }
        };
    }
}
