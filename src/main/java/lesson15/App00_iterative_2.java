package lesson15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Sergius on 04.07.2015.
 */
public class App00_iterative_2 {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong result = new AtomicLong(0);
        int cpuCount = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cpuCount);
        final CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            pool.submit(() -> {
                long localResult = calc(10_000 * finalI, 10_000 * (finalI + 1));
                result.addAndGet(localResult);
                latch.countDown();
            });
        }
        // final barrier
        latch.await();

        System.out.println(result);
        pool.shutdown();
    }

    private static long calc(int from, int to) {
        long result = 0;
        for (int index = from; index < to; index++) {
            if (index % 3 != 0 && index % 5 != 0) {
                result += index;
            }
        }
        return result;
    }
}
