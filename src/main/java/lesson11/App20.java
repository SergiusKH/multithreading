package lesson11;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;
import static java.util.concurrent.CompletableFuture.*;

/**
 * Created by Sergius on 23.06.2015.
 */
public class App20 {

    public static void main(String[] args) throws IOException {

        CompletableFuture<String> f0 = supplyAsync(() ->{
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "A";
        });

        CompletableFuture<String> f1 = supplyAsync(() ->{
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "b";
        });

//        f0.acceptEitherAsync(f1, System.out::println);

        f0.thenAcceptBothAsync(f1, (x , y) -> System.out.println(x + " # " + y));

        System.in.read();
    }
}
