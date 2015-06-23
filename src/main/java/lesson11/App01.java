package lesson11;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.nio.file.Files.*;
import static java.nio.file.Paths.*;
import static java.util.concurrent.Executors.*;

/**
 * Created by Sergius on 23.06.2015.
 */
public class App01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = newCachedThreadPool();

        Future<byte[]> futBytes0 = pool.submit( () -> readAllBytes(get("d:/tmp0.txt")));
        Future<byte[]> futBytes1 = pool.submit( () -> readAllBytes(get("d:/tmp1.txt")));
        Future<byte[]> futBytes2 = pool.submit( () -> readAllBytes(get("d:/tmp2.txt")));

        System.out.println(futBytes0.isDone());
        byte[] bytes = futBytes0.get();
        System.out.println(futBytes0.isDone());
        System.out.println(Arrays.toString(bytes));
    }
}
