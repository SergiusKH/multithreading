package lesson7;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Sergius on 17.06.2015.
 */
public class Demo08_invokeAll {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        Callable<Integer> task0 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 42;
            }
        };

        Callable<Integer> task1 = new Callable<Integer>() {
            @Override
            public Integer call() throws InterruptedException {
                while (true) { if (Thread.interrupted()) throw new InterruptedException();}
//                throw  new  RuntimeException();
            }
        };

//        List<Future<Integer>> result = executorService.invokeAll(Arrays.asList(task0, task1));
        Integer result = executorService.invokeAny(Arrays.asList(task0, task1));
        System.out.println(result);

        executorService.shutdownNow();
    }
}
