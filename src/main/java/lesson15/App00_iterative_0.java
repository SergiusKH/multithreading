package lesson15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Sergius on 02.07.2015.
 */
public class App00_iterative_0 {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong result = new AtomicLong(0);
        int cpuCount = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(cpuCount);

        List<Callable<Void>> taskList = new ArrayList();
        for (int i = 0; i < 100; i++) {
            final int finalI = i;
            taskList.add(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    calc(result, 10_000 * finalI, 10_000 * (finalI + 1));
                    return null;
                }
            });
        }
        pool.invokeAll(taskList);
        System.out.println(result);
        pool.shutdown();
    }

    private static void calc(AtomicLong result, int from, int to) {
        for (int index = from; index < to; index++) {
            if (index % 3 != 0 && index % 5 != 0) {
                result.addAndGet(index);
            }
        }
    }
}
