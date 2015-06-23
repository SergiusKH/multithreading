package lesson11;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Sergius on 23.06.2015.
 */
public class App11 {

    public static void main(String[] args) {
        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(() -> {
                    for (int i = 0; i < Long.MAX_VALUE; i++);
                    return "42";
                });
        CompletableFuture<Integer> f1 = f0.thenApply(Integer::valueOf);
        CompletableFuture<Double> f2 = f1.thenApply(x -> Math.PI * x *x);
        f2.thenAccept(System.out::println);

        System.out.println("End!");
    }
}
