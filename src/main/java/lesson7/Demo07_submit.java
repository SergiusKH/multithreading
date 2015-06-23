package lesson7;

import java.util.concurrent.*;

/**
 * Created by Sergius on 17.06.2015.
 */
public class Demo07_submit {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future future0 = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 42;
            }
        });

        Future future1 = executorService.submit(() -> {
            while (true) {/* infinity loop*/}
        });

        Thread.sleep(1000);
        System.out.println("future0.isDone(): " + future0.isDone());
        System.out.println("future1.isDone(): " + future1.isDone());
        System.out.println("future0.get(): " + future0.get());
        System.out.println("future1.get(): " + future1.get());
    }
}
