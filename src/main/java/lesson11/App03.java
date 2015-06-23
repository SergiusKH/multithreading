package lesson11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.concurrent.Executors.newCachedThreadPool;

/**
 * Created by Sergius on 23.06.2015.
 */
public class App03 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = newCachedThreadPool();
        // invokeAny - отпускает тогда когда хотяды одна задача выполнена
        byte[] bytes = pool
                .invokeAny(asList(
                        (Callable<byte[]>)() -> readAllBytes(get("d:/tmp0.txt")),
                        (Callable<byte[]>)() -> readAllBytes(get("d:/tmp1.txt")),
                        (Callable<byte[]>)() -> readAllBytes(get("d:/tmp2.txt"))
                ));

        System.out.println(Arrays.toString(bytes));
    }
}
