package lesson15;

import java.util.stream.Stream;

/**
 * Created by Sergius on 05.07.2015.
 */
public class App10_lazy_2 {

    public static void main(String[] args) {
        Stream<Integer> stream0 = Stream.generate(() -> {
            System.out.println("get()");
            return 0;
        });

        Stream<Integer> stream1 = stream0.filter(x -> x != 0);
        Stream<String> stream2 = stream1.map(x -> "" + x);
        Stream<String> stream3 = stream2.limit(3);

        stream3.forEach(System.out::println);
    }
}
