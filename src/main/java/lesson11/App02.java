package lesson11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.*;
import static java.util.concurrent.Executors.*;

/**
 * Created by Sergius on 23.06.2015.
 */
public class App02 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = newCachedThreadPool();
        // invokeAll - блокируе пока все задачи не выполнятся
        List<Future<Object>> listFutBytes = pool
                .invokeAll(asList(
                        () -> readAllBytes(get("d:/tmp0.txt")),
                        () -> readAllBytes(get("d:/tmp1.txt")),
                        () -> readAllBytes(get("d:/tmp2.txt"))));

        System.out.println(listFutBytes.get(0).isDone());
        System.out.println(listFutBytes.get(1).isDone());
        System.out.println(listFutBytes.get(2).isDone());
    }
}
