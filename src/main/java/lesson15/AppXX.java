package lesson15;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by Sergius on 05.07.2015.
 */
public class AppXX {

    public static void main(String[] args) {
        Random random = new Random(0);
        Stream<Integer> stream = Stream.generate(() -> random.nextInt(8));

        stream.distinct().limit(3).forEach(System.out::println);
        stream.sorted().limit(3).forEach(System.out::println);
    }
}
