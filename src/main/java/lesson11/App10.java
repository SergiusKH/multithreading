package lesson11;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;

/**
 * Created by Sergius on 23.06.2015.
 */
public class App10 {

    public static void main(String[] args) {
        Optional<Integer> sum =
                 of(1, 2, 3)
                .reduce((x, y) -> x + y);
        Optional<Integer> sqr = sum.map(x -> x * x);
        Optional<String> str = sqr.map(x -> "#" + x);
        System.out.println(str);



    }

    {
        Optional<Integer> sum =
                 of(1, 2, 3)
                .filter(x -> x > 10)
                .reduce((x, y) -> x + y);
        Optional<Integer> sqr = sum.map(x -> x * x);
        Optional<String> str = sqr.map(x -> "#" + x);
        System.out.println(str);
    }
}
